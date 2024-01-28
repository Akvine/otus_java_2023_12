package tests;

import annotations.After;
import annotations.Before;
import annotations.Test;

public class TestClass {

    @Before
    public void beforeMethod1() {
        System.out.println("Execute some prepare logic 1");
    }

    @Before
    public void beforeMethod2() {
        System.out.println("Execute some prepare logic 2");
    }
    @Test
    public void testMethod1() {
        int a = 5;
        int b = 4;

        Assert.assertEquals(a, b);
    }

    @Test
    public void testMethod2() {
        int a = 5;
        int b = 5;

        Assert.assertEquals(a, b);
    }

    @Test
    public void testMethod3() {
        String string = null;
        string.toLowerCase();
    }

    @Test
    public void testMethod4() {
        String value = "Dasd";
        String value2 = "Das";
        Assert.assertEquals(value2, value);
    }

    @After
    public void afterMethod1() {
        System.out.println("Execute some after logic 1");
    }

    @After
    public void afterMethod2() {
        System.out.println("Execute some after logic 2");
    }
}
