package testanalyzer.parsing.rules;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import testanalyzer.model.TestClassInfo;
import testanalyzer.model.TestInfo;
import testanalyzer.parsing.AssertInfoHelper;
import testanalyzer.parsing.AssertingMethods;
import testanalyzer.parsing.CalledMethods;

public class TestRules extends VoidVisitorAdapter<Void> {

	public  TestClassInfo testClassInfo = new TestClassInfo();
	AssertInfoHelper assertInfo = new AssertInfoHelper();
	int count = 0;
	
	@Override
	public void visit(MethodDeclaration method, Void arg) {
		if (isTest(method)) {
			if (!isIgnored(method)) {
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

	
	private boolean isTest(MethodDeclaration method) {
		return method.getAnnotationByName("Test").isPresent();
	}

	private boolean isIgnored(MethodDeclaration method) {
		return method.getAnnotationByName("Disabled").isPresent() || method.getAnnotationByName("Ignore").isPresent();
	}

	private boolean hasExpected(MethodDeclaration method) {
		ExpectedJUnit4Rules expectedAnnotationFinder = new ExpectedJUnit4Rules();
		method.accept(expectedAnnotationFinder, null);
		ExpectedRuleJUnit4Rules expectedRuleFinder = new ExpectedRuleJUnit4Rules();
		method.accept(expectedRuleFinder, null);
		return expectedAnnotationFinder.hasExpectedAnnotation ||
				expectedRuleFinder.hasExpectedRule;
	}

	private void addTestInfo(MethodDeclaration method) {
		TestInfo testInfo = testClassInfo.create(method.getNameAsString());
		if (hasExpected(method)) {
			testInfo.assertCount = 1;
		}
		testInfo.assertCount += assertInfo.getNumberOfAsserts(method) ;
		testClassInfo.incrementTests();
	}
}
