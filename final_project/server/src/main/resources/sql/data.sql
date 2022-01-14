insert into companies values (1, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'CORP', 'Norda Stelo');
insert into companies values (2, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'INC', 'Air Canada');
insert into companies values (3, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'IBC', 'Barrick Gold');
insert into companies values (4, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'JV', 'Biovail');
insert into companies values (5, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'CORP', 'Blackburn Radio');
insert into companies values (6, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'INC', 'Book City');
insert into companies values (7, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'IBC', 'Canpotex');
insert into companies values (8, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'JV', 'Coffee Time');
insert into companies values (9, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'CORP', 'Cyntech');
insert into companies values (10, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'INC', 'Dollarama');
insert into companies values (11, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'IBC', 'Fairchild Group');
insert into companies values (12, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'JV', 'Irving Oil');
insert into companies values (13, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'CORP', 'Jean Coutu Group');
insert into companies values (14, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'INC', 'Koingo Software');

insert into counterparties values (1, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'CLIENT', '538450167053', 'Donald Bren');
insert into counterparties values (2, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'SUPPLIER', '350721390244', 'Steve Cohen');
insert into counterparties values (3, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'CLIENT', '174628089090', 'Bobby Murphy');
insert into counterparties values (4, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'CLIENT', '879222723719', 'Brian Armstrong');
insert into counterparties values (5, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'SUPPLIER', '524915257407', 'Charles Schwab');
insert into counterparties values (6, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'CLIENT', '209849653298', 'Diane Hendricks');
insert into counterparties values (7, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'SUPPLIER', '674255224069', 'Jan Koum');
insert into counterparties values (8, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'CLIENT', '447014163068', 'Stanley Kroenke');
insert into counterparties values (9, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'SUPPLIER', '720442700667', 'Israel Englander');
insert into counterparties values (10, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'CLIENT', '339560953472', 'Marc Benioff');
insert into counterparties values (11, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'CLIENT', '254389050205', 'George Kaiser');
insert into counterparties values (12, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'SUPPLIER', '201544062296', 'Jim Kennedy');
insert into counterparties values (13, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'SUPPLIER', '844611697206', 'George Roberts');
insert into counterparties values (14, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'CLIENT', '492481044095', 'George Soros');
insert into counterparties values (15, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'SUPPLIER', '670949926702', 'John Malone');

insert into agreements values (1, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'CLIENT_AGREEMENT', 'Supply agreement №1002', 2, 3);
insert into agreements values (2, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'CLIENT_AGREEMENT', 'Supply agreement №1003', 2, 4);
insert into agreements values (3, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'CLIENT_AGREEMENT', 'Supply agreement №1004', 2, 6);
insert into agreements values (4, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'CLIENT_AGREEMENT', 'Supply agreement №1005', 8, 14);
insert into agreements values (5, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'CLIENT_AGREEMENT', 'Supply agreement №1006', 8, 11);
insert into agreements values (6, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'CLIENT_AGREEMENT', 'Supply agreement №1007', 13, 10);
insert into agreements values (7, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'SUPPLIER_AGREEMENT', 'Purchase agreement №1009', 13, 12);
insert into agreements values (8, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'SUPPLIER_AGREEMENT', 'Purchase agreement №1010', 13, 15);
insert into agreements values (9, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'SUPPLIER_AGREEMENT', 'Purchase agreement №1011', 13, 9);
insert into agreements values (10, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'SUPPLIER_AGREEMENT', 'Purchase agreement №1012', 2, 2);
insert into agreements values (11, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'SUPPLIER_AGREEMENT', 'Purchase agreement №1013', 7, 9);
insert into agreements values (12, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'SUPPLIER_AGREEMENT', 'Purchase agreement №1014', 6, 5);
insert into agreements values (13, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'CLIENT_AGREEMENT', 'agreement №1009', 13, 11);
insert into agreements values (14, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'CLIENT_AGREEMENT', 'agreement №1010', 13, 13);
insert into agreements values (15, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'CLIENT_AGREEMENT', 'agreement №1011', 13, 14);

insert into business_directions values (1, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'Furniture manufacturing');
insert into business_directions values (2, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'IT');
insert into business_directions values (3, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'Retail sales');
insert into business_directions values (4, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'Wholesale');
insert into business_directions values (5, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'Provision of services');

insert into company_business_direction values (2, 2);
insert into company_business_direction values (2, 5);
insert into company_business_direction values (14, 2);
insert into company_business_direction values (14, 5);
insert into company_business_direction values (14, 4);
insert into company_business_direction values (1, 1);
insert into company_business_direction values (1, 5);

insert into nomenclatures values (1, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'Winsome Halifax Storage/Organization, 7 drawer, Black', true, false, 1);
insert into nomenclatures values (2, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'Winsome Alden Bed Tray, Walnut', true, false, 1);
insert into nomenclatures values (3, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'Sauder North Avenue Bookcase, Charter Oak finish', true, false, 1);
insert into nomenclatures values (4, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'Sauder Beginnings 5-Shelf Bookcase, Highland Oak finish', true, false, 1);
insert into nomenclatures values (5, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'Hillsdale Furniture Dutton Vanity Stool, Chrome', true, false, 1);
insert into nomenclatures values (6, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'Furniture cleaning', false, true, 1);
insert into nomenclatures values (7, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'Kitchen design', false, true, 1);
insert into nomenclatures values (8, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'Furniture installation', false, true, 1);

insert into currencies values (1, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), '980', 'UAH');
insert into currencies values (2, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), '840', 'USD');
insert into currencies values (3, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), '978', 'EUR');

insert into price_types values (1, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'retail price');
insert into price_types values (2, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'wholesale price');

insert into prices values (1, '2022-01-03 13:05:00', 450.00, 1, 1);
insert into prices values (2, '2022-01-03 13:05:00', 470.00, 2, 1);
insert into prices values (3, '2022-01-03 13:05:00', 435.00, 3, 1);
insert into prices values (4, '2022-01-03 13:05:00', 515.00, 4, 1);
insert into prices values (5, '2022-01-03 13:05:00', 490.00, 5, 1);
insert into prices values (6, '2022-01-03 13:05:00', 395.00, 1, 2);
insert into prices values (7, '2022-01-03 13:05:00', 435.00, 2, 2);
insert into prices values (8, '2022-01-03 13:05:00', 365.00, 3, 2);
insert into prices values (9, '2022-01-03 13:05:00', 430.00, 4, 2);
insert into prices values (10, '2022-01-03 13:05:00', 415.00, 5, 2);
insert into prices values (11, '2022-01-10 12:05:00', 590.00, 5, 1);
insert into prices values (12, '2022-01-10 12:05:00', 515.00, 5, 2);

insert into purchase_invoices values (1, '2022-01-03 13:05:00', false, '2022-01-03 13:05:00', '2022-01-03 13:05:00', 10, 2, 2, 2);
insert into purchase_invoices_goods values (1, 110.0000, 15.00, 1650.00, 4, 1);
insert into purchase_invoices_goods values (2, 150.0000, 15.00, 2250.00, 5, 1);
insert into purchase_invoices_goods values (3, 170.0000, 15.00, 2550.00, 3, 1);
insert into purchase_invoices_goods values (4, 195.0000, 15.00, 2925.00, 2, 1);
insert into purchase_invoices_goods values (5, 210.0000, 15.00, 3150.00, 1, 1);

insert into stock_of_goods values (1, 1650.00, '2022-01-03 13:05:00', 1, 'PurchaseInvoice', 15.00, 2, 1, 4);
insert into stock_of_goods values (2, 2250.00, '2022-01-03 13:05:00', 1, 'PurchaseInvoice', 15.00, 2, 1, 5);
insert into stock_of_goods values (3, 2550.00, '2022-01-03 13:05:00', 1, 'PurchaseInvoice', 15.00, 2, 1, 3);
insert into stock_of_goods values (4, 2925.00, '2022-01-03 13:05:00', 1, 'PurchaseInvoice', 15.00, 2, 1, 2);
insert into stock_of_goods values (5, 3150.00, '2022-01-03 13:05:00', 1, 'PurchaseInvoice', 15.00, 2, 1, 1);

insert into purchase_invoices values (2, '2022-01-04 14:35:00', false, '2022-01-04 14:35:00', '2022-01-04 14:35:00', 10, 2, 2, 2);
insert into purchase_invoices_goods values (6, 135.0000, 5.00, 675.00, 4, 2);
insert into purchase_invoices_goods values (7, 160.0000, 5.00, 800.00, 5, 2);
insert into purchase_invoices_goods values (8, 150.0000, 5.00, 750.00, 3, 2);
insert into purchase_invoices_goods values (9, 180.0000, 5.00, 900.00, 2, 2);
insert into purchase_invoices_goods values (10, 270.0000, 5.00, 1350.00, 1, 2);

insert into stock_of_goods values (6, 675.00, '2022-01-04 14:35:00', 2, 'PurchaseInvoice', 5.00, 2, 2, 4);
insert into stock_of_goods values (7, 800.00, '2022-01-04 14:35:00', 2, 'PurchaseInvoice', 5.00, 2, 2, 5);
insert into stock_of_goods values (8, 750.00, '2022-01-04 14:35:00', 2, 'PurchaseInvoice', 5.00, 2, 2, 3);
insert into stock_of_goods values (9, 900.00, '2022-01-04 14:35:00', 2, 'PurchaseInvoice', 5.00, 2, 2, 2);
insert into stock_of_goods values (10, 1350.00, '2022-01-04 14:35:00', 2, 'PurchaseInvoice', 5.00, 2, 2, 1);

insert into purchase_invoices values (3, '2022-01-05 12:10:00', false, '2022-01-05 12:10:00', '2022-01-05 12:10:00', 8, 13, 15, 2);
insert into purchase_invoices_goods values (11, 230.0000, 15.00, 3450.00, 4, 3);
insert into purchase_invoices_goods values (12, 195.0000, 15.00, 2925.00, 5, 3);
insert into purchase_invoices_goods values (13, 170.0000, 15.00, 2550.00, 3, 3);
insert into purchase_invoices_goods values (14, 235.0000, 15.00, 3525.00, 2, 3);
insert into purchase_invoices_goods values (15, 310.0000, 15.00, 4650.00, 1, 3);

insert into stock_of_goods values (11, 3450.00, '2022-01-05 12:10:00', 3, 'PurchaseInvoice', 15.00, 13, 3, 4);
insert into stock_of_goods values (12, 2925.00, '2022-01-05 12:10:00', 3, 'PurchaseInvoice', 15.00, 13, 3, 5);
insert into stock_of_goods values (13, 2550.00, '2022-01-05 12:10:00', 3, 'PurchaseInvoice', 15.00, 13, 3, 3);
insert into stock_of_goods values (14, 3525.00, '2022-01-05 12:10:00', 3, 'PurchaseInvoice', 15.00, 13, 3, 2);
insert into stock_of_goods values (15, 4650.00, '2022-01-05 12:10:00', 3, 'PurchaseInvoice', 15.00, 13, 3, 1);

insert into purchase_invoices values (4, '2022-01-06 12:10:00', false, '2022-01-06 12:10:00', '2022-01-06 12:10:00', 9, 13, 9, 2);
insert into purchase_invoices_goods values (16, 290.0000, 10.00, 2900.00, 4, 4);
insert into purchase_invoices_goods values (17, 245.0000, 10.00, 2450.00, 5, 4);
insert into purchase_invoices_goods values (18, 315.0000, 10.00, 3150.00, 3, 4);
insert into purchase_invoices_goods values (19, 150.0000, 10.00, 1500.00, 2, 4);
insert into purchase_invoices_goods values (20, 232.0000, 10.00, 2320.00, 1, 4);

insert into stock_of_goods values (16, 2900.00, '2022-01-06 12:10:00', 4, 'PurchaseInvoice', 10.00, 13, 4, 4);
insert into stock_of_goods values (17, 2450.00, '2022-01-06 12:10:00', 4, 'PurchaseInvoice', 10.00, 13, 4, 5);
insert into stock_of_goods values (18, 3150.00, '2022-01-06 12:10:00', 4, 'PurchaseInvoice', 10.00, 13, 4, 3);
insert into stock_of_goods values (19, 1500.00, '2022-01-06 12:10:00', 4, 'PurchaseInvoice', 10.00, 13, 4, 2);
insert into stock_of_goods values (20, 2320.00, '2022-01-06 12:10:00', 4, 'PurchaseInvoice', 10.00, 13, 4, 1);

insert into sales_invoices values (1, '2022-01-05 15:54:00', false, '2022-01-05 15:54:00', '2022-01-05 15:54:00', 1, 2, 3, 2, 2);
insert into sales_invoices_goods values (1, 415.0000, 3.00, 1245.00, 5, 1);
insert into sales_invoices_goods values (2, 150.0000, 1.00, 150.00, 8, 1);

insert into stock_of_goods values (21, -450.00, '2022-01-05 15:54:00', 1, 'SalesInvoice', -3.00, 2, 1, 5);

insert into sales_income values (1, 450.00, '2022-01-05 15:54:00', 795.00, 3.00, 1245.00, 2, 5, 1);
insert into sales_income values (2, 0.00, '2022-01-05 15:54:00', 150.00, 1.00, 150.00, 2, 8, 1);

insert into sales_invoices values (2, '2022-01-06 15:55:00', false, '2022-01-06 15:55:00', '2022-01-06 15:55:00', 2, 2, 4, 2, 1);
insert into sales_invoices_goods values (3, 435.0000, 1.00, 435.00, 3, 2);
insert into sales_invoices_goods values (4, 450.0000, 3.00, 1350.00, 1, 2);
insert into sales_invoices_goods values (5, 435.0000, 2.00, 870.00, 3, 2);

insert into stock_of_goods values (22, -170.00, '2022-01-06 15:55:00', 2, 'SalesInvoice', -1.00, 2, 1, 3);
insert into stock_of_goods values (23, -630.00, '2022-01-06 15:55:00', 2, 'SalesInvoice', -3.00, 2, 1, 1);
insert into stock_of_goods values (24, -340.00, '2022-01-06 15:55:00', 2, 'SalesInvoice', -2.00, 2, 1, 3);

insert into sales_income values (3, 170.00, '2022-01-06 15:55:00', 265.00, 1.00, 435.00, 2, 3, 2);
insert into sales_income values (4, 630.00, '2022-01-06 15:55:00', 720.00, 3.00, 1350.00, 2, 1, 2);
insert into sales_income values (5, 340.00, '2022-01-06 15:55:00', 530.00, 2.00, 870.00, 2, 3, 2);

insert into sales_invoices values (3, '2022-01-08 15:56:00', false, '2022-01-08 15:56:00', '2022-01-08 15:56:00', 2, 2, 4, 2, 2);
insert into sales_invoices_goods values (6, 430.0000, 1.00, 430.00, 4, 3);
insert into sales_invoices_goods values (7, 395.0000, 2.00, 790.00, 1, 3);
insert into sales_invoices_goods values (8, 365.0000, 3.00, 1095.00, 3, 3);
insert into sales_invoices_goods values (9, 1350.0000, 2.00, 1350.00, 7, 3);

insert into stock_of_goods values (25, -110.00, '2022-01-08 15:56:00', 3, 'SalesInvoice', -1.00, 2, 1, 4);
insert into stock_of_goods values (26, -420.00, '2022-01-08 15:56:00', 3, 'SalesInvoice', -2.00, 2, 1, 1);
insert into stock_of_goods values (27, -510.00, '2022-01-08 15:56:00', 3, 'SalesInvoice', -3.00, 2, 1, 3);

insert into sales_income values (6, 110.00, '2022-01-08 15:56:00', 320.00, 1.00, 430.00, 2, 4, 3);
insert into sales_income values (7, 420.00, '2022-01-08 15:56:00', 370.00, 2.00, 790.00, 2, 1, 3);
insert into sales_income values (8, 510.00, '2022-01-08 15:56:00', 585.00, 3.00, 1095.00, 2, 3, 3);
insert into sales_income values (9, 0.00, '2022-01-08 15:56:00', 1350.00, 1.00, 1350.00, 2, 7, 3);

insert into sales_invoices values (4, '2022-01-07 15:56:00', false, '2022-01-07 15:56:00', '2022-01-07 15:56:00', 1, 2, 3, 2, 1);
insert into sales_invoices_goods values (10, 435.0000, 1.00, 435.00, 3, 4);
insert into sales_invoices_goods values (11, 590.0000, 1.00, 590.00, 5, 4);
insert into sales_invoices_goods values (12, 470.0000, 1.00, 470.00, 2, 4);

insert into stock_of_goods values (28, -170.00, '2022-01-07 15:56:00', 4, 'SalesInvoice', -1.00, 2, 1, 3);
insert into stock_of_goods values (29, -150.00, '2022-01-07 15:56:00', 4, 'SalesInvoice', -1.00, 2, 1, 5);
insert into stock_of_goods values (30, -195.00, '2022-01-07 15:56:00', 4, 'SalesInvoice', -1.00, 2, 1, 2);

insert into sales_income values (10, 170.00, '2022-01-07 15:56:00', 265.00, 1.00, 435.00, 2, 3, 4);
insert into sales_income values (11, 150.00, '2022-01-07 15:56:00', 440.00, 1.00, 590.00, 2, 5, 4);
insert into sales_income values (12, 195.00, '2022-01-07 15:56:00', 275.00, 1.00, 470.00, 2, 2, 4);

insert into sales_invoices values (5, '2022-01-13 15:57:00', false, '2022-01-13 15:57:00', '2022-01-13 15:57:00', 2, 2, 4, 2, 2);
insert into sales_invoices_goods values (13, 365.0000, 1.00, 365.00, 3, 5);
insert into sales_invoices_goods values (14, 430.0000, 3.00, 1290.00, 4, 5);
insert into sales_invoices_goods values (15, 365.0000, 5.00, 1825.00, 3, 5);

insert into stock_of_goods values (31, -170.00, '2022-01-13 15:57:00', 5, 'SalesInvoice', -1.00, 2, 1, 3);
insert into stock_of_goods values (32, -330.00, '2022-01-13 15:57:00', 5, 'SalesInvoice', -3.00, 2, 1, 4);
insert into stock_of_goods values (33, -850.00, '2022-01-13 15:57:00', 5, 'SalesInvoice', -5.00, 2, 1, 3);

insert into sales_income values (13, 170.00, '2022-01-13 15:57:00', 195.00, 1.00, 365.00, 2, 3, 5);
insert into sales_income values (14, 330.00, '2022-01-13 15:57:00', 960.00, 3.00, 1290.00, 2, 4, 5);
insert into sales_income values (15, 850.00, '2022-01-13 15:57:00', 975.00, 5.00, 1825.00, 2, 3, 5);

insert into sales_invoices values (6, '2022-01-07 19:00:00', false, '2022-01-07 19:00:00', '2022-01-07 19:00:00', 13, 13, 11, 2, 1);
insert into sales_invoices_goods values (16, 490.0000, 1.00, 490.00, 5, 6);
insert into sales_invoices_goods values (17, 515.0000, 1.00, 515.00, 4, 6);
insert into sales_invoices_goods values (18, 435.0000, 1.00, 435.00, 3, 6);
insert into sales_invoices_goods values (19, 470.0000, 1.00, 470.00, 2, 6);
insert into sales_invoices_goods values (20, 450.0000, 1.00, 450.00, 1, 6);
insert into sales_invoices_goods values (21, 750.0000, 1.00, 750.00, 6, 6);
insert into sales_invoices_goods values (22, 750.0000, 1.00, 750.00, 8, 6);

insert into stock_of_goods values (34, -195.00, '2022-01-07 19:00:00', 6, 'SalesInvoice', -1.00, 13, 3, 5);
insert into stock_of_goods values (35, -230.00, '2022-01-07 19:00:00', 6, 'SalesInvoice', -1.00, 13, 3, 4);
insert into stock_of_goods values (36, -170.00, '2022-01-07 19:00:00', 6, 'SalesInvoice', -1.00, 13, 3, 3);
insert into stock_of_goods values (37, -235.00, '2022-01-07 19:00:00', 6, 'SalesInvoice', -1.00, 13, 3, 2);
insert into stock_of_goods values (38, -310.00, '2022-01-07 19:00:00', 6, 'SalesInvoice', -1.00, 13, 3, 1);

insert into sales_income values (16, 195.00, '2022-01-07 19:00:00', 295.00, 1.00, 490.00, 13, 5, 6);
insert into sales_income values (17, 230.00, '2022-01-07 19:00:00', 285.00, 1.00, 515.00, 13, 4, 6);
insert into sales_income values (18, 170.00, '2022-01-07 19:00:00', 265.00, 1.00, 435.00, 13, 3, 6);
insert into sales_income values (19, 235.00, '2022-01-07 19:00:00', 235.00, 1.00, 470.00, 13, 2, 6);
insert into sales_income values (20, 310.00, '2022-01-07 19:00:00', 140.00, 1.00, 450.00, 13, 1, 6);
insert into sales_income values (21, 0.00, '2022-01-07 19:00:00', 750.00, 1.00, 750.00, 13, 6, 6);
insert into sales_income values (22, 0.00, '2022-01-07 19:00:00', 750.00, 1.00, 750.00, 13, 8, 6);

insert into sales_invoices values (7, '2022-01-08 16:02:00', false, '2022-01-08 16:02:00', '2022-01-08 16:02:00', 14, 13, 13, 2, 1);
insert into sales_invoices_goods values (23, 515.0000, 5.00, 2575.00, 4, 7);

insert into stock_of_goods values (39, -1150.00, '2022-01-08 16:02:00', 7, 'SalesInvoice', -5.00, 13, 3, 4);

insert into sales_income values (23, 1150.00, '2022-01-08 16:02:00', 1425.00, 5.00, 2575.00, 13, 4, 7);

insert into sales_invoices values (8, '2022-01-09 16:02:00', false, '2022-01-09 16:02:00', '2022-01-09 16:02:00', 15, 13, 14, 2, 1);
insert into sales_invoices_goods values (24, 450.0000, 11.00, 4950.00, 1, 8);

insert into stock_of_goods values (40, -3410.00, '2022-01-09 16:02:00', 8, 'SalesInvoice', -11.00, 13, 3, 1);

insert into sales_income values (24, 3410.00, '2022-01-09 16:02:00', 1540.00, 11.00, 4950.00, 13, 1, 8);

insert into sales_invoices values (9, '2022-01-10 16:02:00', false, '2022-01-10 16:02:00', '2022-01-10 16:02:00', 13, 13, 11, 2, 1);
insert into sales_invoices_goods values (25, 435.0000, 13.00, 5655.00, 3, 9);

insert into stock_of_goods values (41, -2210.00, '2022-01-10 16:02:00', 9, 'SalesInvoice', -13.00, 13, 3, 3);

insert into sales_income values (25, 2210.00, '2022-01-10 16:02:00', 3445.00, 13.00, 5655.00, 13, 3, 9);

insert into sales_invoices values (10, '2022-01-13 16:03:00', false, '2022-01-13 16:03:00', '2022-01-13 16:03:00', 14, 13, 13, 2, 1);
insert into sales_invoices_goods values (26, 435.0000, 3.00, 1305.00, 3, 10);
insert into sales_invoices_goods values (27, 590.0000, 2.00, 1180.00, 5, 10);

insert into stock_of_goods values (42, -170.00, '2022-01-13 16:03:00', 10, 'SalesInvoice', -1.00, 13, 3, 3);
insert into stock_of_goods values (43, -630.00, '2022-01-13 16:03:00', 10, 'SalesInvoice', -2.00, 13, 4, 3);
insert into stock_of_goods values (44, -390.00, '2022-01-13 16:03:00', 10, 'SalesInvoice', -2.00, 13, 3, 5);

insert into sales_income values (26, 800.00, '2022-01-13 16:03:00', 505.00, 3.00, 1305.00, 13, 3, 10);
insert into sales_income values (27, 390.00, '2022-01-13 16:03:00', 790.00, 2.00, 1180.00, 13, 5, 10);

insert into users values ('PERSONAL', 1, '2021-12-28 17:08:57', false, '2021-12-28 17:08:57', 'admin@gmail.com', true, '$2a$10$z/1zdFEnrRaqCZ94yUp6nO1fRU0597GPoEME6YenxGrZR4uWsC.Te', 'ROLE_ADMIN', '1993-04-28 00:00:00', 'Admin', 'Admin');
insert into users values ('PERSONAL', 2, '2021-12-28 17:08:57', false, '2021-12-28 17:08:57', 'sales_manager@gmail.com', true, '$2a$10$z/1zdFEnrRaqCZ94yUp6nO1fRU0597GPoEME6YenxGrZR4uWsC.Te', 'ROLE_SALES_MANAGER', '1993-04-28 00:00:00', 'Sales', 'Manager');
insert into users values ('PERSONAL', 3, '2021-12-28 17:08:57', false, '2021-12-28 17:08:57', 'purchusing_manager@gmail.com', true, '$2a$10$z/1zdFEnrRaqCZ94yUp6nO1fRU0597GPoEME6YenxGrZR4uWsC.Te', 'ROLE_PURCHASING_MANAGER', '1993-04-28 00:00:00', 'Purchusing', 'Manager');