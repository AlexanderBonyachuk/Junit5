package com.in28minutes.junit5;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class StringTest {
	
	private String str;
	
	@BeforeAll
	static void beforeAll() {
		System.out.println("Initilize connection to database");	
	}
	
	@AfterAll
	static void afterAll() {
		System.out.println("Close connection to database");
	}
	
	@BeforeEach
	void brforeEach(TestInfo info) {
		System.out.println("Initilize Test Data for " + info.getDisplayName());
	}
	
	@AfterEach
	void afterEach(TestInfo info) {
		System.out.println("Clean up Test Data for " + info.getDisplayName());
	}

	@Test
	@Disabled
	void lenght_basic() {
		int actualLength = "ABCD".length();
		int expectedlenght = 4;
		assertEquals(expectedlenght, actualLength);
	}
	
	@Test
	void lenght_greater_than_zero() {
		assertTrue("ABCD".length() > 0);
		assertTrue("ABC".length() > 0);
		assertTrue("A".length() > 0);
		assertTrue("DEF".length() > 0);
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"ABCD", "ABC", "A", "DEF"})
	void lenght_greater_than_zero_using_parametrized_test(String str) {
		assertTrue(str.length() > 0);
	}
	
	@ParameterizedTest(name = "{0} toUpperCase is {1}")
	@CsvSource(value = {"abcd, ABCD", "abc, ABC", "'',''", "abcdefg, ABCDEFG"})
	void upperCase(String word, String capitalizedWord) {
		assertEquals(capitalizedWord, word.toUpperCase());
	}
	
	@ParameterizedTest(name = "{0} lenght {1}")
	@CsvSource(value = {"abcd, 4", "abc, 3", "'',0", "abcdefg, 7"})
	void lenght(String word, int expectedLenght) {
		assertEquals(expectedLenght, word.length());
	}
	
	@Test()
	@DisplayName("When lenght is null, throw an exception")
	void lenght_exeption() {
		String str = null;
		assertThrows(NullPointerException.class, 
				() -> {
					str.length();
				}
				);
	}
	
	@Test
	@Disabled
	void perfomanceTest() {
		assertTimeout(Duration.ofSeconds(3), 
				() -> {
					for(int i = 0; i <= 1000000; i++) {
						int j = i;
						System.out.println(j);
					}
				}
				);
	}

	@Test 
	void toUpperCase_basic() {
		String str = "abcd";
		String result = str.toUpperCase();
		assertNotNull(result);
		assertEquals("ABCD", result);		
	}
	
	@Test 
	@RepeatedTest(10)
	void contains_basic() {
		assertFalse("abcdefgh".contains("ijk"));
	}
	
	@Test
	void split_basic() {
		String str = "abc def ghi";
		String asctualResult[] = str.split(" "); 
		String[] expectedResult = new String[] {"abc", "def", "ghi"};
		
		assertArrayEquals(expectedResult, asctualResult);
	}
	
	@Nested
	@DisplayName("For an empty String")
	class EmptyStringTests {
		
		@BeforeEach
		void setToEmpty() {
			str = "";
		}
		
		@Test
		@DisplayName("lenght shold be zero")
		void lenghtIsZero() {
			assertEquals(0, str.length());
		}
		
		@Test
		@DisplayName("upper case is empty")
		void uppercaseIsEmpty() {
			assertEquals("", str.toUpperCase());
		}
		
	}
	
	@Nested
	@DisplayName("For a large strings")
	class LargeStringTest {
		
		@BeforeEach
		void setToALargeString() {
			str = "I am working with a large string in C#. "
					+ "For example, my string length is 2"
					+ ".000.000 characters. ";
		}
		
		@Test
		@DisplayName("lenght shold be 95")
		void lenghtIsZero() {
			assertEquals(95, str.length());
		}
		
		@Test
		@DisplayName("upper case is empty")
		void uppercaseIsEmpty() {
			assertEquals("I AM WORKING WITH A LARGE STRING "
					+ "IN C#. FOR EXAMPLE, MY STRING LENGTH"
					+ " IS 2.000.000 CHARACTERS. ", str.toUpperCase());
		}
		
	}
	
}
