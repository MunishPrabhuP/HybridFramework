package com.newera.api.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class TestUtils {
	protected String generateFutureDate(String format, long noOfDays) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String futureDate;

		futureDate = formatter
				.format(Date.from(LocalDateTime.now().plusDays(noOfDays).atZone(ZoneId.systemDefault()).toInstant()));
		return futureDate;
	}
}
