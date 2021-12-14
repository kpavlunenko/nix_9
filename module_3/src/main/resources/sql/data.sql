insert into users values (1, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'test@gmail.com', 'Oliver', 'Williams', '380971112233');
insert into users values (2, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'test1@gmail.com', 'Jack', 'Gibson', '380971112234');
insert into users values (3, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'test2@gmail.com', 'Harry', 'Jordan', '380971112235');

insert into bank_accounts values (1, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), '213223130000026007233566001', 'salary account', 1);
insert into bank_accounts values (2, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'UA213223130000026007233577001', 'credit account', 1);
insert into bank_accounts values (3, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'UA213223130000026007233566002', 'salary account', 2);
insert into bank_accounts values (4, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'UA213223130000026007233577002', 'credit account', 2);
insert into bank_accounts values (5, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'UA213223130000026007233566003', 'salary account', 3);
insert into bank_accounts values (6, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'UA213223130000026007233577003', 'credit account', 3);

insert into categories values (1, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'OUTCOME_PAYMENT', 'payment for goods');
insert into categories values (2, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'OUTCOME_PAYMENT', 'payment for services');
insert into categories values (3, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'OUTCOME_PAYMENT', 'utility bills');
insert into categories values (4, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'OUTCOME_PAYMENT', 'payment for meals');
insert into categories values (5, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'OUTCOME_OTHER', 'commission of the bank');
insert into categories values (6, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'OUTCOME_TRANSFER', 'transfer between your accounts');
insert into categories values (7, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'OUTCOME_TRANSFER', 'transfer to another account');
insert into categories values (8, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'OUTCOME_CREDIT', 'repayment of a loan for a product');

insert into categories values (9, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'INCOME_TRANSFER', 'transfer from another account');
insert into categories values (10, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'INCOME_SALARY', 'receiving an advance on wages');
insert into categories values (11, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'INCOME_OTHER', 'receiving interest on a deposit');
insert into categories values (12, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'INCOME_SALARY', 'income salary');
insert into categories values (13, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'INCOME_OTHER', 'other income');