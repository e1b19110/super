INSERT INTO shop (shop_name) VALUES ('A');
INSERT INTO shop (shop_name) VALUES ('B');
INSERT INTO shop (shop_name) VALUES ('C');
INSERT INTO items (item_name,price) VALUES ('apple',300);
INSERT INTO items (item_name,price) VALUES ('banana',200);
INSERT INTO items (item_name,price) VALUES ('grape',500);
INSERT INTO items (item_name,price) VALUES ('orange',100);

INSERT INTO stock (shop_id,item_id,number) VALUES (1,1,10);
INSERT INTO stock (shop_id,item_id,number) VALUES (1,2,20);
INSERT INTO stock (shop_id,item_id,number) VALUES (1,3,40);
INSERT INTO stock (shop_id,item_id,number) VALUES (1,4,40);

INSERT INTO stock (shop_id,item_id,number) VALUES (2,1,10);
INSERT INTO stock (shop_id,item_id,number) VALUES (2,3,20);
INSERT INTO stock (shop_id,item_id,number) VALUES (2,4,50);

INSERT INTO stock (shop_id,item_id,number) VALUES (3,2,10);
INSERT INTO stock (shop_id,item_id,number) VALUES (3,3,10);
INSERT INTO stock (shop_id,item_id,number) VALUES (3,4,10);
