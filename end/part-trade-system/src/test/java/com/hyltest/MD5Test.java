package com.hyltest;

import com.hyltest.utils.MD5Utils;
import org.junit.jupiter.api.Test;

public class MD5Test {

    @Test
    public void testGenerateMD5() {
        String md5 = MD5Utils.encrypt("1234");
        System.out.println(md5);
    }
}
