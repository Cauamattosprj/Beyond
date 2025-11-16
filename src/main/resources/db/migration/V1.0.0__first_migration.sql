-- USERS
CREATE TABLE IF NOT EXISTS "users" (
    "id" UUID NOT NULL DEFAULT RANDOM_UUID(),
    "name" VARCHAR(255) NOT NULL,
    "email" VARCHAR(255) NOT NULL UNIQUE,
    "password_hash" VARCHAR(255) NOT NULL,
    PRIMARY KEY("id")
);

CREATE INDEX "users_email_idx"
ON "users" ("email");

-- POINTS BALANCE
CREATE TABLE IF NOT EXISTS "points_balance" (
    "user_id" UUID NOT NULL,
    "points" INTEGER NOT NULL DEFAULT 0,
    PRIMARY KEY("user_id")
);

-- REWARDABLE ACTIVITIES
CREATE TABLE IF NOT EXISTS "rewardable_activities" (
    "id" UUID NOT NULL DEFAULT RANDOM_UUID(),
    "name" VARCHAR(255) NOT NULL,
    "description" VARCHAR(255) NOT NULL,
    "reward_points" INTEGER NOT NULL,
    "is_default" BOOLEAN NOT NULL,
    "user_id" UUID,
    "period_id" UUID NOT NULL,
    PRIMARY KEY("id")
);

CREATE INDEX "rewardable_activities_index_0"
ON "rewardable_activities" ("user_id", "period_id");

-- REWARD ACTIVITIES
CREATE TABLE IF NOT EXISTS "reward_activities" (
    "id" UUID NOT NULL DEFAULT RANDOM_UUID(),
    "name" VARCHAR(255) NOT NULL,
    "description" VARCHAR(255) NOT NULL,
    "required_points" INTEGER NOT NULL,
    "is_default" BOOLEAN NOT NULL UNIQUE,
    "user_id" UUID,
    "period_id" UUID NOT NULL,
    PRIMARY KEY("id")
);

CREATE INDEX "reward_activities_index_0"
ON "reward_activities" ("user_id", "period_id");

-- POINTS TRANSACTIONS
CREATE TABLE IF NOT EXISTS "points_transactions" (
    "id" UUID NOT NULL DEFAULT RANDOM_UUID(),
    "reward_activity_id" UUID NOT NULL,
    "rewardable_activity_id" UUID,
    "user_id" UUID NOT NULL,
    "created_at" TIMESTAMP NOT NULL,
    "deleted_at" TIMESTAMP,
    "is_deleted" BOOLEAN NOT NULL DEFAULT FALSE,
    "period_id" UUID NOT NULL,
    "period_amount" INTEGER NOT NULL,
    "points_delta" INTEGER NOT NULL,
    "activity_type_id" UUID NOT NULL,
    PRIMARY KEY("id")
);

CREATE INDEX "points_transactions_user_idx"
ON "points_transactions" ("user_id");

CREATE INDEX "points_transactions_period_idx"
ON "points_transactions" ("period_id");

CREATE INDEX "points_transactions_index_0"
ON "points_transactions" ("activity_type_id");

CREATE INDEX "points_transactions_index_1"
ON "points_transactions" ("rewardable_activity_id");

-- PERIODS
CREATE TABLE IF NOT EXISTS "periods" (
    "id" UUID NOT NULL DEFAULT RANDOM_UUID(),
    "period" VARCHAR(255) NOT NULL,
    PRIMARY KEY("id")
);

-- ACTIVITIES TYPES
CREATE TABLE IF NOT EXISTS "activities_types" (
    "id" UUID NOT NULL DEFAULT RANDOM_UUID(),
    "type" VARCHAR(255) NOT NULL UNIQUE,
    PRIMARY KEY("id")
);


-- FOREIGN KEYS
ALTER TABLE "points_balance"
    ADD FOREIGN KEY("user_id") REFERENCES "users"("id");

ALTER TABLE "reward_activities"
    ADD FOREIGN KEY("user_id") REFERENCES "users"("id");

ALTER TABLE "rewardable_activities"
    ADD FOREIGN KEY("user_id") REFERENCES "users"("id");

ALTER TABLE "reward_activities"
    ADD FOREIGN KEY("period_id") REFERENCES "periods"("id");

ALTER TABLE "rewardable_activities"
    ADD FOREIGN KEY("period_id") REFERENCES "periods"("id");

ALTER TABLE "points_transactions"
    ADD FOREIGN KEY("period_id") REFERENCES "periods"("id");

ALTER TABLE "points_transactions"
    ADD FOREIGN KEY("activity_type_id") REFERENCES "activities_types"("id");

ALTER TABLE "points_transactions"
    ADD FOREIGN KEY("user_id") REFERENCES "users"("id");

ALTER TABLE "points_transactions"
    ADD FOREIGN KEY("reward_activity_id") REFERENCES "reward_activities"("id");

ALTER TABLE "points_transactions"
    ADD FOREIGN KEY("rewardable_activity_id") REFERENCES "rewardable_activities"("id");
