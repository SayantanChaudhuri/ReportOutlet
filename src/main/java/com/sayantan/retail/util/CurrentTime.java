package com.sayantan.retail.util;

import java.sql.Timestamp;

public class CurrentTime {

    public static Timestamp now() {
        return new Timestamp(System.currentTimeMillis());
    }
}
