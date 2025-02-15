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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        List<Expense> usersList = new ArrayList<>();

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
                usersList.add(new Expense(expenseID, title, category, amount, date));
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
        return usersList; // may be empty
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

        Scanner kb = new Scanner(System.in);

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

            System.out.print("Enter expense Amount: ");
            double Amount = kb.nextDouble();
            kb.nextLine();

            System.out.print("Enter expense Date (YYYY-MM-DD): ");
            String date = kb.nextLine();

            Date expenseDate = Date.valueOf(date);

            preparedStatement.setString(1, Title);
            preparedStatement.setString(2, Category);
            preparedStatement.setDouble(3, Amount);
            preparedStatement.setDate(4, expenseDate);

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Expense added successfully!");
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

        Scanner kb = new Scanner(System.in);

        try {
            // Get connection object using the getConnection() method inherited
            // from the super class (MySqlDao.java)
            connection = this.getConnection();

            String query = "DELETE FROM Expense WHERE Expense_ID = ?";
            preparedStatement = connection.prepareStatement(query);

            System.out.print("Enter expense ID: ");
            int expenseID = kb.nextInt();

            preparedStatement.setInt(1, expenseID);

            int rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Expense deleted successfully!");
            }

        } catch (SQLException e) {
            throw new DaoException("findAllUseresultSet() " + e.getMessage());
        } finally {
            try {
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
