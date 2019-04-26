package pl.test.client;

import pl.test.engine.TestEngine;
import pl.test.engine.TestEngineException;

/**
 * @author krzysztof.niedzielski
 */
public class Main {

    public static void main(String[] args) throws TestEngineException {
        TestTestEngine testTestEngine = new TestTestEngine();
        System.out.println(testTestEngine.getClass().getName());
        TestEngine.run("pl.test.client");
    }
}
