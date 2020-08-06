package testanalyzer.parsing.rules;

import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class AssertRules extends VoidVisitorAdapter<Void> {

	public int assertCount = 0 ;
	public int assertNotNullCount = 0 ;
	
	@Override
	public void visit(MethodCallExpr method, Void arg) {
		String name = method.getName().toString();
		if (name.contains("assert"))
			assertCount++;
		assertCount += countExpects(method);
		if (name.contains("assertNotNull"))
			assertNotNullCount++;
	}

	private int countExpects(MethodCallExpr method) {
		String fullExpression = method.toString();
		String andExpectTerm = "andExpect\\(";
		return fullExpression.split(andExpectTerm, -1).length-1;
	}

}
