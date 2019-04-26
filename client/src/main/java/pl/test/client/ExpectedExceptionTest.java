package pl.test.client;

import pl.test.engine.Run;

/**
 * @author Makiela Wojciech
 */
class ExpectedExceptionTest {

    @Run(expectedException = IllegalArgumentException.class)
    private void checkIfExpectedExceptionsAreCaught(){
        throw new IllegalArgumentException();
    }

    @Run(expectedException = IndexOutOfBoundsException.class)
    private static void checkIfSubtypesOfGivenExceptionAreCaught() {
        throw new ArrayIndexOutOfBoundsException();
    }

    @Run(expectedException = IndexOutOfBoundsException.class)
    private static void checkIfExceptionMismatchIsHandled() {
        throw new IllegalArgumentException();
    }
}
