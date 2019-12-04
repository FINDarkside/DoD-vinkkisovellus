Feature: Syötetyt lukuvinkit säilyvät käyttökerrasta toiseen

    Scenario: Talletettu vinkki näkyy listauksessa sen jälkeen, kun ohjelma käynnistetään uudelleen
        Given uusi kirjavinkki, otsikolla "The Art of Computer Programming", kirjoittajalla "Knuth, Donald" ja jonka ISBN on "0-201-03801-3" lisataan
	When  komento "tallenna" annetaan ohjelmalle
	And   ohjelma kaynnistetaan uudestaan
        And   listataan kaikki lukuvinkit
        Then  ohjelma vastaa tulosteella, jossa kohdat "Tyyppi: Kirja", "Tekijä: Knuth, Donald", "Nimeke: The Art of Computer Programming" ja "ISBN: 0-201-03801-3"

