Feature: Käyttäjä voi syöttää komennon ensimmäiset kirjaimet jonka jälkeen ohjelma täydentää komennon loppuosan automaattisesti

    Scenario: Käyttäjä voi nähdä kaikki komennot kirjoittamalla vain osan komennosta 'apua'
        Given komennosta kirjoitetaan sen alku "a" ja painetaan TABia
        When  komento suoritetaan
        Then  ohjelmaan tulostuu "Kaikki käytettävissä olevat komennot:"

