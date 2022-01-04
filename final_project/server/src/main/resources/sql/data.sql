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