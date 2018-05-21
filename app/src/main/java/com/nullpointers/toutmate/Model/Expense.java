package com.nullpointers.toutmate.Model;

public class Expense {
    private String expenseName;
    private String expenseAmount;
    private String comment;
    private long date;
    private long time;

    public Expense(String expenseName, String expenseAmount, String comment, long date, long time) {
        this.expenseName = expenseName;
        this.expenseAmount = expenseAmount;
        this.comment = comment;
        this.date = date;
        this.time = time;
    }

    public Expense() {
        //required for Firebase database
    }

    public String getExpenseName() {
        return expenseName;
    }

    public String getExpenseAmount() {
        return expenseAmount;
    }

    public String getComment() {
        return comment;
    }

    public long getDate() {
        return date;
    }

    public long getTime() {
        return time;
    }
}
