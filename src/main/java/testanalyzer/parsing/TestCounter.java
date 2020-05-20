package testanalyzer.parsing;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class TestCounter extends VoidVisitorAdapter<Void> {

	public int numberOfTests;

	@Override
	public void visit(MethodDeclaration method, Void arg) {
		if (method.getAnnotationByName("Test").isPresent()) {
        	numberOfTests++;
        }
	}
}
