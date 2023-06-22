package com.solvd.cucumbercarina.database.domain;

public class UserOrder {

    private Long id;

    private String orderItems;

    private boolean flagCompleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public boolean isFlagCompleted() {
        return flagCompleted;
    }

    public void setFlagCompleted(boolean flagCompleted) {
        this.flagCompleted = flagCompleted;
    }

    public String getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(String orderItems) {
        this.orderItems = orderItems;
    }



}
