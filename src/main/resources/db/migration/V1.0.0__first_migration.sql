-- USERS
CREATE TABLE IF NOT EXISTS users (
    id UUID NOT NULL DEFAULT RANDOM_UUID(),
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    PRIMARY KEY(id)
);

CREATE INDEX users_email_idx
ON users (email);

-- POINTS BALANCE
CREATE TABLE IF NOT EXISTS points_balance (
    user_id UUID NOT NULL,
    points INTEGER NOT NULL DEFAULT 0,
    PRIMARY KEY(user_id),
    FOREIGN KEY(user_id) REFERENCES users(id)
);

-- ACTIVITIES TYPES (EARNING / SPENDING)
CREATE TABLE IF NOT EXISTS activities_types (
    id UUID NOT NULL DEFAULT RANDOM_UUID(),
    type VARCHAR(255) NOT NULL UNIQUE,
    PRIMARY KEY(id)
);

-- PERIODS
CREATE TABLE IF NOT EXISTS periods (
    id UUID NOT NULL DEFAULT RANDOM_UUID(),
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY(id)
);

-- UNIFIED ACTIVITIES TABLE
CREATE TABLE IF NOT EXISTS activities (
    id UUID NOT NULL DEFAULT RANDOM_UUID(),
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    points_value INTEGER NOT NULL,
    is_default BOOLEAN NOT NULL,
    user_id UUID,
    period_id UUID NOT NULL,
    activity_type_id UUID NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(user_id) REFERENCES users(id),
    FOREIGN KEY(period_id) REFERENCES periods(id),
    FOREIGN KEY(activity_type_id) REFERENCES activities_types(id)
);

CREATE INDEX activities_index_0
ON activities (user_id, period_id, activity_type_id);

-- POINTS TRANSACTIONS
CREATE TABLE IF NOT EXISTS points_transactions (
    id UUID NOT NULL DEFAULT RANDOM_UUID(),
    activity_id UUID NOT NULL,
    user_id UUID NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT current_timestamp,
    deleted_at TIMESTAMP,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    period_id UUID NOT NULL,
    period_amount INTEGER NOT NULL,
    points_delta INTEGER NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(activity_id) REFERENCES activities(id),
    FOREIGN KEY(user_id) REFERENCES users(id),
    FOREIGN KEY(period_id) REFERENCES periods(id)
);

CREATE INDEX points_transactions_user_idx
ON points_transactions (user_id);

CREATE INDEX points_transactions_period_idx
ON points_transactions (period_id);
