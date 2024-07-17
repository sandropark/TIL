package com.sandro.xunit;

import java.lang.reflect.Method;

public abstract class TestCase implements Test {
    protected final String name;

    protected TestCase(String name) {
        this.name = name;
    }

    @Override
    public void run(TestResult result) {
        result.testStarted();
        setUp();

        try {
            Method method = getClass().getMethod(name);
            method.invoke(this);
        } catch (Exception e) {
            result.testFailed();
        }

        tearDown();
    }

    protected void setUp() {}

    protected void tearDown() {}
}
