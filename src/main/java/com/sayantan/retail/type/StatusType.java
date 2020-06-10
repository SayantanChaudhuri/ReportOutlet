package com.sayantan.retail.type;

public enum StatusType {

    SUCCESS("Success"), FAIL("Fail");

    private String type = null;

    StatusType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
