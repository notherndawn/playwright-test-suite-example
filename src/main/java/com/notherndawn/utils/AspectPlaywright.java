package com.notherndawn.utils;

import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

import static io.qameta.allure.util.ResultsUtils.getStatus;
import static io.qameta.allure.util.ResultsUtils.getStatusDetails;

@Aspect
public class AspectPlaywright {
    private static final Logger LOGGER = LoggerFactory.getLogger(AspectPlaywright.class);

    private static InheritableThreadLocal<AllureLifecycle> lifecycle = new InheritableThreadLocal<AllureLifecycle>() {
        @Override
        protected AllureLifecycle initialValue() {
            return Allure.getLifecycle();
        }
    };

    @Pointcut("execution(* com.microsoft.playwright.impl.LocatorAssertionsImpl.*(..))")
    public void locatorAssert() {
    }

    @Pointcut("execution(public * com.microsoft.playwright.impl.PageAssertionsImpl+.*(..)) || locatorAssert()")
    public void anyAssert() {
    }

    @Before("anyAssert()")
    public void stepStart(final JoinPoint joinPoint) {
        final MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        final String uuid = UUID.randomUUID().toString();
        Object[] args = joinPoint.getArgs();
        final String name;
        if (args.length > 0) {
            List<Object> argsList = Arrays.stream(args)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            name = String.format("%s '%s'", methodSignature.getName(), argsList);
        } else {
            name = methodSignature.getName();
        }

        final StepResult result = new StepResult()
                .setName(name);
        getLifecycle().startStep(uuid, result);
    }

    @AfterThrowing(pointcut = "anyAssert()", throwing = "e")
    public void stepFailed(final Throwable e) {
        getLifecycle().updateStep(s -> s
                .setStatus(getStatus(e).orElse(Status.BROKEN))
                .setStatusDetails(getStatusDetails(e).orElse(null)));
        getLifecycle().stopStep();
    }

    @AfterReturning(pointcut = "anyAssert()")
    public void stepStop() {
        getLifecycle().updateStep(s -> s.setStatus(Status.PASSED));
        getLifecycle().stopStep();
    }

    public static AllureLifecycle getLifecycle() {
        return lifecycle.get();
    }
}


