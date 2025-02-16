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
import com.dkit.oop.sd2.MonthlyNetIncome;
import java.util.List;
import java.util.Scanner;

public class AppMain {
    public static void main(String[] args) {
        ExpenseDaoInterface IExpenseDao = new MySqlExpenseDao(); // "IUserDao" -> "I" stands for Interface
        IncomeDaoInterface IIncomeDao = new MySqlIncomeDao(); // "IUserDao" -> "I" stands for Interface
        int input = 0;
        List<Expense> ExpensesList = null;
        List<Income> IncomesList = null;

        String[] choices = { "1. Add Expense", "2. Add Income", "3. Delete Expense", "4. Delete Income",
                "5. View All Expenses", "6. View All Incomes", "7. Calculate Net Income", "0. Exit" };

        Scanner kb = new Scanner(System.in);

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
            do {
                for (String choice : choices) {
                    System.out.println(choice);
                }
                input = kb.nextInt();

                switch (input) {
                    case 1:
                        IExpenseDao.addExpense();
                        break;
                    case 2:
                        IIncomeDao.addIncome();
                        break;
                    case 3:
                        IExpenseDao.deleteExpense();
                        break;
                    case 4:
                        IIncomeDao.deleteIncome();
                        break;
                    case 5:
                        ExpensesList = IExpenseDao.findAllExpenses();
                        for (Expense expense : ExpensesList) {
                            System.out.println(expense);
                        }
                        break;
                    case 6:
                        IncomesList = (IIncomeDao.findAllIncomes());
                        for (Income income : IncomesList) {
                            System.out.println(income);
                        }
                        break;
                    case 7:
                        MonthlyNetIncome month = new MonthlyNetIncome(IExpenseDao, IIncomeDao);
                        double netIncome = month.calculateNetIncome();
                        System.out.println("\nNet income for the month: " + netIncome + "\n");
                        break;
                    case 0:
                        break;
                }
            } while (input != 0);

        } catch (DaoException e) {
            /// This code is executed when the DAO layer throws an exception.
            /// We might place some logic here to deal with the issue, but in this case,
            /// we simply print out the exception error message to the console.
            e.printStackTrace();
        }
    }
}
