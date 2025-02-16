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

import com.dkit.oop.sd2.DTOs.Income;
import com.dkit.oop.sd2.Exceptions.DaoException;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MySqlIncomeDao extends MySqlDao implements IncomeDaoInterface {
    /**
     * Will access and return a List of all users in Expense database table
     * 
     * @return List of Expense objects
     * @throws DaoException
     */

    static Scanner kb = new Scanner(System.in);

    @Override
    public List<Income> findAllIncomes() throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Income> IncomesList = new ArrayList<>();

        try {
            // Get connection object using the getConnection() method inherited
            // from the super class (MySqlDao.java)
            connection = this.getConnection();

            String query = "SELECT * FROM INCOME";
            preparedStatement = connection.prepareStatement(query);

            // Using a PreparedStatement to execute SQL...
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int incomeID = resultSet.getInt("INCOME_ID");
                String title = resultSet.getString("TITLE");
                double amount = resultSet.getDouble("AMOUNT");
                Date date = resultSet.getDate("DATEEARNED");
                IncomesList.add(new Income(incomeID, title, amount, date));
            }
        } catch (SQLException e) {
            throw new DaoException("findAllIncomesResultSet() " + e.getMessage());
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
                throw new DaoException("findAllIncomes() " + e.getMessage());
            }
        }
        return IncomesList; // may be empty
    }

    @Override
    public List<Double> findAllIncomesAndCalcutateGain() throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Double> IncomesList = new ArrayList<>();
        double totalGain = 0;

        try {
            // Get connection object using the getConnection() method inherited
            // from the super class (MySqlDao.java)
            connection = this.getConnection();

            String query = "SELECT * FROM INCOME";
            preparedStatement = connection.prepareStatement(query);

            // Using a PreparedStatement to execute SQL...
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                totalGain += resultSet.getDouble("AMOUNT");
                IncomesList.add(resultSet.getDouble("AMOUNT"));
            }
            IncomesList.add(totalGain);
        } catch (SQLException e) {
            throw new DaoException("findAllIncomesAndCalcutateGainResultSet() " + e.getMessage());
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
                throw new DaoException("findAllIncomesAndCalcutateGain() " + e.getMessage());
            }
        }
        return IncomesList; // may be empty
    }

    @Override
    public void addIncome() throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Get connection object using the getConnection() method inherited
            // from the super class (MySqlDao.java)
            connection = this.getConnection();

            String query = "INSERT INTO Income (Title, Amount, DateEarned) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);

            System.out.print("Enter income Title: ");
            String Title = kb.nextLine();

            System.out.print("Enter income Amount: ");
            double Amount = kb.nextDouble();
            kb.nextLine();

            System.out.print("Enter income Date (YYYY-MM-DD): ");
            String date = kb.nextLine();

            Date incomeDate = Date.valueOf(date);

            preparedStatement.setString(1, Title);
            preparedStatement.setDouble(2, Amount);
            preparedStatement.setDate(3, incomeDate);

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Income added successfully!");
            }
        } catch (SQLException e) {
            throw new DaoException("addIncomeResultSet() " + e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("addIncome() " + e.getMessage());
            }
        }
    }

    @Override
    public void deleteIncome() throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Get connection object using the getConnection() method inherited
            // from the super class (MySqlDao.java)
            connection = this.getConnection();

            String query = "DELETE FROM Income WHERE Income_ID = ?";
            preparedStatement = connection.prepareStatement(query);

            System.out.print("Enter Income ID: ");
            int IncomeID = kb.nextInt();

            preparedStatement.setInt(1, IncomeID);

            int rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Expense deleted successfully!");
            }

        } catch (SQLException e) {
            throw new DaoException("deleteIncomeResultSet() " + e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("deleteIncome() " + e.getMessage());
            }
        }
    }

    @Override
    public List<Double> findMonthlyIncome(int month, int year) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Double> IncomesList = new ArrayList<>();
        double totalGain = 0;

        try {
            // Get connection object using the getConnection() method inherited
            // from the super class (MySqlDao.java)
            connection = this.getConnection();

            String query = "SELECT * FROM INCOME WHERE MONTH(DateEarned) = ? AND YEAR(DateEarned) = ?";

            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, month);
            preparedStatement.setInt(2, year);

            // Using a PreparedStatement to execute SQL...
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                totalGain += resultSet.getDouble("AMOUNT");
                IncomesList.add(resultSet.getDouble("AMOUNT"));
            }
            IncomesList.add(totalGain);
        } catch (SQLException e) {
            throw new DaoException("findMonthlyIncomeResultSet() " + e.getMessage());
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
                throw new DaoException("findMonthlyIncome() " + e.getMessage());
            }
        }
        return IncomesList; // may be empty
    }
}
