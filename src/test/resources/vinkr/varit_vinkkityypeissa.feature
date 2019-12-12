Feature: Käyttäjä voi tunnistaa eri vinkkityypit värien perusteella ja nähdä niiden lukuasteen lukuprosentin väristä

Scenario: Listattu kirjavinkki tulostuu halutulla värillä
        Given uusi kirjavinkki, otsikolla "The Art of Computer Programming", kirjoittajalla "Knuth, Donald", jonka ISBN on "0-201-03801-3", julkaisupaikalla "USA", kustantajalla "kustantaja" ja julkaisuvuodella "2008" lisataan
        When  listataan kaikki lukuvinkit
        Then  ohjelma vastaa tulosteella, jossa kirjan vari "sininen"

Scenario: Listattu artikkelivinkki tulostuu halutulla värillä
        Given uusi artikkelivinkki, urlilla "http://www.testiartikkeli.com/artikkeli1", otsikolla "Cucumberista", kirjoittajalla "Maestro, P", julkaisulla "" ja julkaisupaivalla "" lisataan
        When  listataan kaikki lukuvinkit
        Then  ohjelma vastaa tulosteella, jossa artikkelin vari "sinivihrea"

Scenario: Listattu youtubevinkki tulostuu halutulla värillä
        Given uusi youtubevinkki, urlilla "https://www.youtube.com/watch?v=dQw4w9WgXcQ", otsikolla "RickRolling", kanavalla "Official Rick Astley" ja julkaisupaivalla "24.10.2009" lisataan
        When  listataan kaikki lukuvinkit
        Then  ohjelma vastaa tulosteella, jossa youtubevideon vari "violetti"

