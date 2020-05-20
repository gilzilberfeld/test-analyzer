package testanalyzer.unittests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import testanalyzer.Tests;
import testanalyzer.TestsLoader;

class ContainingAsserts {

	private static final String ROOT_PATH = "src/main/java/testanalyzer/examples/";
	private Tests tests;

	@BeforeAll
	public static void setup() {
		TestsLoader.Root = ROOT_PATH;
	}

	@Test
	void test_contains_a_single_assert() {
		
	}
	
	@Test
	void test_contains_a_two_asserts() {
	}
	
	@Test
	void test_contains_no_asserts() {
	}
	
	@Test
	void test_contains_perform_and_checks_status_only() {
	}

	@Test
	void test_contains_perform_and_does_not_check() {
	}
	
	@Test
	void test_contains_perform_and_checks_status_and_response() {
	}

}
