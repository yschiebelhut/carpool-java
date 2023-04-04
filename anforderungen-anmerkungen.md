# Anforderungen

- expliziter Hinweis auf Sprechstunden, idealerweise 24 Stunden im Voraus in Moodle buchen

## Allgemeines

- Zeilen Code und Klassenanzahl ist Empfehlung
- Source Code selbst wird nicht bewertet
- bewertet wird die Ausführung zu den 6 Themen

## Refactoring

- es dürfen alle verwendet werden, die im Refactoring-Buch aufgeführt sind
- 3 Code-Smells heißt *nicht* drei verschiedene
- für die Punktevergabe kommt es aber besser, mehr verschiedene zu nehmen
- z.B. 3 mal das gleiche und dann sicherheitshalber noch ein weiteres, unterschiedliches
- nicht den gesamten Code nach Code-Smells durchsuchen, es reichen die aufgeführten Beispiele
- es geht darum, auf Grundlage unseres Code-Substrats eine Kompetenz nachzuweisen
- &rarr; schriftlicher Nachweis
- Repository und Quellcode dienen als Nachweis, dass wir die schriftlich beschriebenen Maßnahmen auch wirklich an
  unserem Code durchgeführt haben

## Unit Tests

- ATRIP-Regeln, wichtiger Punkt
- nicht überall anwenden, sondern zeigen, welche Entscheidung wir getroffen haben, um eine Regel in einem konkreten Test
  umzusetzen
- Code Coverage: einfacher Nachweis, dass wir ein Tool dafür genutzt haben und wir mit den Ergebnissen etwas anfangen
  können
- Mocks: mindestens 1 Unit-Test benutzt ein Mock-Objekt

## Entwurfsmuster

- mindestens 1 Muster einsetzen und begründen warum
- es reicht, zu erkennen, dass irgendwo ein Entwurfsmuster genutzt wird und dann zu begründen, warum das Muster da zum
  Einsatz kommt
- "Was ist die Rolle der Klasse und wie spielt sie dort eine Rolle?" (ist das richtig mitgeschrieben?)
- Diagramme bloß nicht weglassen, geben viele Punkte
- Diagramme werden kontrolliert!!
- ggf 1 beschreibendes Diagramm oder vorher/nachher Diagramm

## Clean Architecture

- Position der main-Methode analysieren und reflektieren
- Dependency Inversion würde man hier ggf anders beschreiben, als bei den Programming Principles
- es kann aber auch auf die anderen Kapitel verwiesen werden, wenn es Dopplungen gäbe

## Programming Principles

- es ist für DRY zum Beispiel nicht gefordert, dass der Code am Ende vollständig duplikaitonsfrei ist, sondern dass die
  Wiederholung erkannt wird und eine bewusste Entscheidung getroffen wird
- lieber über sämtliche Verletzungen schreiben, die wir erkannt haben und nur eine korrigieren, anstatt einzelne unter
  den Teppich zu kehren, als hätten wir sie gar nicht erkannt