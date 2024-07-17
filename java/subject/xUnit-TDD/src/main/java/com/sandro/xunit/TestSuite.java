package com.sandro.xunit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestSuite implements Test {
    private final List<Test> tests = new ArrayList<>();

    public TestSuite(Class<? extends TestCase> testClass) {
        Arrays.stream(testClass.getMethods())
                .filter(m -> m.getAnnotation(com.sandro.xunit.annotation.Test.class) != null)
                .forEach(m -> {
                    try {
                        add(testClass.getConstructor(String.class).newInstance(m.getName()));
                    } catch (Exception e) {throw new RuntimeException(e);}
                });
    }

    public TestSuite() {}

    public void add(Test test) {
        tests.add(test);
    }

    @Override
    public void run(TestResult result) {
        tests.forEach(t -> t.run(result));
    }
}
