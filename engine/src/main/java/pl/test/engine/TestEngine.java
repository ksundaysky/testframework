package pl.test.engine;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static pl.test.engine.TestResultLogger.logTestResult;

/**
 * @author Wojciech Makieła
 */
public interface TestEngine {

    static void run(String packageWithTest) throws TestEngineException {
        try {
            runTests(packageWithTest);
        } catch (Throwable e) {
            throw new TestEngineException(e);
        }
    }

    static void runTests(String packageWithTest) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, TestEngineException {
        Reflections reflections = new Reflections(packageWithTest, new SubTypesScanner(false));
        for (Class<?> aClass : reflections.getSubTypesOf(Object.class)) {
            runTestsInClass(aClass);
        }
    }

    static void runTestsInClass(Class<?> aClass) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Method[] declaredMethods = aClass.getDeclaredMethods();
        Object testClassInstance = null; // lazy initialization - instance might not be required
        for (Method method : declaredMethods) {
            if (method.isAnnotationPresent(Run.class)) {
                Run annotation = method.getAnnotation(Run.class);
                testClassInstance = initializeClassInstanceIfRequired(aClass, testClassInstance, method);
                method.setAccessible(true);
                runMethodInAccordanceToAnnotationData(testClassInstance, method, annotation);
            }
        }
    }

    static Object initializeClassInstanceIfRequired(Class<?> aClass, Object testClassInstance, Method method) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        if (instanceIsRequired(testClassInstance, method)) {
            testClassInstance = createInstance(aClass);
        }
        return testClassInstance;
    }

    static void runMethodInAccordanceToAnnotationData(Object testClassInstance, Method method, Run annotation) throws IllegalAccessException {
        for (int i = 1; i <= annotation.invocationCount(); i++) {
            Test test = new Test(testClassInstance, method, annotation.expectedException());
            test.run();
            logTestResult(test, i);
        }
    }

    static Object createInstance(Class<?> aClass) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Constructor<?> constructor = aClass.getDeclaredConstructor();
        constructor.setAccessible(true);
        return constructor.newInstance();
    }

    static boolean instanceIsRequired(Object testClassInstance, Method method) {
        return !Modifier.isStatic(method.getModifiers()) && testClassInstance == null;
    }


}
