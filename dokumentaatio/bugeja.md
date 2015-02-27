### parasTetris

* Peliruutu aukeaa väärän kokoisena windowsilla jostain syystä. Jos näin käy kannattaa Kayttoliittyma-luokasta riviltä 25 muuttaa setResizable-metodin parametriksi true.

* Joillakin koneille .jar-tiedosto ei tunnu pyörivän kovin sulavasti. Netbeansissä ohjelma kuitenkin tuntuu aina toimivan oikein.
* Jos tetris-kansiossa ei ole "scoret.txt" tekstitiedostoa antaa ohjelma erroria pyöriessään kunnes tulee game over jolloin puuttuva tiedosto luodaan.
