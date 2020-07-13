package testanalyzer.parsing.rules;

import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import testanalyzer.model.TestClassInfo;
import testanalyzer.model.TestInfo;

public class TestRules extends VoidVisitorAdapter<Void> {

	
	public  TestClassInfo testClassInfo = new TestClassInfo();
	List<InternalAssertingMethod> assertingMethods = new ArrayList<InternalAssertingMethod>();
	List<String> calledMethods = new ArrayList<String>();
	
	@Override
	public void visit(MethodDeclaration method, Void arg) {
		
		if (isTest(method)) {
			if (!isIgnored(method)) {
				addTestInfo(method);
				collectCalledMethods(method);
			}
		}
		else 
			addToAssertableMethodList(method);
	}
	
	private void collectCalledMethods(MethodDeclaration method) {
		// TODO Auto-generated method stub
		
	}

	private void addToAssertableMethodList(MethodDeclaration method) {
		InternalAssertingMethod iam = new InternalAssertingMethod(method);
		iam.assertCount = getNumberOfAsserts(method);
		assertingMethods.add(iam);
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
		// TODO Auto-generated method stub
		
	}

	int count = 0;
	private int addCalledAssertsCount(MethodDeclaration method) {
		List<InternalAssertingMethod> calledMethods = getListOfCalledMethods(method);
		calledMethods.forEach( iam -> {
			count += iam.getNumberOfAsserts();
		});
		return count;
	}
	
	
	private List<InternalAssertingMethod> getListOfCalledMethods(MethodDeclaration method) {
		return assertingMethods;
	}
	
	private int countAsserts(MethodDeclaration method) {
		AssertRules assertChecker = new AssertRules();
		method.accept(assertChecker, null);
		int count = assertChecker.count;
		count += addCalledAssertsCount(method);
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
