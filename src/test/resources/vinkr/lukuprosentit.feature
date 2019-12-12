Feature: Käyttäjä voi merkitä lukuvinkkiin kuinka monta prosenttia siitä on lukenut, nähdä vinkkien lukuprosentit listauksessa ja päivittää niitä

Scenario: Käyttäjän määrittämä lukuprosentti lukuvinkille näkyy listauksessa
        Given uusi kirjavinkki, otsikolla "The Art of Computer Programming", kirjoittajalla "Knuth, Donald", jonka ISBN on "0-201-03801-3", julkaisupaikalla "USA", kustantajalla "kustantaja" ja julkaisuvuodella "2008" lisataan
        When  komento "listaa" annetaan ohjelmalle
        Then  juuri lisatyn kirjavinkin lukuprosentti on listauksessa "0"

Scenario: Käyttäjän voi muuttaa tietyn lukuvinkin lukuprosenttia
        Given komento "lue" annetaan ohjelmalle
        When  kayttaja valitsee vinkin numero "1" ja maarittaa sen lukuprosentiksi "25"
        Then  ohjelmaan tulostuu "Lukuprosentti päivitetty"

Scenario: Tulostettujen lukuvinkkien lukuprosentit näkyvät eri väreissä
        Given uusi kirjavinkki, otsikolla "The Art of Computer Programming", kirjoittajalla "Knuth, Donald", jonka ISBN on "0-201-03801-3", julkaisupaikalla "USA", kustantajalla "kustantaja", julkaisuvuodella "2008" ja lukuprosentilla "10" lisataan     
	When  komento "listaa" annetaan ohjelmalle
        Then  juuri lisatyn kirjavinkin lukuprosentin vari on listauksessa "keltainen"