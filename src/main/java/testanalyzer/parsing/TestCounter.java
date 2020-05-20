package testanalyzer.parsing;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class TestCounter extends VoidVisitorAdapter<Void> {

	public int numberOfTests;

	@Override
	public void visit(MethodDeclaration method, Void arg) {
		if (isTest(method)) {
			if (!Ignored(method)) {
				numberOfTests++;
			}
        }
	}

	private boolean Ignored(MethodDeclaration method) {
		return method.getAnnotationByName("Disabled").isPresent() || 
				method.getAnnotationByName("Ignore").isPresent()	;
	}

	private boolean isTest(MethodDeclaration method) {
		return method.getAnnotationByName("Test").isPresent();
	}
}
