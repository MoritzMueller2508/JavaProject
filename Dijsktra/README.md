# Dokumentation des Djikstra-Algorithmus
***Diese Dokumentation wurde in Markdown verfasst, um sie im GitReposity als Readme einzubinden.***\
***Es wird empfohlen, diese Readme auch im GitReposity zu betrachten: https://github.com/MoritzMueller2508/JavaProject***

-------------

## Installation und Ausführung

Zum Starten des Programms ist die JavaHausarbeitII.jar auszuführen, welche unter
/JavaHausarbeit/Dijkstra/out/artifacts/JavaHausarbeitII.jar gespeichert ist.

Befehl zum Ausführung über die Konsole:\
	***java -jar JavaHausarbeitII.jar***

-------------
-------------	

## Genutze Versionen:
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
-------------------------
-------------
## Methode

Das Programm, welches als Hausarbeit abgegeben wurde, basiert auf dem Dijkstra-Algorithmus.
Der Dijkstra-Algorithmus ist ein Algorithmus, welcher zur Familie der Greedy Algorithmen zählt und nach seinem Erfinder Edsger W. Dijkstra benannt wurde.
Dieser löst das Kürzeste-Weg Problem, bzw. das Berechnen des kürzesten Pfades für einen gegebenen Startknoten.

Der eigentliche Algorithmus berechnet den kürzesten Weg für alle Pfade ab einem beliebigen Startknoten. 
In der hier dargestellten Version wird jedoch weiterhin eine Methode zur Bestimmung des kürzesten Pfades zu einem Zielknoten implementiert.

Angenommen wird, dass ein ungerichteter Graph gegeben ist, welcher aus Knoten und Kanten, welche zwei Knoten verbindet, besteht.
Dabei besitzen die Kanten immer eine Gewichtung, womit der Algorithmus arbeitet.

-------------------------

### Funktionsweise des Algorithmus

Die Grundidee des Algorithmus ist es, immer der Kante zu folgen, welche dem kürzesten Streckenabschnitt vom Startknoten aus entspricht.
Andere Kanten werden erst dann verfolgt, wenn keine kürzeren Wege mehr existieren. Dies gewährleistet, dass immer der kürzeste Weg zu einem Knoten, relativ
zum Startknoten, erreicht wird. Diese Distanz wird als spezifischer Wert des Knotens gespeichert.
Die Distanz/der Wert von Knoten kann sich im Laufe des Algorithmus verändern, nämlich verringern, falls ein kürzerer Weg gefunden wurde.

-------------------------

### Pseudocode

Um den Algorithmus zu implementieren werden Mengen definiert. Die Menge "Edges" enthält alle Knoten, zu den noch kein kürzester Weg gefunden wurde.
Um den Vorgänger zu einem Knoten zu speichern, enthält die Klasse der Knoten das Attribut "Vorgänger", welches wieder ein anderer Knoten ist. \
Zu Beginn wird ein Graph initialisiert, welcher aus Knoten und Kanten, welche zwei Knoten durch eine gewichtete "Strecke" verbinden, besteht.
Weiterhin wird der Startknoten, von welchem der Algorithmus relativ abläuft, definiert,
wobei der Wert des Startknotens 0 ist, die Werte aller anderen Knoten jedoch, vorerst, als unendlich definiert sind. Vorgänger sind zu diesem Zeitpunkt noch nicht bestimmt.


Solange "Edges" nicht leer ist, wird der Algorithmus ausgeführt.
Der Knoten "min" wird als der Knoten definiert, welcher vom aktuellen Knoten "akt" den geringsten Abstand/kleinste Distanz besitzt.
Anschließend wird "min" aus "Edges" entfernt, da nun die kleinstmögliche Distanz gefunden wurde.


main Method:

	initialize(Startknoten)

	while Edges nicht leer:
		min: Knoten mit kleinstem Weg
		entferne min aus Edges					// min = Knoten mit kleinster Distanz
		
		für jeden Nachbarn "neig" von min:
			falls neig in Edges:
				update_distance(min, neig)
			
			
update_distance(Knoten min, Knoten neig):

	alternativ = (Wert von min) + (Kantengewicht zwischen min und neig)
	
	falls( (alternativ) < (Wert von min) ):
		Wert von neig = alternativ
		Vorgänger von neig = min
		
initialize(Knoten Startknoten):

		für jeden Knoten k, der im Graph enthalten ist:
			setzte Wert von k = unendlich
			setzte Vorgänger von k = null
		
		setzte Wert von Startknoten = 0
		Edges := Die Menge aller Knoten, die im Graph enthalten sind
	
Idealerweiße kann man diesen Algorithmus mit Hilfe einer Prioritätswarteschlange implementieren - Um es einfach zu halten, wurde dies in diesem Beispiel jedoch nicht auf diese Weise implementiert.

-------------------------
-------------
# Programmierdokumentation

**In diesem Abschnitt wird nicht auf den generellen Algorithmus eingegangen, sondern auf spezielle Designentscheidungen, da der Algorithmus unveränderbar ist und formal im Internet bereitsteht.**

*Kleinschrittigere Beschreibungen können innerhalb des Quellcodes als ausführliche Kommentare gefunden werden* 


Bei Ausführen des Programms wird zuerst abgefragt, ob der Testcase ausgeführt werden soll. Gibt man hier nichts ein und Enter wird gedrückt, wird standartmäßig "no" als Antwort verwendet.

![initialStatement](https://raw.githubusercontent.com/MoritzMueller2508/JavaProject/master/PicturesDocumentation/Dijkstra/testCase.png "initialStatement")

Wird "y" ausgewählt, so wird der Testcase ausgeführt. Bei jeder anderen Eingabe wird in den Standartmodus gewechselt. Nach dem Testcase ist das Programm fertig und muss neu ausgeführt werden.

Anschließend werden alle zur Auswahl stehenden Städte aufgeführt und der Benutzer wird aufgefordert, einen Startknoten und ein Ziel zu wählen.

![selectionNodes](https://raw.githubusercontent.com/MoritzMueller2508/JavaProject/master/PicturesDocumentation/Dijkstra/inputStartNode.png "selectionNodes")
Nach der Eingabe der Knoten werden die zu besuchenden Städte aufgelistet und die Enddistanz ausgegeben.
Das Programm ist nun fertig und muss neugestartet werden.

-------------------------
-------------
# Implementationsanmerkungen

## Klassen

***clsMain***
- Die Knoten und Kanten sind im Programm "Hardcoded". Da keine weitere Spezifikation in der Aufgabenstellung gegeben war, empfand ich dies als die einfachste Variante.
- Das zu implementierende Interface wurde durch die Konstante eingebunden, welche bei der Initialisierung des Graphen die Distanz jedes Knotens auf maxInteger setzt, da Unendlich nicht setztbar ist.
- Die Klasse "Pair" wurde verwendet, um mit Hilfe einer Methode zwei ArrayLists zu initialisieren und direkt zurückzugeben.
- Um den Algorithmus ausführen zu können und das Konzept von Generics beizubehalten, werden Objektinstanzen des clsDijkstraManagers, welcher den Algorithmus verwaltet, und eines clsGraphen, welcher für das Ausführen des Algorithmus genutzt wird, erzeugt.

-------------------------

***clsDijkstraManager***
- clsDijkstraManager implementiert das Interface *maxInteger*, welches genutzt wird, um den noch nicht besuchten Knoten den maximalen Integer-Wert zuzuweisen.
- clsDijkstraManager nutzt eine HashMap, um den Knoten die vorläufige Distanz zuzuweisen und anschließend mit der alternativen Distanz zu vergleichen.
	- Dies wurde als Antwort auf die Vorraussetzung gewählt, eine Map im Programm zu nutzen.
- Die Methode "findShortestPath" gehört zwar nicht zum grundlegenden Algorithmus von Dijkstra, jedoch wird sie später zur Ermittlung des kürzesten Weges genutzt.

-------------------------

***clsVertex***
- Das Objekt, welches aus der Klasse clsVertex generiert wird, stellt einen Knoten dar.
- Sie enthält die Attribute:
	- name (Name)
	- value (Distanz)
	- before (Vorgänger)
- Die Klasse besitzt einen Konstruktor, sowie getter und setter, da die Attribute privat sind.
- Die Methode printVertex wurde als debuggTool genutzt. 
	- Mir ist bewusst, dass es Möglich gewesen wäre, "@Override toString" zu nutzen, jedoch erschien mir die ausgeführte Implementierung zum derzeitigen Zeitpunkt übersichtlicher, da der Name für mich klarer erkennen ließ, wofür die Methode gedacht ist.

-------------------------

***clsEdge***
- Das Objekt, welches aus der Klasse clsEdges generiert wird, stellt eine Kante dar.
- Sie enthält die Attribute:
	- start (Startknoten)
	- end (Zielknoten)
	- distance (Kantengewicht)
- Die Klasse besitzt einen Konstruktor, sowie getter und setter, da die Attribute privat sind.
- Die Methode printEdge wurde als debuggTool genutzt. 
	- Mir ist bewusst, dass es Möglich gewesen wäre, "@Override toString" zu nutzen, jedoch erschien mir die ausgeführte Implementierung zum derzeitigen Zeitpunkt übersichtlicher, da der Name für mich klarer erkennen ließ, wofür die Methode gedacht ist.

-------------------------

***clsGraph***
- Das Objekt, welches aus der Klasse clsGraph generiert wird, stellt den Graph dar.
- Sie enthält die Attribute:
	- edges (Kanten)
	- vertex (Knoten)
- Die Klasse besitzt einen Konstruktor.

-------------------------

## Interface

***maxInteger***
- Das genutzte Interface implementiert den maximalen Integerwert durch Integer.MAX_VALUE.
- Es wird sowohl in clsMain, als auch in clsDijkstraManager implementiert.

-------------------------
-------------
# Versionen
**Git Commit History**
![history](https://raw.githubusercontent.com/MoritzMueller2508/JavaProject/master/PicturesDocumentation/Dijkstra/CommitH.png "CommitH")

-------------------------
-------------
# Testdokumentation

**Ausgeführte Tests - Hierbei wird nur start, end und distance dokumentiert. Die Ausgabe der zu besuchenden Städte auf dem Weg wird, um Platz zu sparen, ausgelassen**

![Tabel](https://raw.githubusercontent.com/MoritzMueller2508/JavaProject/master/PicturesDocumentation/Dijkstra/Testing.png "Testing")

-------------------------
-------------
# UML-Diagramme
*Hierbei wird nur ein Klassendiagramm, ein Aktivitätsdiagramm und ein UseCase-Diagramm genutzt, da andere UML-Arten keine besonderen Cases abdecken*

## Klassendiagramm

![ClassDiagram](https://raw.githubusercontent.com/MoritzMueller2508/JavaProject/master/PicturesDocumentation/Dijkstra/ClassDG.png "ClassDG")

-------------------------

## Aktivitätsdiagramm

![ActivityDiagram](https://raw.githubusercontent.com/MoritzMueller2508/JavaProject/master/PicturesDocumentation/Dijkstra/ActivityDG.png "ActivityDG")

-------------------------

## UseCase Diagramm
![UseCase](https://raw.githubusercontent.com/MoritzMueller2508/JavaProject/master/PicturesDocumentation/Dijkstra/UseCaseDG.png "UseCaseDG")
-------------



# Auflistung aller Klassen

## clsMain
- Methoden:
	- public static void main(String[] args)
	- private static Pair<ArrayList<clsVertex<String>>, ArrayList<clsEdge<String>>> initialize()
	- private static void printArrayList(ArrayList<clsVertex<String>> nodes)
	- private static clsVertex<String> getStartingPoint(String sP, ArrayList<clsVertex<String>> nodes)
	- private static clsVertex<String> getDestination(String dT, ArrayList<clsVertex<String>> nodes)
	
-------------------------

## clsDijkstraManager
- Attribute:
	- final HashMap<clsVertex<T>, Integer> neighbours = new HashMap<clsVertex<T>, Integer>()  {}
	- final ArrayList<clsVertex<T>> visited = new ArrayList<clsVertex<T>>()	
- Methoden:
	- public void dijkstra(clsGraph<T> graph, clsVertex<T> startNode)
	- private void initialize(clsVertex<T> startNode)
	- private int findSmallestValue(ArrayList<clsVertex<T>> Q)
	- private void findNeighbours(clsVertex<T> currentNode, ArrayList<clsEdge<T>> E, ArrayList<clsVertex<T>> Q)
	- public void findShortestPath(clsVertex<T> destination)
	
-------------------------
	
## clsEdge
- Attribute:
	- private clsVertex<T> start
	- private clsVertex<T> end
	- private int distance
- Methoden:
	- Konstruktor
	- getter/setter
	- public void printEdge() - debugTool
	
-------------------------
	
## clsVertex
- Attribute:
	- private T name
    - private int value
    - private clsVertex<T> before
- Methoden:
	- Konstruktor
	- getter/setter
	- public void printVertex() - debugTool
	
-------------------------
	
## clsGraph
- Attribute:
	- final ArrayList<clsEdge<T>> edges
    - final ArrayList<clsVertex<T>> vertex
-Methoden:
	- Konstruktor
	
-------------------------
	
# Auflistung der Interfaces

## maxInteger 
- Attribute:
	- int integerMaxValue = Integer.MAX_VALUE

-------------------------
-------------------------

# Quellen

- https://www-m9.ma.tum.de/graph-algorithms/spp-dijkstra/index_de.html
- https://de.wikipedia.org/wiki/Dijkstra-Algorithmus

# JavaProjectWork

This is my JavaProjectWork for university. 
This project will be my evaluation in the programmingInJava lecture.
In order to pass this semester, I need 56% in Java, to get 100% in C and Java combined.

So... Let's do this


You can start the project with your command prompt.
To do so, just type navigate to the folder JavaHausarbeit\out\artifacts and start the JavaHausarbeit.jar in the commandPromt or powerShell.
You will then be asked, on which point you want to start, and what your destination should be. You can choose out of existing cities.


ToDO: Write methode to add cities and paths.

