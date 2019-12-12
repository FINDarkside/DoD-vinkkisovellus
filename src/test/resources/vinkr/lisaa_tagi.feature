Feature: Käyttäjä voi lisätä tageja lukuvinkkeihin niin, että ne näkyvät vinkin tiedoissa

    Scenario: Käyttäjä voi luoda uusia tageja
        Given komento "tagit" annetaan ohjelmalle
        When  komennot "lisaa" ja "kiinnostava" annetaan tagivalikossa
        Then  ohjelmaan tulostuu "Uusi tagi luotu."

    Scenario: Käyttäjä voi liittää olemassaolevia tageja olemassaoleviin lukuvinkkeihin
        Given komento "tagit" annetaan ohjelmalle
        When  komento "liita" annetaan ohjelmalle
	And   liitetaan tagi "1" lukuvinkkiin "1"
        Then  ohjelmaan tulostuu "Tagi liitetty lukuvinkkiin"

    Scenario: Tagit näkyvät lukuvinkkien listauksessa
        Given luodaan tagi "suosittele" ja liitetaan se lukuvinkkiin "1"
        When  komento "listaa" annetaan ohjelmalle
        Then  ohjelmaan tulostuu "Tagit: suosittele"