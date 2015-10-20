---

# Testing Tools Beispielanwendung


Im Repository findet sich die Rahmen des Artikels/Vortrags vorgestellte Beipielanwendung. Diese ermittelt anhand der Koordinaten des Webbrowsers einen Kleidungsvorschlag (in Form eines Bildes) für den aktuellen Tag. 

Da der Fokus des Artikels/Vortrags auf Tools und Techniken rund um das Thema Integrations-Testen liegt, wurde der Darstellung wegen auf Unittests verzichtet. 

---

## 1 Vorbedingungen

### 1.1 Docker

Sämtliche Builds inklusive Tests bzw. mit aktiven - unter Punkt 3 genannten - [Build-Profilen](#3-build-profile) setzen eine laufende [Docker](https://www.docker.com)-Instanz voraus. Inbesondere müssen der Laufzeitumgebung sämtliche Docker-Umgebungsvariablen (also DOCKER_HOST etc.) bekannt gemacht werden.

### 1.2 Geolocation-Zugriff

Im verwendeten Browser muss ein Zugriff auf Geo-Koordinaten ermöglicht werden.

## 2 Komponenten der Anwendung

### 2.1 testing-tools-mongodb

Wrapper-Projekt zum Erstellen eines [mongoDB](https://www.mongodb.com) [Docker](https://www.docker.com)-Images.

### 2.2 testing-tools-image-service

[Spring Boot](http://projects.spring.io/spring-boot/)-Anwendung, die für Wetterbedingungen einen Kleidungsvorschlag in Form eines Bildes zur Verfügung stellt.

#### Build

Die Persistenz wird (bei Bedarf) auf dem lokalen Rechner für jeden Buildlauf neu initialisiert. Konkret wird im Rahmen des Buildlaufs mit aktivem Build-Profil *docker-host* der innerhalb **_[testing-tools-mongodb](#2-1-testing-tools-mongodb)_** erzeugte (frisch initialisierte) [Docker](https://www.docker.com)-Container mittels eines [Docker-Maven-Plugins](https://github.com/rhuss/docker-maven-plugin) "on the fly" gestartet und gestoppt. Sofern das Build-Profil *docker-host* nicht aktiviert wurde, wird eine laufende [mongoDB](https://www.mongodb.com)-Instanz auf localhost erwartet. 

#### Tests

* **Integration-Test**, der mittels [Springs Testframework](http://docs.spring.io/spring/docs/current/spring-framework-reference/html/integration-testing.html) exemplarisch integrativ die Funktionalität für den [mongoDB](https://www.mongodb.com)-Zugriff testet.
* **End-to-end-Test**, der die von der Anwendung bereitgestellte Rest-Schnittstelle mittels [REST-assured](https://github.com/jayway/rest-assured) und [Spring Boot](http://projects.spring.io/spring-boot/)'s Testframework  exemplarisch testet. 

### 2.3 testing-tools-weather-service

[Spring Boot](http://projects.spring.io/spring-boot/)-Anwendung, die anhand von Geo-Koordinaten die zugehörigen aktuellen Wetterbedingungen durch Zugriff auf einen externen Wetter-Service ermittelt und per Rest-Schnittstelle bereitstellt.

#### Build

Die Anwendung wird im Rahmen des Tests mittels des [Spring Boot-Maven-Plugins](http://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) initialisiert. Der externe Wetterdaten-Provider wird für Testläufe durch eine Attrappe ersetzt, die konstante Daten liefert und stabile reproduzierbare Tests ermöglicht. Konkret wird dies durch Aktivierung eines dedizierten Spring-Beanprofils erreicht.

#### Tests

* Integrative **Smoke-Tests** gegen den externen Wetterdaten-Provider. Diese werden bei Bedarf ausgeführt und mittels JUnit-Category aus den kontinuierlichen Build-Läufen entfernt.
* Exemplarischer **Komponenten-Test**, der die Anwendung mittels [REST-assured](https://github.com/jayway/rest-assured) integrativ testet.
* Exemplarischer **Komponenten-Test**, der mittels des Werkzeuges [Pact](https://github.com/DiUS/pact-jvm) den Kontrakt verifiziert, den der Benutzer des Service (**_[testing-tools-clothing-frontend](#2-4-testing-tools-clothing-frontend)_**) spezifiziert hat. Entkoppelt die Verifikation des Zusammenspiels von Provider und Consumer.

### 2.4 testing-tools-clothing-frontend

[Spring Boot](http://projects.spring.io/spring-boot/) und [AngularJs](https://angularjs.org) basierte Anwendung, die das Benutzerinterface zur Gesamtanwendung darstellt.

#### Tests

* **Integration-Test**, der das Interaktionsverhalten für den Zugriff auf den vom Modul **_[testing-tools-weather-service](#2-3-testing-tools-weather-service)_** bereitgestellten Wetter-Service beschreibt und integrativ gegen einen vom Werkzeug [Pact](https://github.com/DiUS/pact-jvm) bereitgestellten Mock-Rest-Service testet, ohne tatsächlich auf den Wetter-Service-Provider zugreifen zu müssen. Der im Rahmen des Tests spezifierte und aufgenommene 'Kontrakt' (das Pact-File) wird im Buildlauf des Providers **_[testing-tools-weather-service](#2-3-testing-tools-weather-service)_** ebenfalls verifiziert (siehe 2.3).

### 2.5 testing-tools-end-to-end

Maven-Modul, dass die Anwendung mittels [Selenium WebDriver](http://www.seleniumhq.org/projects/webdriver/) End-to-end testet.

#### Build

Die Gesamtanwendung wird bei aktivem Profil *docker-host* auf einem Unix-basiertem System mittels [Docker Compose](https://docs.docker.com/compose/) - angesteuert durch das [Exec Maven Plugin](http://www.mojohaus.org/exec-maven-plugin/) - gestartet. 
Der Build ist in diesem Falle plattform-spezifisch, da Compose zum Zeitpunkt des Artikels noch von keinem Maven-Plugin unterstützt wird. Im Normalfall würde man dies zu vermeiden versuchen und folglich solche Aufgaben auf einen zentralen Build-Server verlagern. 
Für einen erfolgreichen Build muss die Gesamtanwendung mit den [Build-Profilen](#3-build-profile) *docker-image* und *docker-host* gebaut worden sein. Neben den Voraussetzungen aus [Vorbedingungen](#1-1-docker) muss der Laufzeitumgebung der Pfad zu [Docker Compose](https://docs.docker.com/compose/)  und dem Unix-Sleep-Kommando bekannt sein.

Im Buildablauf wird des weiteren ein [Docker](https://www.docker.com)-Container mit einem [Selenium Grid](https://github.com/SeleniumHQ/selenium/wiki/Grid2) Server initialisiert (nach Start erreichbar unter DOCKER_HOST-Ip:4444/wd/hub/static/resource/hub.html), dessen Chrome Browser die Applikation ansteuert. Eine lokale Browserinstallation ist somit nicht notwendig.

#### Tests

* Exemplarischer **End-to-end-Test**, der über die Oberfläche überprüft, ob alle Systemteile korrekt zusammenspielen, ohne funktionale Details zu testen.

## 3 Build-Profile

### 3.1 docker-image

Erzeugt für die jeweiligen Komponenten anhand eines Dockerfiles und [Maven-Plugins](https://github.com/spotify/docker-maven-plugin) ein [Docker](https://www.docker.com)-Image und legt dies in der lokalen Docker-Registry ab. Docker muss im Rahmen des Builds gestartet sein. Das einmalige Zuschalten des Profils ist Voraussetzung dafür, die Anwendung mit Profil *docker-host* bauen und später per [Docker Compose](https://docs.docker.com/compose/) (siehe 4.1) starten zu können.

### 3.2 docker-host

Modifiziert zur Buildzeit Ip-Adressen, unter denen die Anwendungskomponenten zur Laufzeit miteinander kommunizieren. Erforderlich zum Zugriff auf mittels *docker-image* erstellte [Docker](https://www.docker.com)-Images. Sorgt im Modul **_[testing-tools-image-service](#2-2-testing-tools-image-service)_** dafür, dass für Tests "on-the-fly" eine [mongoDB](https://www.mongodb.com)-Instanz gestartet wird.

## 4 Starten der Anwendung

### 4.1 Docker Compose

Sofern die Anwendung mit den Profilen *docker-image* und *docker-host* gebaut wurde, kann Sie mittels eines eines [Docker Compose](https://docs.docker.com/compose/)-Files (im Modul **_[testing-tools-end-to-end](#2-5-testing-tools-end-to-end)_**) gestartet werden. Der Zugriff erfolgt dann unter der DOCKER_HOST-Ip und dem Port 8080.

### 4.2 manuell

Die einzelnen Teilanwendungen können (ohne die Profile *docker-image* und *docker-host*) gebaut werden und dann "normal" unter der Adresse http://localhost:8080 erreicht werden. Voraussetzung hierfür ist, dass auf dem localhost eine [mongoDB](https://www.mongodb.com)-Instanz verfügbar ist.


Dieses Beispielanwendung ist lizenziert unter einer [Creative Commons Namensnennung - Nicht-kommerziell - Weitergabe unter gleichen Bedingungen 4.0 International Lizenz](http://creativecommons.org/licenses/by-nc-sa/4.0/)
(c) andrena objects ag
