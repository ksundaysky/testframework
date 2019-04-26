package pl.test.client;

import pl.test.engine.Run;

/**
 * @author Makiela Wojciech
 */
class InvocationCountTesting {

    @Run(invocationCount = 5)
    public void checkIfInvocationCountWorksOnInstanceMethods(){
        assert true;
    }

    @Run(invocationCount = 5)
    public static void checkIfInvocationCountWorksOnStaticMethods() {
        assert true;
    }

    private static int counter = 0;
    @Run(invocationCount = 5)
    public static void assertCounterIsEven() {
        counter++;
        assert counter % 2 == 0;
    }

}
