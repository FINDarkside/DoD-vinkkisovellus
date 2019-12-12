Feature: Käyttäjä voi merkitä lukuvinkkiin kuinka monta prosenttia siitä on lukenut, nähdä vinkkien lukuprosentit listauksessa ja päivittää niitä

Scenario: Käyttäjän määrittämä lukuprosentti lukuvinkille näkyy listauksessa ja se on oletuksena 0
        Given uusi kirjavinkki, otsikolla "The Art of Computer POGramming", kirjoittajalla "Nuth, Donald", jonka ISBN on "", julkaisupaikalla "USA", kustantajalla "kustantaja" ja julkaisuvuodella "2008" lisataan
        When  listataan kaikki lukuvinkit
        Then  juuri lisatyn kirjavinkin lukuprosentti on listauksessa "0"

Scenario: Käyttäjä voi muuttaa tietyn lukuvinkin lukuprosenttia
        Given komento "lue" annetaan ohjelmalle
        When  kayttaja valitsee vinkin numero "1" ja maarittaa sen lukuprosentiksi "25"
        Then  ohjelmaan tulostuu "Lukuprosentti päivitetty"

Scenario: Tulostettujen kirjavinkkien lukuprosentit näkyvät eri väreissä
        Given uusi kirjavinkki, otsikolla "The Art of Computer POGramming", kirjoittajalla "Nuth, Donald", jonka ISBN on "0-201-03801-3", julkaisupaikalla "USA", kustantajalla "kustantaja", julkaisuvuodella "2008" ja lukuprosentilla "25" lisataan     
	When  listataan kaikki lukuvinkit
        Then  juuri lisatyn kirjavinkin lukuprosentin vari on listauksessa "keltainen"

Scenario: Tulostettujen artikkelivinkkien lukuprosentit näkyvät eri väreissä
        Given uusi artikkelivinkki, urlilla "http://www.testiartikkeli.com/artikkeli15", otsikolla "CucumberistaTaas", kirjoittajalla "Maestro, P", julkaisulla "", julkaisupaivalla "" ja lukuprosentilla "100" lisataan
	When  listataan kaikki lukuvinkit
        Then  juuri lisatyn artikkelivinkin lukuprosentin vari on listauksessa "vihrea"

Scenario: Tulostettujen youtubevinkkien lukuprosentit näkyvät eri väreissä
        Given uusi youtubevinkki, urlilla "https://www.youtube.com/watch?v=dQw4w9WgXcQ", otsikolla "RickRollingAgain", kanavalla "Official RickRoller Astley", julkaisupaivalla "24.10.2009" ja lukuprosentilla "0" lisataan
	When  listataan kaikki lukuvinkit
        Then  juuri lisatyn youtubevinkin lukuprosentin vari on listauksessa "punainen"