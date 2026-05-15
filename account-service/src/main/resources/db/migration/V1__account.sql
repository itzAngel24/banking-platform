CREATE TABLE IF NOT EXISTS account (
  id UUID PRIMARY KEY,
  customer_id UUID NOT NULL,
  account_number VARCHAR(24) NOT NULL UNIQUE,
  account_type VARCHAR(20) NOT NULL,
  currency CHAR(3) NOT NULL,
  balance NUMERIC(18,2) NOT NULL DEFAULT 0,
  status VARCHAR(20) NOT NULL,
  created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);
