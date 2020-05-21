package testanalyzer.parsing;

import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import testanalyzer.TestClassQuality;
import testanalyzer.TestQuality;

public class QualityChecker extends VoidVisitorAdapter<Void> {

	
	public  TestClassQuality qualityData = new TestClassQuality();
	
	@Override
	public void visit(MethodDeclaration method, Void arg) {
		TestQuality testQuality = qualityData.create();
		
		if (isTest(method)) {
			if (!isIgnored(method)) {
				if (hasExpected(method)) {
					testQuality.assertCount = 1;
				}
				testQuality.assertCount += countAsserts(method);
				qualityData.incrementTests(); 
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
		ExpectedChecker expectedFinder = new ExpectedChecker();
		method.accept(expectedFinder, null);
		return expectedFinder.hasExpected;
	}

}
