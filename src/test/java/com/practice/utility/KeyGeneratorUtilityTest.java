package com.practice.utility;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

public class KeyGeneratorUtilityTest {

    @Test
    void generateKey(){
        Assert.isTrue(KeyGeneratorUtility.generateKey("INR", "USD")
                .equalsIgnoreCase( "USDTOINR"),
                "Not Matched");
    }
}
