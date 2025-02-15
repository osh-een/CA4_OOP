package com.dkit.oop.sd2.DTOs;

public class Expense {
    private int expenseID;
    private String title;
    private String category;
    private double amount;
    private String dateIncurred;

    public Expense() {
    }

    public Expense(int expenseID, String title, String category, double amount, String dateIncurred) {
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

    public String getdateIncurred() {
        return dateIncurred;
    }

    public void setdateIncurred(String dateIncurred) {
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