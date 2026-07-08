package com.petstore.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TestListener - Custom TestNG listener for test lifecycle events
 * Logs test execution details and handles setup/teardown
 */
public class TestListener implements ITestListener {
    
    private static final Logger logger = LoggerFactory.getLogger(TestListener.class);
    
    @Override
    public void onStart(ITestContext context) {
        logger.info("========================================");
        logger.info("Test Suite Started: {}", context.getName());
        logger.info("Test Methods: {}", context.getAllTestMethods().length);
        logger.info("========================================");
    }
    
    @Override
    public void onFinish(ITestContext context) {
        logger.info("========================================");
        logger.info("Test Suite Finished: {}", context.getName());
        logger.info("Passed Tests: {}", context.getPassedTests().size());
        logger.info("Failed Tests: {}", context.getFailedTests().size());
        logger.info("Skipped Tests: {}", context.getSkippedTests().size());
        logger.info("========================================");
    }
    
    @Override
    public void onTestStart(ITestResult result) {
        logger.info("--------- TEST START ---------");
        logger.info("Test Name: {}", result.getMethod().getMethodName());
        logger.info("Test Class: {}", result.getTestClass().getName());
        logger.info("Description: {}", result.getMethod().getDescription());
    }
    
    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("✓ TEST PASSED: {}", result.getMethod().getMethodName());
        logger.info("Execution Time: {} ms", result.getEndMillis() - result.getStartMillis());
        logger.info("--------- TEST END ---------\n");
    }
    
    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("✗ TEST FAILED: {}", result.getMethod().getMethodName());
        logger.error("Failure Cause: {}", result.getThrowable().getMessage());
        logger.error("Execution Time: {} ms", result.getEndMillis() - result.getStartMillis());
        logger.error("--------- TEST END ---------\n");
    }
    
    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("⊘ TEST SKIPPED: {}", result.getMethod().getMethodName());
        if (result.getThrowable() != null) {
            logger.warn("Skip Reason: {}", result.getThrowable().getMessage());
        }
        logger.warn("--------- TEST END ---------\n");
    }
    
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        logger.info("TEST FAILED WITHIN SUCCESS PERCENTAGE: {}", result.getMethod().getMethodName());
    }
}
