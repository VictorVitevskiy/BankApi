CREATE TABLE Accounts (id IDENTITY PRIMARY KEY, account VARCHAR(21), balance DECIMAL(12, 3), customerId BIGINT);
CREATE TABLE Bank_Cards (id IDENTITY PRIMARY KEY, cardNumber VARCHAR(20), cvc2cvv2 INT, accountId BIGINT, customerId BIGINT);
CREATE TABLE Customers (id IDENTITY PRIMARY KEY, fullName VARCHAR(255), phoneNumber VARCHAR(20),email VARCHAR(255), passportSeriesNumber BIGINT);