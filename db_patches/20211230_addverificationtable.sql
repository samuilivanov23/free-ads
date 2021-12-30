CREATE TABLE IF NOT EXISTS verifications_email(
	"id" bigserial PRIMARY KEY,
	"inserted_at" timestamp NOT NULL DEFAULT NOW(),
	"user_id" bigserial,
	"token" text
);
