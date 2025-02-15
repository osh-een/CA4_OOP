package com.dkit.oop.sd2.BusinessObjects;

/** OOP Jan 2025
 * This AppMain demonstrates the use of a Data Access Layer
 * to separate Business logic from Database specific logic.
 * It uses:
 * Data Access Objects (DAOs) to implement the logic required to access a database.
 * Data Transfer Objects (DTOs), to transfer data between layers, and a
 * DAO Interface to define a 'contract' between Business Objects and DAOs.
 *
 * "Use a Data Access Object (DAO) to abstract and encapsulate all
 * access to the data source. The DAO manages the connection with
 * the data source to obtain and store data" Ref: oracle.com
 *
 * Here, we use one DAO per database table.
 *
 * Use the SQL script "CreateUsers.sql" included with this project
 * to create the required MySQL user_database and User table.
 */

import com.dkit.oop.sd2.DAOs.MySqlExpenseDao;
import com.dkit.oop.sd2.DAOs.MySqlIncomeDao;
import com.dkit.oop.sd2.DAOs.ExpenseDaoInterface;
import com.dkit.oop.sd2.DAOs.IncomeDaoInterface;
import com.dkit.oop.sd2.DTOs.Expense;
import com.dkit.oop.sd2.DTOs.Income;
import com.dkit.oop.sd2.Exceptions.DaoException;
import java.util.List;

public class AppMain {
    public static void main(String[] args) {
        ExpenseDaoInterface IExpenseDao = new MySqlExpenseDao(); // "IUserDao" -> "I" stands for Interface
        IncomeDaoInterface IIncomeDao = new MySqlIncomeDao(); // "IUserDao" -> "I" stands for Interface

        /// Notice that the userDao reference is an Interface type.
        /// This allows for the use of different concrete implementations.
        /// e.g. we could replace the MySqlUserDao with an OracleUserDao
        /// (accessing an Oracle Database)
        /// without changing anything in the Interface.
        /// If the Interface doesn't change, then none of the
        /// code in this file that uses the interface needs to change.
        /// This code is dependent of the Interface but not on the implementation
        /// of the interface.
        /// The 'contract' defined by the interface will not be broken.
        /// This means that this code is 'independent' of the code
        /// used to access the database. (Reduced coupling).

        /// The Business Objects require that all User DAOs implement
        /// the interface called "UserDaoInterface", as the code uses
        /// only references of the interface type to access the DAO methods.

        try {
            // System.out.println("\nCall findAllExpensesAndCalcutateSpend()");
            // List<Expense> ExpensesList = IExpenseDao.findAllExpenses(); // call a method
            // in the DAO

            // if (ExpensesList.isEmpty())
            // System.out.println("There are no Expenses");
            // else {
            // for (Expense expense : ExpensesList) {
            // System.out.println(expense);
            // }
            // }

            // System.out.println("\nCall findAllExpensesAndCalcutateSpend()");
            // List<Double> Expenses = IExpenseDao.findAllExpensesAndCalcutateSpend(); //
            // call a method in the DAO

            // if (Expenses.isEmpty())
            // System.out.println("There are no Expenses");
            // else {
            // for (int i = 0; i < Expenses.size(); i++) {
            // if (i == Expenses.size() - 1) {
            // System.out.println("Total Spend: " + Expenses.get(i));
            // } else {
            // System.out.println("Expense " + (i + 1) + ": " + Expenses.get(i));
            // }

            // }
            // }

            // System.out.println("\nCall deleteExpense()");
            // IExpenseDao.deleteExpense(); // call a method in the DAO

            // System.out.println("\nCall addExpense()");
            // IExpenseDao.addExpense(); // call a method in the DAO

            System.out.println("\nCall findAllExAndCalcutateSpend()");
            List<Income> IncomesList = IIncomeDao.findAllIncomes(); // call a method in the DAO

            if (IncomesList.isEmpty())
                System.out.println("There are no Expenses");
            else {
                for (Income income : IncomesList) {
                    System.out.println(income);
                }
            }

            System.out.println("\nCall findAllExpensesAndCalcutateSpend()");
            List<Double> Incomes = IIncomeDao.findAllIncomesAndCalcutateGain(); // call a method in the DAO

            if (Incomes.isEmpty())
                System.out.println("There are no Incomes");
            else {
                for (int i = 0; i < Incomes.size(); i++) {
                    if (i == Incomes.size() - 1) {
                        System.out.println("Total Spend: " + Incomes.get(i));
                    } else {
                        System.out.println("Expense " + (i + 1) + ": " + Incomes.get(i));
                    }

                }
            }

            System.out.println("\nCall deleteIncome()");
            IIncomeDao.deleteIncome(); // call a method in the DAO

            System.out.println("\nCall addIncome()");
            IIncomeDao.addIncome(); // call a method in the DAO

            // test dao with a username and password that we know are present in the
            // database
            // (Use phpMyAdmin to check that the database has a row with this data)
            // System.out.println("\nCall: findUserByUsernamePassword()");
            // String username = "smithj";
            // String password = "password";

            // User user = IExpenseDao.findUserByUsernamePassword(username, password);

            // if (user != null) // null returned if userid and password not valid
            // System.out.println("User found: " + user);
            // else
            // System.out.println("Username with that password not found");

            // // test dao - with an invalid username (i.e. row not in database)
            // username = "madmax";
            // password = "thunderdome";

            // user = IUserDao.findUserByUsernamePassword(username, password);

            // if (user != null)
            // System.out.println("Username: " + username + " was found: " + user);
            // else
            // System.out.println("Username: " + username + ", password: " + password + " :
            // NO match found");
        } catch (DaoException e) {
            /// This code is executed when the DAO layer throws an exception.
            /// We might place some logic here to deal with the issue, but in this case,
            /// we simply print out the exception error message to the console.
            e.printStackTrace();
        }
    }
}
