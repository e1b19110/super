CREATE TABLE shop (
  shop_id INT IDENTITY,
  shop_name CHAR NOT NULL
);
CREATE TABLE items (
  item_id INT IDENTITY,
  item_name CHAR NOT NULL,
  price INT NOT NULL
);
CREATE TABLE stock (
  shop_id INT references shop(shop_id),
  item_id INT references items(item_id),
  number INT NOT NULL,
  primary key(shop_id,item_id)
);