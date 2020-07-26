package testanalyzer.parsing.rules;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import testanalyzer.model.TestClassInfo;
import testanalyzer.model.TestInfo;
import testanalyzer.parsing.TestClassParser;
import testanalyzer.parsing.TestClassType;
import testanalyzer.parsing.TestParser;
import testanalyzer.parsing.TestType;
import testanalyzer.parsing.asserts.AssertInfoHelper;

public class TestRules extends VoidVisitorAdapter<Void> {

	private  TestClassInfo testClassInfo;
	AssertInfoHelper assertInfo = new AssertInfoHelper();
	int count = 0;

	public TestRules(TestClassInfo testClassInfo) {
		this.testClassInfo = testClassInfo;
	}
	
	@Override
	public void visit(MethodDeclaration method, Void arg) {
		if (TestParser.isTest(method)) {
			if (!TestParser.isIgnored(method)) {
				addTestInfo(method);
				assertInfo.collectCalledMethods(method);
			}
		}
		else 
			assertInfo.addToAssertingMethodList(method);
	}
	

	public void updateAssertCount(TestClassInfo testClassInfo) {
		assertInfo.updateTestInfos(testClassInfo.testsInfo);
	}

	
	private void addTestInfo(MethodDeclaration method) {
		TestInfo testInfo = testClassInfo.create(method.getNameAsString());
		if (TestParser.hasExpected(method)) {
			testInfo.assertCount = 1;
		}
		testInfo.assertCount += assertInfo.getNumberOfAsserts(method);
		testInfo.type = getType(method);
		testClassInfo.incrementTests();
	}


	private TestType getType(MethodDeclaration method) {
		if (isSpringBootTestClass())
			if (callsAPI(method))
				return TestType.API;
			else 
				return TestType.Spring;
		if (isSpringRunnerClass())
			return TestType.Spring;
		return TestType.Unit;
	}
	
	
	private boolean callsAPI(MethodDeclaration method) {
		APIRules apiChecker = new APIRules();
		method.accept(apiChecker, null);
		return apiChecker.callsPerform;
	}

	private boolean isSpringRunnerClass() {
		return (testClassInfo.type == TestClassType.Spring);
	}

	public boolean isSpringBootTestClass() {
		return (testClassInfo.type == TestClassType.SpringBoot);
	}


}
