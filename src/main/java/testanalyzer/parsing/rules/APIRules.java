package testanalyzer.parsing.rules;

import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class APIRules extends VoidVisitorAdapter<Void> {

	public boolean callsPerform = false;

	@Override
	public void visit(MethodCallExpr method, Void arg) {
		String methodExpression = method.toString();
		if (methodExpression.contains("perform"))
			callsPerform = true;
	}

}
