package pl.test.client;

import pl.test.engine.Run;
import pl.test.engine.TestEngine;

import java.lang.reflect.InvocationTargetException;

/**
 * @author krzysztof.niedzielski
 */
public class TestTestEngine {

    @Run
    public void assertFalseInInstanceMethod(){
        assert false;
    }

    @Run
    public static void assertFalseInStaticMethod() {
        assert false;
    }

    public static void shouldIgnoreStaticMethodsWithoutAnnotation() {
        System.out.println("Doesn't work! Static method without annotation is running!");
    }

    public void shouldIgnoreNonStaticMethodsWithoutAnnotation() {
        System.out.println("Doesn't work! Method without annotation is running!");
    }



}
