# Groupchat
Diese Aufgabe implementiert einen einfachen Gruppenchat.
Bearbeite dafür die einzelnen Methoden in den 3 Klassen.
Fange dabei bei Server an, mach dann mit ClientHandler weiter 
und bearbeite zuletzt den Client.
Die Aufgabe legt starken Fokus auf Sockets, Threads und Exceptions.

# Tests
Die Tests zu dieser Aufgabe sind nur sehr rudimentär und sollen
dir ein wenig Feedback dazu geben, ob die Klassen bereits so funktionieren wie gedacht. 
Du sollst am ehesten lokal selbst oder mit deinen Kommilitonen testen, da ihr dort leichter auf 
Fehler treffen werdet. 
Die ServerTests sollten ausführbar sein, wenn Server und ClientHandler implementiert sind.
Die ClientTests hängen von der Implementierung aller 3 Klassen ab.
Damit die Tests richtig funktionieren, solltest du alle Read-Operationen auf der Konsole mit dem consoleReader Attributen
in den jeweiligen Klassen durchführen.

# Hinweise
Wenn du mehrere Clients bei dir lokal ausführen möchtest, kannst du in der Run Configuration
unter `Modify Options -> Allow Multiple Instances` erlauben, dass du mehrere Instanzen des Programms
gleichzeitig ausführen kannst.
Standardmäßig verbinden die Clients nur auf den localhost, du kannst das jederzeit in der Main-Methode
ändern, oder du fügst eigenen Code zum Angeben einer IP-Adresse hinzu.
Deine IP-Adresse kannst du unter Windows mit `ipconfig` und unter Unix mit `ifconfig` in der Konsole herausfinden.



