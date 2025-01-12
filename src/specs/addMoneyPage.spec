Add Money Page Test Scenarios
=====================
Created by musti on 12.01.2025

This is an executable specification file which follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.
     
Kredi Kartindan Dogru Bilgilerle Hesaba Para Yukleme
---------------
Tags:Kredi kartindan dogru bilgilerle hesaba para yukleme
* Login with username "mustafa.uygur@testinium.com" and password "x3azF6w(usKdsP5q"
* Click to element "OpenMoneyTransfer"
* Add money with card details "1234123412341234", "Mustafa Uygur", "1026", "110" and amount "1100"
* Wait "2" seconds
* Check if amount is "1,100.00"


Kredi Kartindan Hatali Card No Ile Hesaba Para Yukleme
---------------
Tags:Kredi kartindan hatali card no ile hesaba para yukleme
* Login with username "mustafa.uygur@testinium.com" and password "x3azF6w(usKdsP5q"
* Click to element "OpenMoneyTransfer"
* Add money with card details "1234123412341256", "Mustafa Uygur", "1026", "110" and amount "1100"
* Wait "2" seconds
* Check if amount is "1,100.00"

Kredi Kartindan Hatali Date Ile Hesaba Para Yukleme
---------------
Tags:Kredi kartindan hatali date ile hesaba para yukleme
* Login with username "mustafa.uygur@testinium.com" and password "x3azF6w(usKdsP5q"
* Click to element "OpenMoneyTransfer"
* Add money with card details "1234123412341234", "Mustafa Uygur", "1028", "110" and amount "1100"
* Wait "2" seconds
* Check if amount is "1,100.00"

Kredi Kartindan Hatali CVV Ile Hesaba Para Yukleme
---------------
Tags:Kredi kartindan hatali CVV ile hesaba para yukleme
* Login with username "mustafa.uygur@testinium.com" and password "x3azF6w(usKdsP5q"
* Click to element "OpenMoneyTransfer"
* Add money with card details "1234123412341234", "Mustafa Uygur", "1026", "118" and amount "1100"
* Wait "2" seconds
* Check if amount is "1,100.00"

Kredi Kartindan Eksi Deger Ile Hesaba Para Yukleme
---------------
Tags:Kredi kartindan eksi deger ile hesaba para yukleme

* Login with username "mustafa.uygur@testinium.com" and password "x3azF6w(usKdsP5q"
* Click to element "OpenMoneyTransfer"
* Add money with card details "1234123412341234", "Mustafa Uygur", "1026", "110" and amount "-1100"
* Wait "2" seconds
* Check if amount is "1,100.00"


Kredi Kartindan Required Alan Kontrolu
---------------
Tags:Kredi kartindan hesaba basarili para yukleme

* Login with username "mustafa.uygur@testinium.com" and password "x3azF6w(usKdsP5q"
* Click to element "OpenMoneyTransfer"
* Add money with card details "", "", "", "" and amount "100"
* Wait "2" seconds
* Verify required field messages

Kredi Karti Amount Alanina Harf Girme Kontrolu
---------------
Tags:Kredi Karti Amount Alanina Harf Girme Kontrolu
* Login with username "mustafa.uygur@testinium.com" and password "x3azF6w(usKdsP5q"
* Click to element "OpenMoneyTransfer"
* Click to element "AddMoneyButton"
* Enter "abc" to amount field
* Verify warning message "JustNumberWarning" in element "amount must be"