package testanalyzer.parsing;

import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import testanalyzer.Quality;

public class QualityChecker extends VoidVisitorAdapter<Void> {

	public List<Quality> qualityData = new ArrayList<Quality>();
	public int numberOfValidTests=0;

	@Override
	public void visit(MethodDeclaration method, Void arg) {
		Quality quality = new Quality();
		qualityData.add(quality);
		if (isTest(method)) {
			if (!isIgnored(method)) {
				if (hasExpected(method)) {
					quality.assertCount = 1;
				}
				quality.assertCount += countAsserts(method);
				numberOfValidTests++;
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
