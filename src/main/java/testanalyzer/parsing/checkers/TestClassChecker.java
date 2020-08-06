package testanalyzer.parsing.checkers;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class TestClassChecker extends VoidVisitorAdapter<Void> {

	public boolean hasTestMethods=false;

	@Override
	public void visit(MethodDeclaration method, Void arg) {
		 if (method.getAnnotationByName("Test").isPresent()) {
         	hasTestMethods = true;
         }
	}
}
