package pl.test.client;

import pl.test.engine.TestEngine;
import pl.test.engine.TestEngineException;

/**
 * @author krzysztof.niedzielski
 */
public class Main {

    public static void main(String[] args) throws TestEngineException {
        TestEngine.run();
    }
}
