package com.protectsoft;


import org.junit.runner.JUnitCore;

public class JunitCoreRunnerTest {

	
	public static void main(String[] args) {
		String[] tests = {"com.protectsoft.TestAnswer","com.protectsoft.TestQuestion"};
		JUnitCore.main(tests);
	}

}
