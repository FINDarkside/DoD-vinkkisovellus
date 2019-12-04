Feature: Käyttäjä voi lisätä järjestelmään kirjamuotoisen lukuvinkin, jolla on otsikko, kirjoittaja ja ISBN

    Scenario: Kirja lisätään, kun kaikki pyydetyt tiedot annetaan
        Given komento "lisaa" annetaan ohjelmalle
        When  tyyppi "kirja", otsikko "Clean Code: A Handbook of Agile Software Craftsmanship", kirjoittaja "Martin, Robert", ISBN "978-0132350884", julkaisupaikka "USA", kustantaja "Someone" ja julkaisuvuosi "2008" annetaan
        Then  ohjelmaan tulostuu "Kirja lisätty"

    Scenario: Kirja lisätään, kun ainoastaan otsikko annetaan
        Given komento "lisaa" annetaan ohjelmalle
        When  tyyppi "kirja", otsikko "Clean Code: A Handbook of Agile Software Craftsmanship", kirjoittaja "", ISBN "", julkaisupaikka "", kustantaja "" ja julkaisuvuosi "" annetaan
        Then  ohjelmaan tulostuu "Kirja lisätty"

    Scenario: Kirjan lisääminen ei onnistu, jos käyttäjä jättää sen otsikon tyhjäksi
        Given komento "lisaa" annetaan ohjelmalle
        When  tyyppi "kirja", virheellinen otsikko "", kirjoittaja "Martin, Robert", ISBN "978-0132350884", julkaisupaikka "USA", kustantaja "Someone" ja julkaisuvuosi "2008" annetaan
        Then  ohjelmaan tulostuu "Virhe: Otsikko ei saa olla tyhjä"

