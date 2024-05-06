package ru.homeworks.homework8.aspects;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class MyAspects {
    @AfterReturning(value = "@annotation(TrackUserActionAfterReturn)", returning = "returnValues")
    public void logInConsoleAfterReturn(JoinPoint jp, Object returnValues) {
        jp.getSignature().getName();
        System.out.println(
                "Method: " + jp.getSignature().getName() + '\n' +
                        "Arguments ---> " + Arrays.toString(jp.getArgs()) + '\n' +
                        "Annotation TrackUserActionAfterReturn: Return values -->" + returnValues + '\n'
        );
    }

    @Before(value = "@annotation(TrackUserActionBefore)")
    public void logInConsoleBefore(JoinPoint jp) {
        System.out.println(
                "Method: " + jp.getSignature().getName() + '\n' +
                        "Annotation TrackUserActionBefore: Arguments ---> "
                        + Arrays.toString(jp.getArgs())
        );
    }

}
