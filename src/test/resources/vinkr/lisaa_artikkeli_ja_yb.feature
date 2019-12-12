Feature: Käyttäjä voi lisätä artikkeli- tai Youtube-videomuotoisen vinkkityypin jossa on avattava linkki

    Scenario: Artikkeli lisätään, kun kaikki pyydetyt tiedot annetaan
        Given komento "lisaa" annetaan ohjelmalle
        When  tyyppi "artikkeli", URL "https://medium.com", otsikko "Random Scrum article", kirjoittaja "Doe, John", julkaisu "medium" ja julkaisupaiva "29.3.2019" annetaan
        Then  ohjelmaan tulostuu "Artikkeli lisätty"

    Scenario: Youtube-vinkki lisätään, kun kaikki pyydetyt tiedot annetaan
        Given komento "lisaa" annetaan ohjelmalle
        When  tyyppi "youtube", URL "https://www.youtube.com/watch?v=GazC3A4OQTE", otsikko "Djikstra's Algorithm", kanava "Computerphile" ja julkaisupaivamaara "11.11.2018" annetaan
        Then  ohjelmaan tulostuu "Video lisätty"

    Scenario: Artikkelin lisääminen ei onnistu, ellei käyttäjän syöttämä url-osoite ole asianmukainen
        Given komento "lisaa" annetaan ohjelmalle
        When  tyyppi "artikkeli", virheellinen URL "jokuLinkkiVaan", otsikko "Random Scrum article", kirjoittaja "Doe, John", julkaisu "medium" ja julkaisupaiva "29.3.2019" annetaan
        Then  ohjelmaan tulostuu "Virhe: Anna kelvollinen URL-osoite"

    Scenario: Youtube-linkin lisääminen ei onnistu, ellei käyttäjän syöttämä url-osoite ole asianmukainen
        Given komento "lisaa" annetaan ohjelmalle
        When  tyyppi "youtube", virheellinen URL "jokuLinkkiVaan", otsikko "Cats being derps", kanava "CATFACTS" ja julkaisupaivamaara "11.12.2015" annetaan
        Then  ohjelmaan tulostuu "Virhe: Anna kelvollinen URL-osoite"

    Scenario: Artikkelin lisääminen ei onnistu, jos käyttäjä jättää sen otsikon tyhjäksi
        Given komento "lisaa" annetaan ohjelmalle
        When  tyyppi "artikkeli", URL "https://medium.com", virheellinen otsikko "", kirjoittaja "Doe, John", julkaisu "medium" ja julkaisupaiva "29.3.2019" annetaan
        Then  ohjelmaan tulostuu "Virhe: Otsikko ei saa olla tyhjä"

    Scenario: Youtube-linkin lisääminen ei onnistu, jos käyttäjä jättää sen otsikon tyhjäksi
        Given komento "lisaa" annetaan ohjelmalle
        When  tyyppi "youtube", URL "https://www.youtube.com/watch?v=GazC3A4OQTE", virheellinen otsikko "", kanava "Computerphile" ja julkaisupaivamaara "11.11.2018" annetaan
        Then  ohjelmaan tulostuu "Virhe: Otsikko ei saa olla tyhjä"

    Scenario: Linkin avaaminen ei onnistu, jos annettu numero ei vastaa mitään vinkkiä
        Given komento "avaa" annetaan ohjelmalle
        When  kayttaja valitsee vinkin numero "777"
	Then  ohjelmaan tulostuu "Virhe: Anna kelvollinen vinkin numero"
