package com.dkit.oop.sd2.DTOs;

import java.sql.Date;

/**                                                     OOP Feb 2022
 *  Data Transfer Object (DTO)
 *
 * This POJO (Plain Old Java Object) is called the Data Transfer Object (DTO).
 * (or, alternatively, the Model Object or the Value Object).
 * It is used to transfer data between the DAO and the Business Objects.
 * Here, it represents a row of data from the User database table.
 * The DAO creates and populates a User object (DTO) with data retrieved from
 * the resultSet and passes the User object to the Business Layer.
 *
 * Collections of DTOs( e.g. ArrayList<User> ) may also be passed
 * between the Data Access Layer (DAOs) and the Business Layer objects.
 */

public class Expense {
    private int expenseID;
    private String title;
    private String category;
    private double amount;
    private Date dateIncurred;

    public Expense() {
    }

    public Expense(int expenseID, String title, String category, double amount, Date dateIncurred) {
        this.expenseID = expenseID;
        this.title = title;
        this.category = category;
        this.amount = amount;
        this.dateIncurred = dateIncurred;
    }

    public int getexpenseID() {
        return expenseID;
    }

    public void setexpenseID(int expenseID) {
        this.expenseID = expenseID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getdateIncurred() {
        return dateIncurred;
    }

    public void setdateIncurred(Date dateIncurred) {
        this.dateIncurred = dateIncurred;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "expenseID=" + expenseID +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", amount=" + amount +
                ", dateIncurred='" + dateIncurred + '\'' +
                '}';
    }
}