insert into users values (1, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'test@gmail.com', 'Oliver', 'Williams', '380971112233');
insert into users values (2, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'test1@gmail.com', 'Jack', 'Gibson', '380971112234');
insert into users values (3, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'test2@gmail.com', 'Harry', 'Jordan', '380971112235');

insert into bank_accounts values (1, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'UA213223130000026007233566001', 'salary account', 1);
insert into bank_accounts values (2, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'UA213223130000026007233577001', 'common account', 1);
insert into bank_accounts values (3, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'UA213223130000026007233566002', 'salary account', 2);
insert into bank_accounts values (4, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'UA213223130000026007233577002', 'common account', 2);
insert into bank_accounts values (5, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'UA213223130000026007233566003', 'salary account', 3);
insert into bank_accounts values (6, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 'UA213223130000026007233577003', 'common account', 3);

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

insert into bank_operations values (1, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 5000, 'INCOME_SALARY', 'INCOME', 1, 10, null);
insert into bank_operations values (2, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 7000.00, 'INCOME_SALARY', 'INCOME', 1, 12, null);
insert into bank_operations values (3, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 500.75, 'INCOME_OTHER', 'INCOME', 1, 11, null);
insert into bank_operations values (4, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), -1200.00, 'OUTCOME_PAYMENT', 'EXPENSE', 1, 1, null);
insert into bank_operations values (5, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), -120.35, 'OUTCOME_OTHER', 'EXPENSE', 1, 5, null);
insert into bank_operations values (6, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), -2300.00, 'OUTCOME_CREDIT', 'EXPENSE', 1, 8, null);
insert into bank_operations values (7, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 500.75, 'INCOME_OTHER', 'INCOME', 1, 11, null);

insert into bank_operations values (8, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 1000, 'INCOME_SALARY', 'INCOME', 2, 10, null);
insert into bank_operations values (9, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 3200.00, 'INCOME_SALARY', 'INCOME', 2, 12, null);
insert into bank_operations values (10, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 700.75, 'INCOME_OTHER', 'INCOME', 2, 11, null);
insert into bank_operations values (11, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), -900.00, 'OUTCOME_PAYMENT', 'EXPENSE', 2, 1, null);
insert into bank_operations values (12, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), -220.35, 'OUTCOME_OTHER', 'EXPENSE', 2, 5, null);
insert into bank_operations values (13, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), -1350.00, 'OUTCOME_CREDIT', 'EXPENSE', 2, 8, null);
insert into bank_operations values (14, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 890.75, 'INCOME_OTHER', 'INCOME', 2, 11, null);

insert into bank_operations values (15, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 5000, 'INCOME_SALARY', 'INCOME', 3, 10, null);
insert into bank_operations values (16, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 7000.00, 'INCOME_SALARY', 'INCOME', 3, 12, null);
insert into bank_operations values (17, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 500.75, 'INCOME_OTHER', 'INCOME', 3, 11, null);
insert into bank_operations values (18, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), -1200.00, 'OUTCOME_PAYMENT', 'EXPENSE', 3, 1, null);
insert into bank_operations values (19, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), -120.35, 'OUTCOME_OTHER', 'EXPENSE', 3, 5, null);
insert into bank_operations values (20, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), -2300.00, 'OUTCOME_CREDIT', 'EXPENSE', 3, 8, null);
insert into bank_operations values (21, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 500.75, 'INCOME_OTHER', 'INCOME', 3, 11, null);

insert into bank_operations values (22, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 5000, 'INCOME_SALARY', 'INCOME', 5, 10, null);
insert into bank_operations values (23, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 7000.00, 'INCOME_SALARY', 'INCOME', 5, 12, null);
insert into bank_operations values (24, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 500.75, 'INCOME_OTHER', 'INCOME', 5, 11, null);
insert into bank_operations values (25, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), -1200.00, 'OUTCOME_PAYMENT', 'EXPENSE', 5, 1, null);
insert into bank_operations values (26, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), -120.35, 'OUTCOME_OTHER', 'EXPENSE', 5, 5, null);
insert into bank_operations values (27, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), -2300.00, 'OUTCOME_CREDIT', 'EXPENSE', 5, 8, null);
insert into bank_operations values (28, CURRENT_TIMESTAMP(), false, CURRENT_TIMESTAMP(), 500.75, 'INCOME_OTHER', 'INCOME', 5, 11, null);