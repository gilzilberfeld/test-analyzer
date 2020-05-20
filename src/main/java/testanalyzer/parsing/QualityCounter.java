package testanalyzer.parsing;

import java.util.List;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import testanalyzer.Quality;

public class QualityCounter extends VoidVisitorAdapter<Void> {

	public List<Quality> QualityData;
	public Quality result = new Quality();
	public int numberOfValidTests=0;

	@Override
	public void visit(MethodDeclaration method, Void arg) {
		if (isTest(method)) {
			if (!isIgnored(method)) {
				if (hasExpected(method))
					result.assertCount = 1;
				numberOfValidTests++;
			}
		}
	}

	private boolean isTest(MethodDeclaration method) {
		return method.getAnnotationByName("Test").isPresent();
	}

	private boolean isIgnored(MethodDeclaration method) {
		return method.getAnnotationByName("Disabled").isPresent() || method.getAnnotationByName("Ignore").isPresent();
	}

	private boolean hasExpected(MethodDeclaration method) {
		TestExpectedChecker expectedFinder = new TestExpectedChecker();
		method.accept(expectedFinder, null);
		return expectedFinder.hasExpected;
	}

}
