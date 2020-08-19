package testanalyzer.parsing.checkers;

import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class AssertChecker extends VoidVisitorAdapter<Void> {

	public int assertCount = 0 ;
	public int assertNotNullCount = 0 ;
	public int assertOnStatusCount = 0;
	
	@Override
	public void visit(MethodCallExpr method, Void arg) {
		String name = method.getName().toString();
		if (name.contains("assert"))
			assertCount++;
		assertCount += countExpects(method);
		if (name.contains("assertNotNull"))
			assertNotNullCount++;
		if (name.contains("assertEquals") && 
				containsStatus(method))
			assertOnStatusCount++;
	}

	private boolean containsStatus(MethodCallExpr method) {
		String firstParam = method.getChildNodes().get(1).toString().toLowerCase();
		String secondParam = method.getChildNodes().get(2).toString().toLowerCase();
		return firstParam.contains("status") || secondParam.contains("status");
	}

	private int countExpects(MethodCallExpr method) {
		String fullExpression = method.toString();
		String andExpectTerm = "andExpect\\(";
		return fullExpression.split(andExpectTerm, -1).length-1;
	}

}
