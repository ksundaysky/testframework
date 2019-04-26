package pl.test.client;

import pl.test.engine.TestEngine;

import java.lang.reflect.InvocationTargetException;

/**
 * @author krzysztof.niedzielski
 */
public class Main {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        TestTestEngine testTestEngine = new TestTestEngine();
        System.out.println(testTestEngine.getClass().getName());
        TestEngine.run();
    }
}
