package pl.test.engine;

import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * @author krzysztof.niedzielski
 */
public class TestEngine {

    public static void run() throws InvocationTargetException, IllegalAccessException {

        Reflections reflections = new Reflections("pl.test.client",new MethodAnnotationsScanner(),new SubTypesScanner());

        Set<Method> allMethods =reflections.getMethodsAnnotatedWith(Run.class);

//        Set<Class<?>> subTypesOf = reflections.getSubTypesOf(Object.class);

        for(Method method : allMethods)
            {
                Class<?> declaringClass = method.getDeclaringClass();
                System.out.println(method.getName());
                try {
                    method.invoke(declaringClass.getConstructor().newInstance());
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
            }

    }


}
