package com.example.service;

import java.util.Random;

import org.springframework.stereotype.Service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class HelloService {
	private int count = 0;
	
    @CircuitBreaker(name = "hello", fallbackMethod = "helloFallback")
    public String greeting() {
    	count += 1;
        randomException();
        return "hello world!";
    }

    private void randomException() {
        int randomInt = new Random().nextInt(10);
        System.out.println("randomInt :" + randomInt);
        if(randomInt <= 4) {
            throw new RuntimeException("failed");
        }
    }

    private String helloFallback(Throwable t) {
    	System.out.println("helloFallback :" + count + "    :" + t.getClass());
        return "fallback invoked! exception type : " + count + "    :" + t.getClass();
    }
//https://gardeny.tistory.com/46
}