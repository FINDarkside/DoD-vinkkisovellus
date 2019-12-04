Feature: Syötetyt lukuvinkit säilyvät käyttökerrasta toiseen

    Scenario: Talletettu vinkki näkyy listauksessa sen jälkeen, kun ohjelma käynnistetään uudelleen
        Given uusi kirjavinkki, jolla otsikko "Clean Code: A Handbook of Agile Software Craftsmanship", kirjoittaja "Martin, Robert", ISBN "978-0132350884", julkaisupaikka "USA", kustantaja "Someone" ja julkaisuvuosi "2008" annetaan
	When  komento "tallenna" annetaan ohjelmalle
	And   ohjelma kaynnistetaan uudestaan
        And   listataan kaikki lukuvinkit
        Then  ohjelma vastaa tulosteella, jossa kohdat "Tyyppi: Kirja", "Tekijä: Knuth, Donald", "Nimeke: The Art of Computer Programming" ja "ISBN: 0-201-03801-3"

