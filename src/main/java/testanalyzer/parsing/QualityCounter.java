package testanalyzer.parsing;

import java.util.List;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import testanalyzer.Quality;

public class QualityCounter extends VoidVisitorAdapter<Void> {

	public List<Quality> QualityData;
	public Quality result = new Quality();

	@Override
	public void visit(MethodDeclaration method, Void arg) {
		if (isTest(method)) {
			if (!Ignored(method)) {

			TestExpectedChecker expectedFinder = new TestExpectedChecker();
			method.accept(expectedFinder, null);
			if (expectedFinder.hasExpected)
				result.assertCount = 1;
			}
		}
	}

	private boolean isTest(MethodDeclaration method) {
		return method.getAnnotationByName("Test").isPresent();
	}

	private boolean Ignored(MethodDeclaration method) {
		return method.getAnnotationByName("Disabled").isPresent() || 
				method.getAnnotationByName("Ignore").isPresent()	;
	}

}
