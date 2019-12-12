Feature: Käyttäjä voi hakea kirjaVinkin tiedot automaattisesti verkosta syöttämällä teoksen ISBN-numeron

    Scenario: Ohjelma lisää uuden kirjan ISBN perusteella, kun se on validi
        Given komento "lisaa" annetaan ohjelmalle
        When  valitaan tyyppi kirjaVinkki ja syotetaan validi ISBN "9780471268529"
        Then  ohjelmaan tulostuu "Kirja lisätty"

