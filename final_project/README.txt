Цель приложения вести учет остатков и прибыли от операционной деятельности.
Документ Purchase invoice предназначен для покупки товара у поставщиков.
При создании происходит фиксация остатка и себестоимости товара.

Документ Sales invoice предназначен для продажи товара покупателям.
При создании происходит списание остатка товара и себестоисомти по методу FIFO.
Так же расчитываеться прибыль от сделки.
При продаже услуг остатки не списываються.

Учет остатков ведеться в разрезе компаний и товарных позицый.

Реализована фиксация цен на товары и услуги в резрезе видов цен на конкретную дату.
В Sales invoice и Purchase invoice реализован механизм пересчета цены по валюте.
Прибыль и себестоимость товаров ведеться в валюте, которая указана в application.properties.

---------------
Таблица в MySql: web_crud;
Создание таблиц происходит при запуске;
Для заполнения базы нужно использовать скрипт: data.sql;
---------------
Пользователи:
логин: admin@gmail.com
пароль: rootroot

логин: sales_manager@gmail.com
пароль: rootroot
