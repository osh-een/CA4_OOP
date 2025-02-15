DROP DATABASE IF EXISTS `IncomeExpense_Database`;

CREATE DATABASE `IncomeExpense_Database`;

USE `IncomeExpense_Database`;

DROP TABLE IF EXISTS `expense`;

CREATE TABLE `expense` (
  `EXPENSE_ID` INT(9) NOT NULL AUTO_INCREMENT,
  `TITLE` VARCHAR(50) NOT NULL,
  `CATEGORY` VARCHAR(50) NOT NULL,
  `AMOUNT` DECIMAL(10, 2) NOT NULL,
  `DATEINCURRED` DATE NOT NULL,
  PRIMARY KEY (`EXPENSE_ID`)
);

DROP TABLE IF EXISTS `income`;

CREATE TABLE `income` (
  `INCOME_ID` INT(9) NOT NULL AUTO_INCREMENT,
  `TITLE` VARCHAR(50) NOT NULL,
  `AMOUNT` DECIMAL(10, 2) NOT NULL,
  `DATEEARNED` DATE NOT NULL,
  PRIMARY KEY (`INCOME_ID`)
);

INSERT INTO
  `expense` (`TITLE`, `CATEGORY`, `AMOUNT`, `DATEINCURRED`)
VALUES
  ('weekly shop', 'groceries', 47.50, '2025-01-07'),
  ('gym membership', 'fitness', 30.00, '2025-01-09'),
  ('rent payment', 'housing', 500.00, '2025-01-01'),
  (
    'electricity bill',
    'utilities',
    80.75,
    '2025-01-10'
  ),
  ('dining out', 'food', 35.25, '2025-01-12');

INSERT INTO
  `income` (`TITLE`, `AMOUNT`, `DATEEARNED`)
VALUES
  ('babysitting', 60.00, '2025-01-05'),
  ('Bar work', 100.00, '2025-01-07'),
  ('freelance design', 150.00, '2025-01-10'),
  ('tutoring', 75.00, '2025-01-11'),
  ('online surveys', 20.00, '2025-01-13');