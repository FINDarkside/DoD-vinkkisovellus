# vinkr

[![CircleCI](https://circleci.com/gh/Ohjelmistotuotanto-DoD/vinkr.svg?style=svg)](https://circleci.com/gh/Ohjelmistotuotanto-DoD/vinkr)
[![codecov](https://codecov.io/gh/Ohjelmistotuotanto-DoD/vinkr/branch/master/graph/badge.svg)](https://codecov.io/gh/Ohjelmistotuotanto-DoD/vinkr)

**vinkr** on syksyn 2019 Ohjelmistotuotanto-kurssin miniprojekti, jossa tehtävänä on luoda lukuvinkkejä hallinnoiva sovellus. Sovellusta käytetään tekstikäyttöliittymällä komentorivin kautta.

### Asennus- ja käyttöohje



#### Lataaminen

Hanki sovellus kloonaamalla tämä Git-repositorio:
<pre>
git clone git@github.com:Ohjelmistotuotanto-DoD/vinkr.git
</pre>
tai lataa projekti ZIP-tiedostona painamalla oikean yläkulman vihreää "Clone or download" -nappia ja valitsemalla "Download ZIP". Pura ZIP haluamaasi sijaintiin.

#### Buildaaminen

Komento
<pre>
./gradlew build
</pre>
luo kansioon _build/libs_ ajettavan jar-tiedoston _vinkr-all.jar_.

#### Ajaminen

Avaa terminaali projektin juureen ja käynnistä sovellus komennolla:
<pre>
./gradlew run -q --console=plain
</pre>
nyt tekstikäyttöliittymän tulisi käynnistyä.

Jar-tiedoston voi ajaa komennolla:
<pre>
java -jar vinkr-all.jar
</pre>
Huomaa että buildattaessa jar ei sijaitse projektin juuressa.

#### Käyttäminen

Kun projekti käynnistyy, tekstikäyttöliittymä toivottaa sinut tervetulleeksi näyttävällä taideteoksella sekä listaamalla kaikki käytettävissä olevat komennot ja niiden toiminnallisuuden. Ohjelmaa käytetään näillä komennoilla, ja komennot sekä toiminnallisuudet saat uudestaan näkyviin 'apua'-komennolla.

### Releaset

[Sprintin 2 release](https://github.com/Ohjelmistotuotanto-DoD/vinkr/releases/tag/v0.2-sprint2)

### Backlogit

[Projektin backlog](https://github.com/Ohjelmistotuotanto-DoD/vinkr/projects/2)

[Sprintin 1 backlog](https://github.com/Ohjelmistotuotanto-DoD/vinkr/projects/1)

[Sprintin 2 backlog](https://github.com/Ohjelmistotuotanto-DoD/vinkr/projects/3)

[Sprntin 3 backlog](https://github.com/Ohjelmistotuotanto-DoD/vinkr/projects/4)

### Definition of done

[Projektin definition of done](https://github.com/Ohjelmistotuotanto-DoD/vinkr/blob/master/definitionOfDone.md)

### Burndown-käyrät & edistyminen

[Taskien edistyminen ja burndown-käyrät (sprintit eri välilehtinä)](https://docs.google.com/spreadsheets/d/1IaOlbvyjOnPDexS8vWC0Q_28GK2p_VH0qf8ubIVeSeo/edit?usp=sharing)

### Storyjen hyväksymisehdot

[Sprinttiin 2 valitut storyt ja niiden hyväksymisehdot](https://github.com/Ohjelmistotuotanto-DoD/vinkr/blob/master/hyvaksymiskriteerit.md)

Sprintiin 3 valitut storyt on kirjattu [backlogiin](https://github.com/Ohjelmistotuotanto-DoD/vinkr/projects/4), linkit cucumber-featureihin storyissä

### Lisenssi

vinkr on lisensoitu [MIT-lisensillä](https://github.com/Ohjelmistotuotanto-DoD/vinkr/blob/master/LICENSE.md)
