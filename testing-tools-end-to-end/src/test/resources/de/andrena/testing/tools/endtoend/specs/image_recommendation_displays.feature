Feature: Fuer den aktuellen Tag werden dem Besucher die Temparatur, der aktuelle Ort 
	     und eine Kleidungsempfehlung in Form eines Bildes angezeigt. 

  Scenario: Ermittle Kleidungsvorschlag
    Given Ich habe ein Browserfenster geoeffnet
    When ich die Url der Anwendung aufrufe
    Then soll ein Bild mit einem Kleidungsvorschlag angezeigt werden