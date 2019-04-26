package pl.test.engine;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Makiela Wojciech
 */
class Test {

    TestResult result;
    Throwable actualException;
    final Class expectedException;
    final Method method;
    private final Object testClassInstance;

    Test(Object testClassInstance, Method method, Class expectedException) {
        result = TestResult.PASSED;
        this.testClassInstance = testClassInstance;
        this.method = method;
        this.expectedException = expectedException;
        actualException = null;
    }

    void run() throws IllegalAccessException {
        if (exceptionsAreNotExpected(expectedException)) {
            runAssertionTest();
        } else {
            runExpectedExceptionTest();
        }
    }

    private void runAssertionTest() throws IllegalAccessException {
        try {
            method.invoke(testClassInstance);
        } catch (InvocationTargetException e) {
            if (assertionFailed(e)) {
                result = TestResult.ASSERTION_FAILED;
            } else {
                result = TestResult.UNEXPECTED_EXCEPTION;
                actualException = e.getTargetException();
            }
        }
    }

    private boolean assertionFailed(InvocationTargetException e) {
        return e.getTargetException() instanceof AssertionError;
    }

    private void runExpectedExceptionTest() throws IllegalAccessException {
        try {
            method.invoke(testClassInstance);
            result = TestResult.EXCEPTION_MISMATCH;
        } catch (InvocationTargetException e) {
            if (exceptionsMismatch(e)) {
                result = TestResult.EXCEPTION_MISMATCH;
                actualException = e.getTargetException();
            }
        }
    }

    private boolean exceptionsMismatch(InvocationTargetException e) {
        return !expectedException.isInstance(e.getTargetException());
    }

    private static boolean exceptionsAreNotExpected(Class expectedException) {
        return expectedException.equals(NoExceptionsExpected.class);
    }

}
