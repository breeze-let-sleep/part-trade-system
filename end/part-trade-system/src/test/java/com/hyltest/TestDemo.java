package com.hyltest;

import org.junit.jupiter.api.Test;

import java.security.SecureRandom;
import java.util.Random;

public class TestDemo {

    @Test
    public void test() {
        SecureRandom secureRandom = new SecureRandom();
        int num = 100000 + secureRandom.nextInt(900000);
        String code = String.valueOf(num);
        System.out.println(code);
    }
}
