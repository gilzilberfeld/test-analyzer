package testanalyzer.parsing.rules;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import testanalyzer.model.TestClassInfo;
import testanalyzer.model.TestInfo;
import testanalyzer.parsing.AssertingMethods;
import testanalyzer.parsing.CalledMethods;

public class TestRules extends VoidVisitorAdapter<Void> {

	
	public  TestClassInfo testClassInfo = new TestClassInfo();
	AssertingMethods assertingMethods = new AssertingMethods();
	CalledMethods calledMethods = new CalledMethods();
	int count = 0;
	
	@Override
	public void visit(MethodDeclaration method, Void arg) {
		if (isTest(method)) {
			if (!isIgnored(method)) {
				addTestInfo(method);
				collectCalledMethods(method);
			}
		}
		else 
			addToAssertingMethodList(method);
	}
	
	private void collectCalledMethods(MethodDeclaration method) {
		InternalMethodCollector collector = new InternalMethodCollector();
		method.accept(collector, calledMethods.listToFillFor(method.getNameAsString()));
	}

	private void addToAssertingMethodList(MethodDeclaration method) {
		assertingMethods.add(method.getNameAsString(), getNumberOfAsserts(method));
	}

	private int getNumberOfAsserts(MethodDeclaration method) {
		AssertRules assertChecker = new AssertRules();
		method.accept(assertChecker, null);
		return assertChecker.count;
	}

	public TestClassInfo getTestClassInfo() {
		updateTestInfos();
		return testClassInfo;
	}

	private void updateTestInfos() {
		for (TestInfo ti : testClassInfo.testsInfo) {
			String testName = ti.testName;
			for (String calledMethod : calledMethods.listFor(testName)) {
				ti.assertCount += assertingMethods.getAssertCountFor(calledMethod); 
			}
		}
	}

	private int getCalledAssertsCount(MethodDeclaration method) {
//		List<AssertMethodInfo> calledMethods = getListOfCalledMethods(method);
//		calledMethods.forEach( iam -> {
//			count += iam.getNumberOfAsserts();
//		});
		return count;
	}
	
	
//	private List<AssertMethodInfo> getListOfCalledMethods(MethodDeclaration method) {
//		return assertingMethods;
//	}
	
	private int countAsserts(MethodDeclaration method) {
		int count = getNumberOfAsserts(method);
//		count += getCalledAssertsCount(method);
		return count;
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
		TestInfo testInfo = testClassInfo.create();
		testInfo.testName = method.getNameAsString();
		if (hasExpected(method)) {
			testInfo.assertCount = 1;
		}
		testInfo.assertCount += countAsserts(method) ;
		testClassInfo.incrementTests();
	}
}
