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
				parametersContainStatus(method))
			assertOnStatusCount++;
		if (name.contains("assertTrue") && 
				firstParameterContainStatus(method))
			assertOnStatusCount++;
	}

	private boolean firstParameterContainStatus(MethodCallExpr method) {
		String firstParam = getParamExpression(1, method);		
		return containsStatusString(firstParam);
	}


	private boolean parametersContainStatus(MethodCallExpr method) {
		String secondParam = getParamExpression(2, method);
		return firstParameterContainStatus(method) || containsStatusString(secondParam);
	}
	
	private boolean containsStatusString(String paramExpression) {
		return paramExpression.contains("getstatuscodevalue") || 
				paramExpression.contains("status");
	}

	
	
	private String getParamExpression(int id, MethodCallExpr method) {
		return method.getChildNodes().get(id).toString().toLowerCase();
	}

	private int countExpects(MethodCallExpr method) {
		String fullExpression = method.toString();
		String andExpectTerm = "andExpect\\(";
		return fullExpression.split(andExpectTerm, -1).length-1;
	}

}
