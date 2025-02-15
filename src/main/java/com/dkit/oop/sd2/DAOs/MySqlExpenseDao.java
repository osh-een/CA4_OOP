package com.dkit.oop.sd2.DAOs;

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

import com.dkit.oop.sd2.DTOs.Expense;
import com.dkit.oop.sd2.Exceptions.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlExpenseDao extends MySqlDao implements ExpenseDaoInterface {
    /**
     * Will access and return a List of all users in Expense database table
     * 
     * @return List of Expense objects
     * @throws DaoException
     */
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
    public void addExpense(Expense expense) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Expense> ExpensesList = new ArrayList<>();

        try {
            // Get connection object using the getConnection() method inherited
            // from the super class (MySqlDao.java)
            connection = this.getConnection();

            String query = "SELECT * FROM USER";
            preparedStatement = connection.prepareStatement(query);

            // Using a PreparedStatement to execute SQL...
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ExpensesList.add(new Expense(
                        resultSet.getInt("EXPENSE_ID"),
                        resultSet.getString("TITLE"),
                        resultSet.getString("CATEGORY"),
                        resultSet.getDouble("AMOUNT"),
                        resultSet.getDate("DATE_INCURRED")));
            }
        } catch (SQLException e) {
            throw new DaoException("findAllUseresultSet() " + e.getMessage());
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
                throw new DaoException("findAllUsers() " + e.getMessage());
            }
        }
    }

    @Override
    public void deleteExpense(int id) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Expense> ExpensesList = new ArrayList<>();

        try {
            // Get connection object using the getConnection() method inherited
            // from the super class (MySqlDao.java)
            connection = this.getConnection();

            String query = "SELECT * FROM USER";
            preparedStatement = connection.prepareStatement(query);

            // Using a PreparedStatement to execute SQL...
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ExpensesList.add(new Expense(
                        resultSet.getInt("EXPENSE_ID"),
                        resultSet.getString("TITLE"),
                        resultSet.getString("CATEGORY"),
                        resultSet.getDouble("AMOUNT"),
                        resultSet.getDate("DATE_INCURRED")));
            }
        } catch (SQLException e) {
            throw new DaoException("findAllUseresultSet() " + e.getMessage());
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
                throw new DaoException("findAllUsers() " + e.getMessage());
            }
        }
    }
}
