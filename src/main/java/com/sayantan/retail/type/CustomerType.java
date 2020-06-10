package com.sayantan.retail.type;

public enum CustomerType {

    REGULAR("regular"), PREMIUM("premium");

    private String category = null;

    CustomerType(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return category;
    }
}
