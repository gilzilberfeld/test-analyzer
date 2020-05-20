package testanalyzer.parsing;

import com.github.javaparser.ast.expr.NormalAnnotationExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class TestExpectedChecker extends VoidVisitorAdapter<Void> {

	public boolean hasExpected;

	@Override
	public void visit(NormalAnnotationExpr annotation, Void arg) {
		if (annotation.getPairs().toString().contains("expected")) {
			hasExpected = true;
		}
	}

}
