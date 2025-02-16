package com.dkit.oop.sd2;

import com.dkit.oop.sd2.DAOs.ExpenseDaoInterface;
import com.dkit.oop.sd2.DAOs.IncomeDaoInterface;
import com.dkit.oop.sd2.Exceptions.DaoException;

import java.util.List;
import java.util.Scanner;

public class MonthlyNetIncome {
    private ExpenseDaoInterface expenseDao;
    private IncomeDaoInterface incomeDao;

    static Scanner kb = new Scanner(System.in);

    public MonthlyNetIncome(ExpenseDaoInterface iExpenseDao, IncomeDaoInterface iIncomeDao) {
        this.expenseDao = iExpenseDao;
        this.incomeDao = iIncomeDao;
    }

    public double calculateNetIncome() throws DaoException {
        System.out.println("Enter month: ");
        int month = kb.nextInt();

        System.out.println("Enter year: ");
        int year = kb.nextInt();

        double totalIncome = displayAndGetIncomes(incomeDao.findMonthlyIncome(month, year));
        double totalExpense = displayAndGetExpenses(expenseDao.findMonthlyExpense(month, year));

        return totalIncome - totalExpense;
    }

    public double displayAndGetIncomes(List<Double> incomes) throws DaoException {
        System.out.println("\nIncomes: ");
        for (int i = 0; i < incomes.size(); i++) {
            if (i == incomes.size() - 1) {
                System.out.println("\nTotal: " + incomes.get(i));
                return incomes.get(i);
            } else {
                System.out.print(incomes.get(i) + ", ");
            }
        }
        return -1;
    }

    public double displayAndGetExpenses(List<Double> expenses) throws DaoException {
        System.out.println("\nExpenses: ");
        for (int i = 0; i < expenses.size(); i++) {
            if (i == expenses.size() - 1) {
                System.out.println("\nTotal: " + expenses.get(i));
                return expenses.get(i);
            } else {
                System.out.print(expenses.get(i) + ",");
            }
        }
        return -1;
    }
}