CREATE TABLE IF NOT EXISTS userfavourites_items(
	"user_id" bigserial,
	"item_id" bigserial
);

ALTER TABLE userfavourites_items ADD FOREIGN KEY (user_id) REFERENCES users(id);
ALTER TABLE userfavourites_items ADD FOREIGN KEY (item_id) REFERENCES items(id);
