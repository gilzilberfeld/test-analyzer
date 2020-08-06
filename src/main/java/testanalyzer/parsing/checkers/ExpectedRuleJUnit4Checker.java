package testanalyzer.parsing.checkers;

import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class ExpectedRuleJUnit4Checker extends VoidVisitorAdapter<Void> {

	public boolean hasExpectedRule;
	
	@Override
	public void visit(MethodCallExpr method, Void arg) {
		String name = method.getName().toString();
		if (name.contains("expect"))
			hasExpectedRule = true;
	}

}
