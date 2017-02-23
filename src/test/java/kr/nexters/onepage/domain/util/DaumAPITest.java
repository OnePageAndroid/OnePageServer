package kr.nexters.onepage.domain.util;

import org.junit.Test;

public class DaumAPITest {
	@Test
	public void test_convert() {
		System.out.println(DaumAPI.makeLocation(37.5485297, 126.91336309999997));
	}
}