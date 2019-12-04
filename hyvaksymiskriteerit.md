## Hyväksymiskriteerit

[Projektin milestonet](https://github.com/Ohjelmistotuotanto-DoD/vinkr/milestones)

### Sprintti 2

Sprinttiin 2 valitut storyt ja niiden hyväksymiskriteerit

#### Artikkeli- ja Youtube-video -vinkkityypit

* Käyttäjä voi lisätä Youtube-videoita lukuvinkeiksi
* Käyttäjä voi lisätä artikkeleita lukuvinkeiksi
* Molemmissa yksi kysyttävä kenttä on vinkin URL-osoite, jonka käyttäjä voi myöhemmin avata ohjelman kautta
* Molempien vinkkien lisäyksen yhteydessä syötettyjä tietoja validoidaan

[Cucumber-featuret](https://github.com/Ohjelmistotuotanto-DoD/vinkr/blob/master/src/test/resources/vinkr/lisaa_artikkeli_ja_yb.feature)

#### Lukuvinkit säilyvät käyttökerrasta toiseen

* Aiemmalla käyttökerralla syötetty lukuvinkki säilyy järjestelmässä käyttökertojen välillä

[Cucumber-featuret](https://github.com/Ohjelmistotuotanto-DoD/vinkr/blob/master/src/test/resources/vinkr/tallenna_vinkit.feature)

#### Help-komento

* Ohjelmassa on olemassa 'apua'-komento, jolla käyttäjä saa näkyviin kaikki komennot ja lyhyet kuvaukset niiden toiminnallisuudesta
* Kun käyttäjä antaa virheellisen komennon, muistutetaan häntä 'apua'-komennon olemassaolosta

[Cucumber-featuret](https://github.com/Ohjelmistotuotanto-DoD/vinkr/blob/master/src/test/resources/vinkr/help_komento.feature)

#### Kometojen autotäydennys

* Peruskomentoja syötettäessä tabia painamalla ohjelma täydentää komennon, jos käyttäjän syöte on yksiselitteisesti tulkittavissa ainoastaan tietyn komennon alkuosaksi
