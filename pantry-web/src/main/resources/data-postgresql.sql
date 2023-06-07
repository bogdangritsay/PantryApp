
insert into stores(id, name, link_to_store) values(1, 'Amazon', 'https://amazon.com');
insert into stores(id, name, link_to_store) values(2, 'Rozetka', 'https://rozetka.com.ua/');
insert into stores(id, name, link_to_store) values(3, 'Prom', 'https://prom.ua/ua/');
insert into stores(id, name, link_to_store) values(4, 'Wastons', 'https://www.watsons.ua/');
insert into stores(id, name, link_to_store) values(5, 'Eva', 'https://eva.ua/ua/');

commit;

insert into category(id, name , description, parent_id) values (1, 'Товары для дома',null, null);
insert into category(id, name , description, parent_id) values (2, 'Бытовая химия',null, 1);
insert into category(id, name , description, parent_id) values (3, 'Средства для мытья посуды',null, 2);
insert into category(id, name , description, parent_id) values (4, 'Средства для посудомоечных машин',null, 3);
insert into category(id, name , description, parent_id) values (5, 'Средства для посудомоечный машин Finish',null, 4);
insert into category(id, name , description, parent_id) values (6, 'Стиральные средства',null, 2);
insert into category(id, name , description, parent_id) values (7, 'Хозяйственный инвентарь',null, 1);
insert into category(id, name , description, parent_id) values (8, 'Хозяйственные товары',null, 7);


commit;

insert into products(id, name, item_price,link_to_product, store_id, category_id) values (1, 'Таблетки для посудомоечных машин FINISH Quantum Ultimate 40 шт (5900627090307)',543.0,'https://rozetka.com.ua/finish_5900627090307/p172709776/', 2, 5);
insert into products(id, name, item_price,link_to_product, store_id, category_id) values (2, 'Ополаскиватель для посудомоечных машин FINISH 800 мл (4607109403556_5900627063493)',138.0,'https://rozetka.com.ua/finish_4607109403556/p6449416/', 2, 5);
insert into products(id, name, item_price,link_to_product, store_id, category_id) values (3, 'Соль для посудомоечных машин FINISH 4 кг (8594002687397)',168.0,'https://rozetka.com.ua/finish_8594002687397/p6449968/', 2, 5);
insert into products(id, name, item_price,link_to_product, store_id, category_id) values (4, 'Гель для стирки Persil 5 л (9000101323047)',539.0,'https://rozetka.com.ua/persil_universal_9000101323047/p39991152/', 2, 6);
insert into products(id, name, item_price,link_to_product, store_id, category_id) values (5, 'Стиральный порошок Ariel Color 9 кг (5413149462014)',359.0,'https://rozetka.com.ua/ariel_5413149462014/p5713041/', 2, 6);
insert into products(id, name, item_price,link_to_product, store_id, category_id) values (6, 'Бумажные полотенца Ruta Soft & Strong 87 отрывов 3 слоя 8 рулонов Белые (4820202891079_1)',115.0,'https://rozetka.com.ua/ruta_4820202891079_1/p69093176/', 2, 8);

commit;

