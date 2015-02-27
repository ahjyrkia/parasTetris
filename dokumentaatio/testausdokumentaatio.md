## parasTetris
* Paljon testauksesta on tehty suoraan testaamalla ohjelmaa, sillä graafisen puolen testaus pelkillä testeillä tekee ongelmanratkonnasta toivottaman vaikeaa.
* Esimerkiksi palikoiden hitboxien toteuttavan koodin testaamiseen tein sitä varten suotuisan ympäristön: yksinkertaiset palikat joita pystyi viemään takaisin ylöspäin ja tulostuksia pitkin koodia. Tulostusten avulla sain lopulta ratkottua suurimman osan ongelmista.

## bugeja
* Peliruutu aukeaa väärän kokoisena windowsilla jostain syystä. Jos näin käy kannattaa Kayttoliittyma-luokasta riviltä 25 muuttaa setResizable-metodin parametriksi true ja sitten venyttää ruutu alareunasta sen kokoiseksi että palikat näkyvät kokonaan.

* Joillakin koneille .jar-tiedosto ei tunnu pyörivän kovin sulavasti. Netbeansissä ohjelma kuitenkin tuntuu aina toimivan oikein.

* .jar tiedosto toimii fuksiläppärillä hyvin mutta todella lagisesti koulun koneella mutta Netbeansillä näyttää toimivan. Highscoret luotu. Peliruutu aukeaa väärän kokoisena windowsilla jostain syystä. Jos näin käy kannattaa Kayttoliittyma-luokasta riviltä 25 muuttaa setResizable-metodin parametriksi true.
