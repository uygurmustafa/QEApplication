Transfer Page Test Scenarios
=====================
Created by musti on 12.01.2025

This is an executable specification file which follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.

Transfer Alanina Eksi deger girme
---------------
Tags:@transfer, @negative, @validation
* Login with username "mustafa.uygur@testinium.com" and password "x3azF6w(usKdsP5q"
* Click to element "OpenMoneyTransfer"
* Initiate money transfer
* Enter transfer amount "-300"
* Verify transfer amount is "-300.00"

Transfer Alanina Para Miktari Transfer Etme
---------------
Tags:@transfer, @money, @critical
* Login with username "mustafa.uygur@testinium.com" and password "x3azF6w(usKdsP5q"
* Click to element "OpenMoneyTransfer"
* Initiate money transfer
* Enter transfer amount "300"
* Click send transfer button
* Verify transfer amount is "300.00"