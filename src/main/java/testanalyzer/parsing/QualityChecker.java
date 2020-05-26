package testanalyzer.parsing;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import testanalyzer.TestClassQuality;
import testanalyzer.TestQuality;

public class QualityChecker extends VoidVisitorAdapter<Void> {

	
	public  TestClassQuality testClassInfo = new TestClassQuality();
	
	@Override
	public void visit(MethodDeclaration method, Void arg) {
		
		if (isTest(method)) {
			if (!isIgnored(method)) {
				TestQuality testInfo = testClassInfo.create();
				testInfo.testName = method.getName().toString();
				if (hasExpected(method)) {
					testInfo.assertCount = 1;
				}
				testInfo.assertCount += countAsserts(method);
				testClassInfo.incrementTests(); 
			}
		}
	}

	
	private int countAsserts(MethodDeclaration method) {
		AssertChecker assertChecker = new AssertChecker();
		method.accept(assertChecker, null);
		return assertChecker.count;
	}



	
	private boolean isTest(MethodDeclaration method) {
		return method.getAnnotationByName("Test").isPresent();
	}

	private boolean isIgnored(MethodDeclaration method) {
		return method.getAnnotationByName("Disabled").isPresent() || method.getAnnotationByName("Ignore").isPresent();
	}

	private boolean hasExpected(MethodDeclaration method) {
		ExpectedJUnit4Checker expectedAnnotationFinder = new ExpectedJUnit4Checker();
		method.accept(expectedAnnotationFinder, null);
		ExpectedRuleJUnit4Checker expectedRuleFinder = new ExpectedRuleJUnit4Checker();
		method.accept(expectedRuleFinder, null);
		return expectedAnnotationFinder.hasExpectedAnnotation ||
				expectedRuleFinder.hasExpectedRule;
	}

}
