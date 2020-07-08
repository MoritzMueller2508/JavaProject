# Dokumentation des GoogleCalendars
***Diese Dokumentation wurde in Markdown verfasst, um sie im GitReposity als Readme einzubinden***\
***Es wird empfohlen, diese Readme auch im GitReposity zu betrachten: https://github.com/MoritzMueller2508/JavaProject***


-------------


## Installation und Ausführung

Zum Starten des Programms ist die GoogleCalendar-1.0.jar auszuführen, welche unter\
*/JavaHausarbeit/GoogleCalendar/build/libs/GoogleCalendar-1.0.jar gespeichert ist*.

Zum Ausführen müssen folgende Argumente der .jar übergeben werden, in folgender Reihenfolge:

***java -jar GoogleCalendar-1.0.jar \<Start-Datum>  \<Ende-Datum> \<Text-Selektionskriterium> \<Dateiname>***

Hierbei muss gelten:
- Die Daten müssen in folgendem Format angegeben werden: **"yyyy-MM-dd"**
- Als Text-Selektionskriterium können RegEx-Ausdrücke genutzt werden. Sollen jedoch alle Events im angegeben Zeitraum ausgegeben werden, so muss als Text-Selektionskriterium "xxxx" angegeben werden *( '\*' ist nicht zulässig)*
	- Sollte das Text-Selektionskriterium keinem regEx-Ausdruck oder "xxxx" entsprechen, werden alle Events ausgegeben, welche das Text-Selektionskriterium im Titel **enthalten**
- Der Dateiname muss auf .ics enden

Als Ergebnis der Eingabe ruft das Programm die GoogleCalendarAPI auf und exportiert alle im Zeitraum liegenden Events, welche dem Text-Selektionskriterium entsprechen, in eine .ics Datei mit gegebenen Dateinamen.\
Beim ersten Ausführen des Programms wird der Nutzer aufgefordert, sich mit seinem Google-Konto anzumelden und der API Zugriff auf den GoogleCalendar zu gewähren.\
Diese .ics Datei kann nun in einen Kalender eingebunden/importiert werden.

Anders als die geforderte Herangehensweise, wird das Programm in diesem Fall über eine executable.jar file aufgerufen, da, aufgrund der Beschaffenheit des Gradle-Projekts, das Aufrufen über die geforderte Herangehensweise nicht möglich ist.\
Dafür entschuldige ich mich.

-------------

## Importieren der .ics Datei in den GoogleCalendar

***Zum importieren der neu erstellten .ics Datei in den GoogleCalendar, bitte folgendem "Tutorial" der Google-Dokumentation folgen:***
https://support.google.com/calendar/answer/37118?co=GENIE.Platform%3DDesktop&hl=de


-------------
-------------

## Genutzte Versionen
*Diese Informationen sind direkt aus IntelliJ übernommen (Sprache Englisch)*
- ProjectSDK: 1.8
- javac version "1.8.0_241"
- java version "1.8.0_241"
- Java(TM) SE Runtime Environment (build 1.8.0_241-b07)
- Java HotSpot(TM) 64-Bit Server VM (build 25.241-b07, mixed mode)
- ProjectLanguageLevel: "8 - Lambdas, type annotations etc."
- Gradle: 6.3
- Kotlin: 1.3.70
- Groovy: 2.5.10
- Ant: Apache Ant(TM) version 1.10.7 compiled on September 1 2019
- JVM: 1.8.0_241 (Oracle Corporation 25.241-b07)
- OS: Windows 10 10.0 amd64exit
- Libraries:
	- Gradle: com.fasterxml.jackson.core:jackson-core:2.1.3
	- Gradle: com.google.api-client:google-api-client:1.23.0
	- Gradle: com.google.apis:google-api-services-calendar:v3-rev305-1.23.0
	- Gradle: com.google.code.findbugs:jsr305:1.3.9
	- Gradle: com.google.guava:guava-jdk5:17.0
	- Gradle: com.google.http-client:google-http-client-jackson2:1.23.0
	- Gradle: com.google.http-client:google-http-client:1.23.0
	- Gradle: com.google.oauth-client:google-oauth-client-java6:1.23.0
	- Gradle: com.google.oauth-client:google-oauth-client-jetty:1.23.0
	- Gradle: com.google.oauth-client:google-oauth-client:1.23.0
	- Gradle: commons-codec:commons-codec:1.3
	- Gradle: commons-logging:commons-logging:1.1.1
	- Gradle: org.apache.httpcomponents:httpclient:4.0.1
	- Gradle: org.apache.httpcomponents:httpcore:4.0.1
	- Gradle: org.mortbay.jetty:jetty-util:6.1.26
	- Gradle: org.mortbay.jetty:jetty:6.1.26
	- Gradle: org.mortbay.jetty:servlet-api:2.5-20081211
-------------
-------------
## Methode
*Quellen werden in einem weiteren Abschnitt später aufgelistet*

Im Nachfolgenden werden zuerst die Grundsätze der genutzten GoogleCalendarAPI erörtert, anschließend wird auf das allgemeine Programm eingegangen.

> *"The Calendar API is a REST API that can be accessed through explicit HTTP calls or via the Google Client Libraries; the API exposes most of the features available in the Google Calendar Web interface."*

-------------

### Kalender 
Ein Kalender, welcher von der GoogleCalendarAPI bereitgestellt wurde ist eine Kollektion von Events, zusammen mit weiteren Metadaten, wie eine Zusammenfassung, die standart Zeitzone,
den Ort etc. Jeder Kalender ist durch eine ID definiert, welche als Email dargestellt wird. Weiterhin kann ein Email von mehreren Person gleichzeitig genutzt werden.

-------------

### Events 
Ein Event ist ein spezifisches Objekt, welches ein Datum oder eine Zeitangabe enthält. Events besitzen eine eindeutige ID innerhalb eines Kalenders.
Weiterhin können Events weitere Daten, neben einem Start-Datum und Ende-Datum, enthalten, wie z.B eine Zusammenfassung, eine Beschreibung etc.

Es gibt zwei Arten von Events:
- Ein einfaches Event, welches einmal innerhalb eines Kalenders auftritt
- Ein wiederholendes Event, welches mehrfach innerhalb eines Kalenders auftritt. Z.B wöchentliche Meetings.

Ein Event tritt in einem spezifischen Zeitraum auf, welcher duch ein Datum und eine Zeitangabe defniert ist. Dies nennt man ein "time-Event".\
Dennoch kann ein Event einen vollständigen Tag belegen. Dies nennt man ein "all-day-Event"

-------------

### Authentifizierung
> *"Jedes Event muss einenen Autorisierungstoken enthalten, welcher das Programm für die GoogleCalendarAPI identifiziert um so eine Anfrage an den Kalender zu senden."*

Dies erfolgt durch das erste Aufrufen des Programms. Dadurch wird der Nutzer aufgefordert, sich bei seinem GoogleAccount anzumelden und seinen GoogleKalender für das Programm freizugeben.

-------------

### Zusammenfassung

Zusammenfassend für die GoogleCalendarAPI können wir also annehmen, dass durch das erstmalige Einloggen und freigeben des GoogleKalenders, 
nun auf Informationen aus dem Kalender zugegriffen werden können, wodurch Informationen, wie Events, abrufbar sind, um diese im Programm weiter zu verarbeiten.

Genau dies nutzen wir im Programm, um unsere Events, welche mit dem Text-Selektionskriterium übereinstimmen, in einer .ics Datei zu exportieren.

-------------
-------------
## Das .ics Format

Eine .ics Datei muss eine grundlegende Formatierung umsetzten.
Der Beginn einer .ics Datei besteht aus dem Indikator, dass der Kalender nun beginnt (*BEGIN:VCALENDAR*), 
der Version der Datei (*VERSION:2.0*), 
der PROID (*PROID:Moritz_iCalProject*), welche die Instanz angibt, welche die .ics Datei erstellt hat,
und der Methode (*METHOD:PUBLISH*), welche die Art angibt, wie die Datei an den Nutzer übermittelt wird.

Ein Event muss immer folgende Syntax erfüllen:
- BEGIN:VEVENT
- UID: "Unique ID des Events"
- LOCATION: "Ort des Events" *- kann null sein*
- SUMMARY: "Titel des Events"
- DESCRIPTION: "Beschreibung des Events" *- kann null sein*
- CLASS: "Parameter, ob das Event öffentlich oder Privat ist" *- kann null sein*
- DTSTART: "Start des Kalendereintrags"
- DTEND: "Ende des Kalendereintrags"
- END:VEVENT *- Ende des Events*

Die Datei endet mit "END:VCALENDAR"


Beispiel:

![.icsFile](https://raw.githubusercontent.com/MoritzMueller2508/JavaProject/master/PicturesDocumentation/GC/icsFile.png ".icsFile")

-------------
-------------

## Funktionsweise des Programms

Die Grundidee des Programms ist es, die Events eines GoogleKalenders zu importieren, zu filtern und in eine .ics Datei zu exportieren.
Hierbei werden zuerst alle Events, welche die eingegebene Zeitangabe erfüllen, aus dem Kalender importiert. 
Anschließend werden die importierten Events mit Hilfe des Text-Selektionskriterium lokal gefiltert. 
Abschließend werden die gefilterten Events in eine .ics Datei exportiert. 

-------------
-------------

## Programmdokumentation
**In diesem Abschnitt wird auf den Ablauf des allgemeinen Programms eingegangen**

*Kleinschrittigere Beschreibungen können innerhalb des Quellcodes als ausführliche Kommentare gefunden werden*

-------------

### clsCalendarEvents

Um genau zu sein, ist die Klasse clsCalendarEvents eine Kopie der CalendarQuickstart.java File, welche zur Einbindung der GoogleCalendarAPI genutzt wurde.
Jedoch wurde diese Klasse mit weiteren Methoden ergänzt. 
Um das Programm nicht aus der CalendarQuickstart.java Klasse zu starten, wurde in diesem Projekt eine clsMain erstellt, welche nun die einzelnen Klassen verwaltet, sowie die Methoden der Klassen zur richtigen Zeit aufruft.

-------------

### Programmablauf

Bei Ausführung des Programms wird die Methode clsMain.main aufgerufen. Die erste Aktion dieser Methode ist das Aufrufen der Methode clsCalendarEvents.getEvents, welche als Parameter sowohl den Beginn, als auch den Endzeitpunkt des zu importierenden Zeitraums benötigt.
Diese gibt eine ArrayList mit allen Events im genannten Zeitraum zurück.

Anschließend wird das Text-Selektionskriterium aufgerufen und über die Methode regExParser zu einem String umgewandetl, welcher regEx-Ausdrücke unterstützt.\
Hierbei muss der Character '\*' durch den String ".\*" ersetzt werden.

Darauf hin werden die zuvor importierten Events mit Hilfe des Text-Selektionskriterium gefiltert und durch ein clsExport-Objekt in eine .ics Datei exportiert.
Die Entscheidung ein clsExport-Objekt zu erstellen, beruht darauf, dass die Klasse clsExport mit *Generics* arbeitet.\
Ohne das Erstellen eines neuen clsExport-Objekts war es nicht möglich, diese Klasse generisch aufzubauen.

-------------

### Anmerkung zum Interface

Das Interface ***BuildUp_iCal*** wurde verwendet, da die Aufgabenstellung es gefordert hat.
Es implementiert eine ArrayList\<String>, welche den Beginn der .ics Datei enthält.\
Die Klasse clsExport nutzt dieses Interface, um den Anfang der .ics Datei zu erstellen.


-------------
-------------

# Implementationsanmerkungen

## Klassen

**Jede Methode ist im Quellcode ausführlich beschrieben. Für genauere Informationen wird somit auf den Quellcode verwiesen!**

-------------

***clsMain***
- Die Klasse clsMain wurde erstellt, um den Import von Events, das Filtern und den Export der .ics Datei kontrolliert aufzurufen.\
Ursprünglich wurde das Programm über die CalendarQuickstart.java Klasse gestartet. Allerdings war dies zu unübersichtlich.
- Die statische Variable path wurde implementiert, um eventuell im späteren Verlauf des Projekts eine Möglichkeit zu implementieren, den Speicherort der .ics Datei zu bestimmen.
Aus Zeitgründen wurde diese Idee allerdings verworfen. Da die Variable path jedoch den Pfad zum "workingDirectory" darstellt, wurde sie als Parameter in das Objekt der clsExport übergeben.

-------------

***clsFilter***
- Die Klasse clsFilter implementiert den Filter, mit dem die importierten Events lokal gefiltert werden, um dem Text-Selektionskriterium zu entsprechen.

-------------

***clsExport***
- Die Klasse clsExport exportiert alle gefilterten Events in eine .ics Datei.
- Die Klasse clsExport implementiert das Interface BuildUp_iCal, mit dem die Anfangsdaten der .ics Datei bereitgestellt werden.
- Hierbei wird erst eine leere Datei erstellt, welche anschließend durch einen Filewriter beschrieben wird.
- Durch die Aufteilung in die Methoden zum Schreiben der Daten, wird das Konzept von DivideAndConquer angewendet, um das Programm möglichst übersichtlich zu halten.

-------------

***clsCalendarEvents***
- Eine Kopie der CalendarQuickstart.java Klasse mit weiteren Methoden und kleineren Änderungen.
	- Für weitere Informationen verweise ich auf den dokumentierten Quellcode \
	(*line 28-35 _ Description of changes made to the ClandarQuickstart.java*)

-------------

***CalendarQuickstart***
- Um die Vollständigkeit zu wahren, wurde die CalendarQuickstart.java, welche zum Aufsetzten der GoogleCalendarAPI benötigt wurde, im Projekt beibehalten.
-------------
## Interface

***BuildUp_iCal***
- Das genutzte Interface implementiert den Anfang der .ics Datei als ArrayList\<String>
- Es wird in der clsExport Klasse implementiert, in welcher über die ArrayList iteriert wird, um die darin enthaltenen Daten mit Hilfe eines Filewriters in die zu exportierende Datei zu schreiben

-------------
-------------
# Versionen
**Git Commit History**\
*jeder blau gefärbte commit entspricht einer eigentständigen Version des Programms*\
![CommitHistory](https://raw.githubusercontent.com/MoritzMueller2508/JavaProject/master/PicturesDocumentation/GC/CommitHistoryGC.png "CommitHistory")

-------------
-------------

## Testdokumentation
*Mit der finalen Version des Programms wurde eine .ics Datei erstellt.*\
*Die Bilder zeigen, wie die .ics Datei aufgebaut ist sowie die Implementierung in die Windows-CalendarApp*

![importCalendarApp](https://raw.githubusercontent.com/MoritzMueller2508/JavaProject/master/PicturesDocumentation/GC/EinbindenCalendar.png "ImportCalendarApp")

![.icsFile](https://raw.githubusercontent.com/MoritzMueller2508/JavaProject/master/PicturesDocumentation/GC/icsFile.png ".icsFile")


-------------
-------------

# UML-Diagramme

## Klassendiagramm

*Das hier aufgezeigte Klassendiagram enthält weiterhin alle Packages, welche im Projekt enthalten sind*

![ClassDiagram](https://raw.githubusercontent.com/MoritzMueller2508/JavaProject/master/PicturesDocumentation/GC/ClassDG.png "ClassDiagram")

-------------
## Dependencies Diagramm

*Das hier aufgezeigte Diagramm enthält alle Dependencies, welche von Gradle genutzt wurde*

![Dependencies](https://raw.githubusercontent.com/MoritzMueller2508/JavaProject/master/PicturesDocumentation/GC/GradleDependencies.png "Dependencies")

-------------
## Aktivitätsdiagramm
![Activity](https://raw.githubusercontent.com/MoritzMueller2508/JavaProject/master/PicturesDocumentation/GC/ActivityDG.png "Activity")
-------------
## UseCase Diagramm
![UseCase](https://raw.githubusercontent.com/MoritzMueller2508/JavaProject/master/PicturesDocumentation/GC/UseCaseDG.png "UseCase")


-------------
-------------

# Auflistung aller Klassen

## clsMain
- Attribute:
	- public static ArrayList\<String> events = new ArrayList\<>()
	- public static String path = System.getProperty("user.dir")
- Methoden:
	- public static void main(String[] args) throws IOException, GeneralSecurityException
	- private static DateTime dateTimeParserMin(String str)
	- private static DateTime dateTimeParserMax(String str)
	- private static String regExParser(String str)

-------------
	
## clsFilter
- Methoden:
	- public static ArrayList\<Event> filter(List\<Event> aL, String s)
	
-------------

## clsExport\<T> implements BuildUp_iCal
- Methoden:
	- public void export(String fileName, String pathName, ArrayList\<T> events)
	- private void writeBasicData(String pathName, String fileName)
	- public void writeEvents(String pathName, String fileName, ArrayList\<T> events) throws IOException
	- public void writeEnd(String pathName, String fileName) throws IOException

-------------

## clsCalendarEvents
- Attribute:
	- private static final String APPLICATION_NAME = "Google Calendar API Java Quickstart"
    - private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance()
    - private static final String TOKENS_DIRECTORY_PATH = "tokens"
	- private static final List\<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR_READONLY)
    - private static final String CREDENTIALS_FILE_PATH = "/credentials.json"
- Methoden:
	- private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException
	- public static List\<Event> getEvents(DateTime timeMin, DateTime timeMax) throws IOException, GeneralSecurityException
    - public static void addEvent(ArrayList\<Event> eventsOut, ArrayList\<String> events)
	- public static String timeParser(String dT_time)
	
-------------
	
## CalendarQuickstart
>**Um die Vollständigkeit zu wahren, blieb die CalendarQuickstart.java, welche zum Aufsetzten der GoogleCalendarAPI benötigt wurde, im Projekt enthalten.**

-------------

# Auflistung der Interfaces

## BuildUp_iCal
- Attribute:
	- ArrayList\<String> buildUp = new ArrayList\<String>()










## Quellen
- https://developers.google.com/calendar
- https://praxistipps.chip.de/ics-datei-erstellen-das-muessen-sie-beim-aufbau-beachten_98011
- https://www.kanzaki.com/docs/ical/dateTime.html
- https://support.google.com/calendar/answer/37118?co=GENIE.Platform%3DDesktop&hl=de

