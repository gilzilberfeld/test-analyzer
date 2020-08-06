package testanalyzer.model;

import java.io.Serializable;

import testanalyzer.parsing.TestType;

public class TestInfo implements Serializable {

	public int assertCount=0;
	public String testName=TestClassInfo.NoName;
	public TestType type;
	public int assertNotNullCount=0;

}
