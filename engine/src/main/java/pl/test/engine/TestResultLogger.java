package pl.test.engine;

/**
 * @author Makiela Wojciech
 */
class TestResultLogger {

    static void logTestResult(Test test, int invocationNumber) {
        String methodName = getTestedMethodNameWithNumber(test, invocationNumber);
        switch (test.result) {
            case PASSED:
                logTestPassed(methodName, invocationNumber);
                break;
            case ASSERTION_FAILED:
                logAssertionFailed(methodName);
                break;
            case EXCEPTION_MISMATCH:
                logExceptionMismatch(test, methodName);
                break;
            case UNEXPECTED_EXCEPTION:
                logUnexpectedException(test, methodName);
        }
    }

    private static String getTestedMethodNameWithNumber(Test test, int invocationNumber) {
        return invocationNumber == 1 ? test.method.getName() : String.format("%s[%d]", test.method.getName(), invocationNumber);
    }

    private static void logUnexpectedException(Test test, String methodName) {
        String msg = "Unexpected exception in test %s (%s -> %s)\n";
        System.err.printf(msg, methodName, test.actualException.getClass().toString(), test.actualException.getMessage());
    }

    private static void logExceptionMismatch(Test test, String methodName) {
        String msg = "Exception mismatch in test %s.\n" +
                "Expected: %s\n" +
                "     got: %s\n";
        String actualAsString = test.actualException == null ? null : test.actualException.getClass().toString();
        System.err.printf(msg, methodName, test.expectedException.toString(), actualAsString);
    }

    private static void logAssertionFailed(String methodName) {
        String msg = "Assertion in test %s failed.\n";
        System.err.printf(msg, methodName);
    }

    private static void logTestPassed(String methodName, int invocationNumber) {
        String msg = "Test %s passed\n";
        System.out.printf(msg, methodName, invocationNumber);
    }
}
