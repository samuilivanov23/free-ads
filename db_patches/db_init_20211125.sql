CREATE TABLE IF NOT EXISTS users(
	"id" bigserial PRIMARY KEY,
	"inserted_at" timestamp NOT NULL DEFAULT NOW(),
	"first_name" text,
	"last_name" text,
	"username" text,
	"email_address" text,
	"password" text,
	"salt" text,
	"authenticated" boolean DEFAULT FALSE,
	"cart_id" bigserial UNIQUE
);

CREATE TABLE IF NOT EXISTS items(
	"id" bigserial PRIMARY KEY,
	"inserted_at" timestamp NOT NULL DEFAULT NOW(),
	"name" text,
	"description" text,
	"salesman_user_id" bigserial,
	"category_id" bigserial,
	"count" int,
	"price" numeric,
	"image_name" text,
	"is_deleted" boolean DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS carts(
	"id" bigserial PRIMARY KEY,
	"inserted_at" timestamp NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS categories(
	"id" bigserial PRIMARY KEY,
	"inserted_at" timestamp NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS orders(
	"id" bigserial PRIMARY KEY,
	"inserted_at" timestamp NOT NULL DEFAULT NOW(),
	"total_price" numeric,
	"user_id" bigserial,
	"is_deleted" boolean DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS carts_items(
	"cart_id" bigserial,
	"item_id" bigserial,
	"count" int
);

CREATE TABLE IF NOT EXISTS items_categories(
	"item_id" bigserial,
	"category_id" bigserial
);

CREATE TABLE IF NOT EXISTS orders_items(
	"order_id" bigserial,
	"item_id" bigserial,
	"count" int
);

CREATE TABLE IF NOT EXISTS roles(
	"id" bigserial PRIMARY KEY,
	"inserted_at" timestamp NOT NULL DEFAULT NOW(),
	"name" text
);

ALTER TABLE users ADD FOREIGN KEY (cart_id) REFERENCES carts (id);

ALTER TABLE items ADD FOREIGN KEY (salesman_user_id) REFERENCES users (id);
ALTER TABLE items ADD FOREIGN KEY (category_id) REFERENCES categories (id);

ALTER TABLE orders ADD FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE carts_items ADD CONSTRAINT pk_carts_items PRIMARY KEY (cart_id, item_id);
ALTER TABLE carts_items ADD FOREIGN KEY (cart_id) REFERENCES carts (id);
ALTER TABLE carts_items ADD FOREIGN KEY (item_id) REFERENCES items (id);

ALTER TABLE items_categories ADD FOREIGN KEY (item_id) REFERENCES items (id);
ALTER TABLE items_categories ADD FOREIGN KEY (category_id) REFERENCES categories (id);

ALTER TABLE orders_items ADD CONSTRAINT pk_orders_items PRIMARY KEY (order_id, item_id);
ALTER TABLE orders_items ADD FOREIGN KEY (order_id) REFERENCES orders (id);
ALTER TABLE orders_items ADD FOREIGN KEY (item_id) REFERENCES items (id);
