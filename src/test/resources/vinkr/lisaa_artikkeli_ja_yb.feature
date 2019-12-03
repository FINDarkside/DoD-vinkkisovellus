Feature: Käyttäjä voi lisätä artikkeli- tai Youtube-videomuotoisen vinkkityypin jossa on avattava linkki

    Scenario: Artikkeli lisätään, kun kaikki pyydetyt tiedot annetaan
        Given komento "lisaa" annetaan ohjelmalle
        When  tyyppi "artikkeli", otsikko "Random Scrum article", kirjoittaja "Doe, John" ja URL "https://medium.com/thiscouldbeanarticle" annetaan
        Then  ohjelmaan tulostuu "Artikkeli lisätty"

    Scenario: Youtube-vinkki lisätään, kun kaikki pyydetyt tiedot annetaan
        Given komento "lisaa" annetaan ohjelmalle
        When  tyyppi "youtube", URL "https://www.youtube.com/watch?v=GazC3A4OQTE", otsikko "Djikstra's Algorithm" ja kanava "Computerphile" annetaan
        Then  ohjelmaan tulostuu "Video lisätty"

    Scenario: Artikkelin lisääminen ei onnistu, ellei käyttäjän syöttämä url-osoite ole asianmukainen
        Given komento "lisaa" annetaan ohjelmalle
        When  tyyppi "artikkeli", otsikko "Random agile article", kirjoittaja "Doe, John" ja virheellinen URL "tama.ei.nayta.url.osoitteelta" annetaan
        Then  ohjelmaan tulostuu "Virhe: Anna kelvollinen URL-osoite"

    Scenario: Youtube-linkin lisääminen ei onnistu, ellei käyttäjän syöttämä url-osoite ole asianmukainen
        Given komento "lisaa" annetaan ohjelmalle
        When  tyyppi "youtube", virheellinen URL "jokuLinkkiVaan", otsikko "Cats being derps" ja kanava "CATFACTS" annetaan
        Then  ohjelmaan tulostuu "Virhe: Anna kelvollinen URL-osoite"

    Scenario: Artikkelin lisääminen ei onnistu, jos käyttäjä jättää sen otsikon tyhjäksi
        Given komento "lisaa" annetaan ohjelmalle
        When  tyyppi "artikkeli", virheellinen otsikko "", kirjoittaja "Doe, John" ja URL "https://medium.com/thiscouldbeanarticle" annetaan
        Then  ohjelmaan tulostuu "Virhe: Otsikko ei saa olla tyhjä"

    Scenario: Youtube-linkin lisääminen ei onnistu, jos käyttäjä jättää sen otsikon tyhjäksi
        Given komento "lisaa" annetaan ohjelmalle
        When  tyyppi "youtube", URL "https://www.youtube.com/watch?v=GazC3A4OQTE", virheellinen otsikko "" ja kanava "Computerphile" annetaan
        Then  ohjelmaan tulostuu "Virhe: Otsikko ei saa olla tyhjä"
