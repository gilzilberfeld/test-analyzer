package testanalyzer.parsing.rules;

import java.util.Optional;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import testanalyzer.model.TestClassInfo;
import testanalyzer.model.TestInfo;
import testanalyzer.parsing.TestParser;
import testanalyzer.parsing.TestType;
import testanalyzer.parsing.asserts.AssertInfoHelper;

public class TestRules extends VoidVisitorAdapter<Void> {

	public  TestClassInfo testClassInfo = new TestClassInfo();
	AssertInfoHelper assertInfo = new AssertInfoHelper();
	int count = 0;
	
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
	

	public TestClassInfo getTestClassInfo() {
		assertInfo.updateTestInfos(testClassInfo.testsInfo);
		return testClassInfo;
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
		if (TestParser.isSpringTestClass(method))
			return TestType.API;
		return TestType.Unit;
	}
}
