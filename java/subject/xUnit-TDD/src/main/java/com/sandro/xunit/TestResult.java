package com.sandro.xunit;

public class TestResult {
    private int runCount;
    private int failCount;

    public String getSummary() {
        return runCount + " run, " + failCount + " failed";
    }

    public void testStarted() {
        runCount++;
    }

    public void testFailed() {
        failCount++;
    }
}
