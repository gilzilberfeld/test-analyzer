package testanalyzer.model;

import java.io.Serializable;

public class TestInfo implements Serializable {

	public String testClassPath="";
	public int totalAssertCount=0;
	public String testName=TestClassInfo.NoName;
	public TestType type;
	public int assertNotNullCount=0;
	public int assertStatusCount=0;
}
