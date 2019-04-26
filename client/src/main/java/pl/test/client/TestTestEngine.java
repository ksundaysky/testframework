package pl.test.client;

import pl.test.engine.Run;
import pl.test.engine.TestEngine;

import java.lang.reflect.InvocationTargetException;

/**
 * @author krzysztof.niedzielski
 */
public class TestTestEngine {

    @Run
    public void testIfReflectionWorks(){
        System.out.println("works");
    }


}
