ALTER TABLE users DROP CONSTRAINT users_cart_id_fkey;
ALTER TABLE users DROP COLUMN cart_id;

ALTER TABLE carts_items DROP CONSTRAINT carts_items_cart_id_fkey;
ALTER TABLE carts_items RENAME COLUMN cart_id TO user_id;
ALTER TABLE carts_items ADD FOREIGN KEY (user_id) REFERENCES users(id);

DROP TABLE carts;
