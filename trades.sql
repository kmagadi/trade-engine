-- Creating Database "trading"
CREATE DATABASE trading;

SELECT current_database();

-- Creating "trades" table
CREATE TABLE trades (
    trade_id BIGINT PRIMARY KEY,
    account_id VARCHAR(50),
    symbol VARCHAR(50),
    quantity INT,
    price DOUBLE PRECISION,
    side VARCHAR(10),
    trade_timestamp TIMESTAMP
);	

--

CREATE TABLE trades (trade_id BIGINT PRIMARY KEY,account_id VARCHAR(50),symbol VARCHAR(50),quantity INT,price DOUBLE PRECISION,side VARCHAR(10), trade_timestamp TIMESTAMP );	

SELECT * FROM trades;