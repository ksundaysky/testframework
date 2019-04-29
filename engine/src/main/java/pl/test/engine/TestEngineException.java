package pl.test.engine;

/**
 * @author Makiela Wojciech
 */
public class TestEngineException extends Exception {
    TestEngineException(Throwable e) {
        super(e);
    }

    public TestEngineException(String msg, Exception e) {
        super(msg, e);
    }

    TestEngineException() {

    }
}
