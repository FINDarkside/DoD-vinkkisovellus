Feature: Help komento

    Scenario: Käyttäjälle listataan komennot, kun hän kirjoittaa komentoriville sanan "apua"
        Given komento "apua" annetaan ohjelmalle
	When  komento suoritetaan
        Then  ohjelmaan tulostuu "Kaikki käytettävissä olevat komennot:"

    Scenario: Kun käyttäjä antaa virheellisen komennon, häntä kehotetaan käyttämään komentoa "apua"
        Given komento "toimiikohan" annetaan ohjelmalle
	When  komento suoritetaan
        Then  ohjelmaan tulostuu "Jos tahdot nähdä kaikki komennot, kirjoita 'apua'"