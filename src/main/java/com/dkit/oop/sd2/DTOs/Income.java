package com.dkit.oop.sd2.DTOs;

import java.sql.Date;

/**
 * OOP Feb 2022
 * Data Transfer Object (DTO)
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

public class Income {
    private int incomeID;
    private String title;
    private double amount;
    private Date dateEarned;

    public Income() {
    }

    public Income(int incomeID, String title, double amount, Date dateEarned) {
        this.incomeID = incomeID;
        this.title = title;
        this.amount = amount;
        this.dateEarned = dateEarned;
    }

    public int getIncomeID() {
        return incomeID;
    }

    public void setIncomeID(int incomeID) {
        this.incomeID = incomeID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDateEarned() {
        return dateEarned;
    }

    public void setDateEarned(Date dateEarned) {
        this.dateEarned = dateEarned;
    }

    @Override
    public String toString() {
        return "Income{" +
                "incomeID=" + incomeID +
                ", title='" + title + '\'' +
                ", amount=" + amount +
                ", dateEarned='" + dateEarned + '\'' +
                '}';
    }
}