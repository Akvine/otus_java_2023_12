package tests;

import annotations.After;
import annotations.Before;
import annotations.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestLauncher<T> {
    public void launch(T object) {
        Class<? extends Object> clazz = object.getClass();
        List<Method> methodsWithBefore = extractMethodsWithBeforeAnnotation(clazz);
        List<Method> methodsWithTest = extractMethodsWithTestAnnotation(clazz);
        List<Method> methodsWithAfter = extractMethodsWithAfterAnnotation(clazz);

        int totalTestMethods = methodsWithTest.size();
        int successTestMethods = 0;

        for (Method method : methodsWithTest) {
            launchBeforeMethods(methodsWithBefore, object);
            try {
                method.invoke(object);
                successTestMethods += 1;
            } catch (Exception exception) {
                System.out.println("Something was wrong, ex = " + exception.getMessage());
            }
            launchAfterMethods(methodsWithAfter, object);
        }

        System.out.println("=================[Statistics]=================");
        System.out.println("Success tests: " + successTestMethods);
        System.out.println("Fail tests: " + (totalTestMethods - successTestMethods));
        System.out.println("Total tests: " + totalTestMethods);
    }

    private void launchBeforeMethods(List<Method> methodsWithBefore, T object) {
        try {
            methodsWithBefore.forEach(methodWithBefore -> {
                try {
                    methodWithBefore.invoke(object);
                } catch (Exception exception) {
                    System.out.println("Something was wrong, ex = " + exception.getMessage());
                }
            });
        } catch (Exception exception) {
            System.out.println("Something was wrong, ex = " + exception.getMessage());
        }
    }

    private void launchAfterMethods(List<Method> methodsWithAfter, T object) {
        methodsWithAfter.forEach(methodWithAfter -> {
            try {
                methodWithAfter.invoke(object);
            } catch (Exception exception) {
                System.out.println("Something was wrong, ex = " + exception.getMessage());
            }
        });
    }

    private List<Method> extractMethodsWithBeforeAnnotation(Class<? extends Object> testClass) {
        Method[] methods = testClass.getMethods();
        List<Method> methodsWithAnnotation = new ArrayList<>();
        Arrays
                .stream(methods)
                .forEach(method -> {
                    Annotation[] annotations = method.getAnnotations();
                    Arrays
                            .stream(annotations)
                            .forEach(annotation -> {
                                if (annotation instanceof Before) {
                                    methodsWithAnnotation.add(method);
                                }
                            });
                });
        return methodsWithAnnotation;
    }

    private List<Method> extractMethodsWithTestAnnotation(Class<? extends Object> testClass) {
        Method[] methods = testClass.getMethods();
        List<Method> methodsWithAnnotation = new ArrayList<>();
        Arrays
                .stream(methods)
                .forEach(method -> {
                    Annotation[] annotations = method.getAnnotations();
                    Arrays
                            .stream(annotations)
                            .forEach(annotation -> {
                                if (annotation instanceof Test) {
                                    methodsWithAnnotation.add(method);
                                }
                            });
                });
        return methodsWithAnnotation;
    }

    private List<Method> extractMethodsWithAfterAnnotation(Class<? extends Object> testClass) {
        Method[] methods = testClass.getMethods();
        List<Method> methodsWithAnnotation = new ArrayList<>();
        Arrays
                .stream(methods)
                .forEach(method -> {
                    Annotation[] annotations = method.getAnnotations();
                    Arrays
                            .stream(annotations)
                            .forEach(annotation -> {
                                if (annotation instanceof After) {
                                    methodsWithAnnotation.add(method);
                                }
                            });
                });
        return methodsWithAnnotation;
    }
}
