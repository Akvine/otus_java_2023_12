package ru.otus;

import com.google.common.base.Preconditions;

public class HelloOtus {
    public static void main(String[] args) {
        String value = "some value";
        Preconditions.checkNotNull(value, "value is null");
    }
}
