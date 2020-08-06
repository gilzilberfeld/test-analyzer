package testanalyzer.parsing.checkers;

import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class APIChecker extends VoidVisitorAdapter<Void> {

	public boolean callsPerform = false;
	public boolean callsRestTemplate = false;

	@Override
	public void visit(MethodCallExpr method, Void arg) {
		String methodExpression = method.toString();
		callsPerform = methodExpression.contains("perform");
		callsRestTemplate = callsRestTemplateMethods(methodExpression);
		
	}

	private boolean callsRestTemplateMethods(String methodExpression) {
		return methodExpression.contains("exchange") ||
				methodExpression.contains("getFor") ||
				methodExpression.contains("postFor") ||
				methodExpression.contains("patchFor");
	}

}
