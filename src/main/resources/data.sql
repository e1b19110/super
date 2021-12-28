INSERT INTO shop (shop_name) VALUES ('スーパー玉田');
INSERT INTO shop (shop_name) VALUES ('難波屋');
INSERT INTO shop (shop_name) VALUES ('九条市場');
INSERT INTO shop (shop_name) VALUES ('中野店');
INSERT INTO shop (shop_name) VALUES ('大村1号店');
INSERT INTO shop (shop_name) VALUES ('大村2号店');
INSERT INTO shop (shop_name) VALUES ('馬場屋南店');
INSERT INTO shop (shop_name) VALUES ('馬場屋西店');



INSERT INTO items (item_name,price) VALUES ('リンゴ',300);
INSERT INTO items (item_name,price) VALUES ('バナナ',200);
INSERT INTO items (item_name,price) VALUES ('ブドウ',500);
INSERT INTO items (item_name,price) VALUES ('みかん',100);
INSERT INTO items (item_name,price) VALUES ('白菜',130);
INSERT INTO items (item_name,price) VALUES ('梨',250);
INSERT INTO items (item_name,price) VALUES ('桃',700);
INSERT INTO items (item_name,price) VALUES ('キュウリ',200);
INSERT INTO items (item_name,price) VALUES ('落花生',400);
INSERT INTO items (item_name,price) VALUES ('えんどう豆',180);
INSERT INTO items (item_name,price) VALUES ('ネギ',370);
INSERT INTO items (item_name,price) VALUES ('タマネギ',300);
INSERT INTO items (item_name,price) VALUES ('じゃがいも',200);
INSERT INTO items (item_name,price) VALUES ('鯖',890);
INSERT INTO items (item_name,price) VALUES ('鯛',2000);
INSERT INTO items (item_name,price) VALUES ('牛肉300グラム',1300);



INSERT INTO stock (shop_id,item_id,number) VALUES (1,1,10);
INSERT INTO stock (shop_id,item_id,number) VALUES (1,2,20);
INSERT INTO stock (shop_id,item_id,number) VALUES (1,3,40);
INSERT INTO stock (shop_id,item_id,number) VALUES (1,4,40);
INSERT INTO stock (shop_id,item_id,number) VALUES (1,5,17);
INSERT INTO stock (shop_id,item_id,number) VALUES (1,7,17);
INSERT INTO stock (shop_id,item_id,number) VALUES (1,15,17);

INSERT INTO stock (shop_id,item_id,number) VALUES (2,1,10);
INSERT INTO stock (shop_id,item_id,number) VALUES (2,3,20);
INSERT INTO stock (shop_id,item_id,number) VALUES (2,4,50);
INSERT INTO stock (shop_id,item_id,number) VALUES (2,7,17);

INSERT INTO stock (shop_id,item_id,number) VALUES (3,2,10);
INSERT INTO stock (shop_id,item_id,number) VALUES (3,3,10);
INSERT INTO stock (shop_id,item_id,number) VALUES (3,4,10);
INSERT INTO stock (shop_id,item_id,number) VALUES (3,8,17);

INSERT INTO stock (shop_id,item_id,number) VALUES (4,5,17);
INSERT INTO stock (shop_id,item_id,number) VALUES (4,7,17);
INSERT INTO stock (shop_id,item_id,number) VALUES (4,15,17);

INSERT INTO stock (shop_id,item_id,number) VALUES (5,9,17);
INSERT INTO stock (shop_id,item_id,number) VALUES (5,10,17);

INSERT INTO stock (shop_id,item_id,number) VALUES (6,10,17);
INSERT INTO stock (shop_id,item_id,number) VALUES (6,12,17);
INSERT INTO stock (shop_id,item_id,number) VALUES (6,14,17);

INSERT INTO stock (shop_id,item_id,number) VALUES (7,6,17);
INSERT INTO stock (shop_id,item_id,number) VALUES (7,13,17);

INSERT INTO stock (shop_id,item_id,number) VALUES (8,4,17);
INSERT INTO stock (shop_id,item_id,number) VALUES (8,8,17);
INSERT INTO stock (shop_id,item_id,number) VALUES (8,9,17);

INSERT INTO users (user_name,shop_id) VALUES ('いがき',1);
INSERT INTO users (user_name,shop_id) VALUES ('ふくやす',2);
INSERT INTO users (user_name,shop_id) VALUES ('user1',3);
INSERT INTO users (user_name,shop_id) VALUES ('user2',4);
INSERT INTO users (user_name,shop_id) VALUES ('いば',5);
INSERT INTO users (user_name,shop_id) VALUES ('どい',6);
INSERT INTO users (user_name,shop_id) VALUES ('たばた',7);
INSERT INTO users (user_name,shop_id) VALUES ('よしだ',8);

INSERT INTO logs (user_id,item_id,send_shop_id,recv_shop_id,number,msg) VALUES (1,1,1,2,5,'test');
