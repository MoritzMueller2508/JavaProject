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
![UseCase](https://raw.githubusercontent.com/MoritzMueller2508/JavaProject/master/PicturesDocumentation/Dijkstra/UseCase.png "UseCaseDG")
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



# Weitere Fragen

### Nach welchem Prinzip arbeitet der Algorithmus von Dijkstra? Erläutern Sie.

>***Der Dijkstra-Algorithmus ist ein Algorithmus aus der Klasse der Greedy-Algorithmen und löst das Problem der kürzesten Pfade für einen gegebenen Startknoten***

Der Algorithmus wird dann angewandt, wenn ein kürzester Pfad aus einem gegeben Graphen gesucht ist. Dabei ist der Graph kantengewichtet.\
Jedoch ist darauf zu achten, dass die Kanten keine negative Kantengewichtung enthalten. Sollte dies jedoch der Fall sein, ist der Algorithmus von Dijkstra nicht mehr anwendbar.
Stattdessen wird in diesem Fall empfohlen, auf den Bellman-Ford-Algorithmus zurückzugreifen, da dieser auch mit negativen Kantengewichtungen umgehen kann.

**Greedy-Algorithmen oder auch gierige Algorithmen, bilden eine spezielle Klasse von Algorithmen in der Informatik.**

Hierbei ist die Lösung eines Greedy-Algorithmus nicht immer die optimale Lösung, da immer die aktuell beste Entscheidung gewählt wird, ohne jedoch auf vorherige Entscheidungen oder Auswirkungen dieser zu achten.

>Greedy-Algorithmen berechnen jeweils ein "lokales Optimum" in jedem Schritt und können daher eventuell ein "globales Optimum" verpassen.

Da ein Greedy-Algorithmus immer nur die aktuell beste Entscheidung trifft, ist die Laufzeit eines solchen Algorithmus verhältnismäßig gering. 

Algorithmen, welche dazu ausgelegt sind das kürzeste-Wege-Problem zu lösen, allerdings nicht der Klasse der Greedy-Algorithmen angehören, müssen pro getroffener Entscheidung den vollständigen Graphen durchlaufen.
Dadurch ist ein Vorteil eines Greedy-Algorithmus entgegen anderen Algorithmen, dass ein Greedy-Algorithmus schnell eine Lösung findet, während andere Algorithmen eventuell kein Ergebnis in endlicher Zeit liefern können. 

Allgemein weisen Greedy-Algorithmen fünf Komponenten auf:
1. Eine Kandidatenmenge, aus der eine Lösung erzeugt wird.
2. Eine Auswahlfunktion, die den besten Kandidaten wählt, welcher der Lösung hinzugefügt werden soll.
3. Eine Tauglichkeitsfunktion, die verwendet wird, um zu bestimmen, ob ein Kandidat dazu taugt, zu einer Lösung beizutragen.
4. Eine objektive Funktion, die einer Lösung oder Teillösung einen Wert zuweist.
5. Eine Lösungsfunktion, die anzeigt, dass der Nutzer eine vollständige Lösung entdeckt hat.


**Ein Beispiel:**

Aus einem Geldpool soll eine Menge von 15€ entnommen werden. Der Geldpool besteht aus 11-, 5- und 1- Euro-Wertmarken.\
Ein Greedy-Algorithmus würde pro Schritt die beste Lösung wählen, also den höchsten Wert vom Geldpool abziehen. Die befolgten Schritte wären dementsprechend:
1. 11€
2. 1€
3. 1€
4. 1€
5. 1€

Somit hätte der Algorithmus in 5 Schritten sein Ziel erreicht.
Die beste Möglichkeit um in möglichst wenigen Schritten das Ziel zu erreichen ist jedoch:
1. 5€
2. 5€
3. 5€

Der Greedy-Algorithmus benötigt also zwei Teilschritte mehr, obwohl er pro Schritt die beste Lösung wählt.
Das Ergebnis bleibt das gleiche, jedoch ist die Methode des Greedy-Algorithmus ineffizienter als die bestmögliche Ausführung.


--------------------------
### Wie könnten Sie Ihre Lösung erweitern, so dass nicht nur der kürzeste Weg, sondern auch der ökologisch beste Weg (”Green Route”) ermittelt werden kann?
### Was müssten Sie hierfür an Ihrem Programm ändern/erweitern?


Um den ökologisch sinnvollsten Weg wählen zu können, muss eine "Umweltgewichtung" eingeführt werden.\
Da der Dijkstra-Algorithmus in einem gewichteten Graphen ausgeführt wird, müssen hierbei die Kantengewichtungen neu definiert werden.

Während der herkömmliche Dijkstra-Algorithmus den kürzesten Weg abruft, die Gewichtung sich also in der Entfernung der verschiedenen Knoten zueinander wiederspiegelt (oder die genutzte Zeit), muss für die Gewichtung der Kanten eine neue Einheit und somit neue Werte gewählt werden.\
Eine Möglichkeit wäre, die Einheit "CO²-Ausstoß (Tonnen)" pro Weg zu nutzen. 

Somit würde, beispielsweise, eine Reise mit dem Flugzeug zwar viel Zeit sparen, jedoch drastische Ausmaße für die "GreenRoute" annehmen.
Die ökologisch sinnvollere Route wäre es hingegen, eine andere Route zu wählen, die eventuell ohne ein Fahrzeug bewältigt werden kann, auch wenn sie um ein Vielfaches länger dauert.
Somit ist die GreenRoute nicht immer die schnellste Route. 


Um die GreenRoute in das Programm zu implementieren, müsste abgefragt werden, welche Version des Algorithmus der Nutzer ausführen möchte.\
Abhängig von der gewählten Option müssen die Kanten mit einer anderen Gewichtung initialisiert werden.

Bei genauer Sicht auf mein Programm, würde ich eine zweite ArrayList bereitstellen, welche die Kanten mit eben genannter Umweltgewichtung enthält.
Abhängig von der gewählten Option wird eine von beiden bereitgestellten ArrayLists genutzt, um den Graphen zu initialisieren.

----------------------------
Quellen (Aufgaben): 
- https://www.bigdata-insider.de/der-greedy-algorithmus-a-843043/
- https://de.wikipedia.org/wiki/Greedy-Algorithmus
- https://www-m9.ma.tum.de/graph-algorithms/spp-dijkstra/index_de.html







