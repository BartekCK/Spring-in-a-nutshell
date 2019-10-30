package com.example.demospring;

import com.example.demospring.controller.AlienController;
import com.example.demospring.model.Alien;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(public * com.example.demospring.controller.AlienController.getAliens())")
    public void logBefore(){
        LOGGER.info("getAlines() method will call");
    }


    @After("execution(public * com.example.demospring.controller.AlienController.getAliens())")
    public void logAfterWithException(){
        LOGGER.info("getAlines() method was called but maybe an exception occurred ");
    }

    @AfterReturning("execution(public * com.example.demospring.controller.AlienController.getAliens())")
    public void logAfter(){
        LOGGER.info("getAlines() method was called without exception");
    }

    @AfterThrowing("execution(public * com.example.demospring.controller.AlienController.getAliens())")
    public void logException(){
        LOGGER.warn("EXCEPTION");
    }
}
