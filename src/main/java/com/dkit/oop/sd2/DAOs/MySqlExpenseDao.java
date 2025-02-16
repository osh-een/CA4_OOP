package com.dkit.oop.sd2.DAOs;

import com.dkit.oop.sd2.AllValidation.Validation;

/**
 * OOP Feb 2024
 * <p>
 * Data Access Object (DAO) for Expense table with MySQL-specific code
 * This 'concrete' class implements the 'UserDaoInterface'.
 * <p>
 * The DAO will contain the SQL query code to interact with the database,
 * so, the code here is specific to a MySql database.
 * No SQL queries will be used in the Business logic layer of code, thus, it
 * will be independent of the database specifics. Changes to code related to
 * the database are all contained withing the DAO code base.
 * <p>
 * <p>
 * The Business Logic layer is only permitted to access the database by calling
 * methods provided in the Data Access Layer - i.e. by calling the DAO methods.
 * In this way, the Business Logic layer is seperated from the database specific code
 * in the DAO layer.
 */

//  REFERENCES:
//  https://neon.tech/postgresql/postgresql-jdbc/delete
// https://www.tutorialspoint.com/jdbc/jdbc-insert-records.htm#:~:text=Once%20connection%20is%20prepared%2C%20we,executeUpdate()%20method.

import com.dkit.oop.sd2.DTOs.Expense;
import com.dkit.oop.sd2.Exceptions.DaoException;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.dkit.oop.sd2.BusinessObjects.AppMain.kb;

public class MySqlExpenseDao extends MySqlDao implements ExpenseDaoInterface {
    /**
     * Will access and return a List of all users in Expense database table
     * 
     * @return List of Expense objects
     * @throws DaoException
     */

    @Override
    public List<Expense> findAllExpenses() throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Expense> ExpensesList = new ArrayList<>();

        try {
            // Get connection object using the getConnection() method inherited
            // from the super class (MySqlDao.java)
            connection = this.getConnection();

            String query = "SELECT * FROM EXPENSE";
            preparedStatement = connection.prepareStatement(query);

            // Using a PreparedStatement to execute SQL...
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int expenseID = resultSet.getInt("EXPENSE_ID");
                String title = resultSet.getString("TITLE");
                String category = resultSet.getString("CATEGORY");
                double amount = resultSet.getDouble("AMOUNT");
                Date date = resultSet.getDate("DATEINCURRED");
                ExpensesList.add(new Expense(expenseID, title, category, amount, date));
            }
        } catch (SQLException e) {
            throw new DaoException("findAllExpensesesultSet() " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("findAllExpenses() " + e.getMessage());
            }
        }
        return ExpensesList; // may be empty
    }

    @Override
    public List<Double> findAllExpensesAndCalcutateSpend() throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Double> ExpensesList = new ArrayList<>();
        double totalSpend = 0;

        try {
            // Get connection object using the getConnection() method inherited
            // from the super class (MySqlDao.java)
            connection = this.getConnection();

            String query = "SELECT * FROM EXPENSE";
            preparedStatement = connection.prepareStatement(query);

            // Using a PreparedStatement to execute SQL...
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                totalSpend += resultSet.getDouble("AMOUNT");
                ExpensesList.add(resultSet.getDouble("AMOUNT"));
            }
            ExpensesList.add(totalSpend);
        } catch (SQLException e) {
            throw new DaoException("findAllExpensesAndCalcutateSpendResultSet() " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("findAllExpensesAndCalcutateSpend() " + e.getMessage());
            }
        }
        return ExpensesList; // may be empty
    }

    @Override
    public void addExpense() throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Get connection object using the getConnection() method inherited
            // from the super class (MySqlDao.java)
            connection = this.getConnection();

            String query = "INSERT INTO Expense (Title, Category, Amount, DateIncurred) VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);

            System.out.print("Enter expense Title: ");
            String Title = kb.nextLine();

            System.out.print("Enter expense Category: ");
            String Category = kb.nextLine();

            double Amount = Validation.validateDoubleInput("Enter expense Amount: ", 0);

            Date date = Validation.validateDateInput("Enter expense Date (YYYY-MM-DD): ");

            preparedStatement.setString(1, Title);
            preparedStatement.setString(2, Category);
            preparedStatement.setDouble(3, Amount);
            preparedStatement.setDate(4, date);

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Expense added successfully!");
            } else {
                System.out.println("Expense not added!");
            }
        } catch (SQLException e) {
            throw new DaoException("addExpenseResultSet() " + e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("addExpense() " + e.getMessage());
            }
        }
    }

    @Override
    public void deleteExpense() throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Get connection object using the getConnection() method inherited
            // from the super class (MySqlDao.java)
            connection = this.getConnection();

            String query = "DELETE FROM Expense WHERE Expense_ID = ?";
            preparedStatement = connection.prepareStatement(query);

            int expenseID = Validation.validateIntInput("Enter expense ID: ", 0);

            preparedStatement.setInt(1, expenseID);

            int rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Expense deleted successfully!");
            } else {
                System.out.println("Expense not found!");
            }

        } catch (SQLException e) {
            throw new DaoException("deleteExpenseResultSet() " + e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("deleteExpense() " + e.getMessage());
            }
        }
    }

    @Override
    public List<Double> findMonthlyExpense(int month, int year) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Double> ExpensesList = new ArrayList<>();
        double totalSpend = 0;

        try {
            // Get connection object using the getConnection() method inherited
            // from the super class (MySqlDao.java)
            connection = this.getConnection();

            String query = "SELECT * FROM EXPENSE WHERE MONTH(dateIncurred) = ? AND YEAR(dateIncurred) = ?";

            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, month);
            preparedStatement.setInt(2, year);

            // Using a PreparedStatement to execute SQL...
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                totalSpend += resultSet.getDouble("AMOUNT");
                ExpensesList.add(resultSet.getDouble("AMOUNT"));
            }
            ExpensesList.add(totalSpend);
        } catch (SQLException e) {
            throw new DaoException("findMonthlyExpenseResultSet() " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("findMonthlyExpense() " + e.getMessage());
            }
        }
        return ExpensesList; // may be empty
    }
}
