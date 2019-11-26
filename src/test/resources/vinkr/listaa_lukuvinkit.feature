Feature: Käyttäjä voi listata järjestelmässä olevat lukuvinkit

    Scenario: Listaaminen, kun järjestelmässä on lukuvinkkejä
        Given järjestelmässä on lukuvinkkejä
        When  komento "listaa" annetaan ohjelmalle
        Then  ohjelma vastaa tulosteella, jossa kohdat "Tekijä: ", "Nimeke: " ja "ISBN: "

    Scenario: Listaaminen, kun järjestelmässä ei ole lukuvinkkejä
        Given järjestelmässä ei ole lukuvinkkejä
        When  komento "listaa" annetaan ohjelmalle
        Then  ohjelma vastaa tulosteella "Järjestelmässä ei lukuvinkkejä"

    Scenario: Listaaminen, kun järjestelmään ensin lisätään lukuvinkki
        Given Uusi kirjavinkki, kirjoittajalla "Knuth, Donald", otsikolla "The Art of Computer Programming" ja jonka ISBN on "0-201-03801-3" lisätään
        When  komento "listaa" annetaan ohjelmalle
        Then  ohjelma vastaa tulosteella, jossa kohdat "Tekijä: Knuth, Donald", "Nimeke: The Art of Computer Programming" ja "ISBN: 0-201-03801-3"

