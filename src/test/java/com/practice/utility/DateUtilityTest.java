package com.practice.utility;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

public class DateUtilityTest {
    @Test
	 void testFormatStringToDateTime(){
		LocalDateTime localDateTime = DateFormatterUtility.convertStringToDateTime("2020-06-05 22:27:00");
        Assert.isTrue(localDateTime != null, "Not Matched");
        Assert.isTrue(localDateTime.getDayOfMonth() == 05,"Not Matched");
	}

    @Test
    void testFormatStringToDate(){
        LocalDateTime localDateTime = DateFormatterUtility.convertStringToDate("2020-06-05");
        Assert.isTrue(localDateTime != null, "Not Matched");
        Assert.isTrue(localDateTime.getDayOfMonth() == 05,"Not Matched");
    }

    @Test
    void testFormatDateToString(){
        String stringDateTime = DateFormatterUtility.convertDateTimeToString(DateFormatterUtility.convertStringToDateTime("2020-06-05 22:27:00"));
        Assert.isTrue(stringDateTime != null, "Not Matched");
        Assert.isTrue(stringDateTime.equalsIgnoreCase("202006051027"), "Not Matched");
    }

}
