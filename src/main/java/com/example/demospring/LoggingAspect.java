package com.example.demospring;

import com.example.demospring.controller.AlienController;
import com.example.demospring.model.Alien;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(public * com.example.demospring.controller.AlienController.getAliens())")
    public void getLog(){
        System.out.println("getAlines() method call");

    }
}
