package com.mocktpo.config;

import java.util.List;

public class TestsHomeConfig {

    private List<TestStatus> tests;

    public List<TestStatus> getTests() {
        return tests;
    }

    public void setTests(List<TestStatus> tests) {
        this.tests = tests;
    }

    @Override
    public String toString() {
        return "{" + "tests:" + this.getTests() + "}";
    }
}
