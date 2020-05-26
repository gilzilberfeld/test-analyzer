package testanalyzer.parsing.rules;

import com.github.javaparser.ast.expr.NormalAnnotationExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class ExpectedJUnit4Rules extends VoidVisitorAdapter<Void> {

	public boolean hasExpectedAnnotation;

	@Override
	public void visit(NormalAnnotationExpr annotation, Void arg) {
		if (hasJUnit4ExpectedAnnotation(annotation)) {
			hasExpectedAnnotation = true;
		}
	}

	
	private boolean hasJUnit4ExpectedAnnotation(NormalAnnotationExpr annotation) {
		return annotation.getPairs().toString().contains("expected");
	}

}
