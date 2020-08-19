package testanalyzer.parsing.checkers;

import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class AssertChecker extends VoidVisitorAdapter<Void> {

	public int assertCount = 0 ;
	public int assertNotNullCount = 0 ;
	public int assertOnStatusCount = 0;
	
	@Override
	public void visit(MethodCallExpr method, Void arg) {
		countAsserts(method);
		countAssertsNotNull(method);
		countStatusAsserts(method);
	}

	private void countStatusAsserts(MethodCallExpr method) {
		if (assertsEqualsOnStatus(method) ||
				assertsTrueOnStatus(method) ||
				assertsOnMockMvcStatus(method))
			assertOnStatusCount++;
	}

	private boolean assertsOnMockMvcStatus(MethodCallExpr method) {
		return method.toString().contains("status()");
	}

	private boolean assertsTrueOnStatus(MethodCallExpr method) {
		return getMethodName(method).contains("assertTrue") && 
				firstParameterContainStatus(method);
	}

	private boolean assertsEqualsOnStatus(MethodCallExpr method) {
		return getMethodName(method).contains("assertEquals") && 
				parametersContainStatus(method);
	}


	private void countAssertsNotNull(MethodCallExpr method) {
		if (getMethodName(method).contains("assertNotNull"))
			assertNotNullCount++;
	}

	private void countAsserts(MethodCallExpr method) {
		if (getMethodName(method).contains("assert"))
			assertCount++;
		assertCount += countExpects(method);
	}

	private int countExpects(MethodCallExpr method) {
		String fullExpression = method.toString();
		String andExpectTerm = "andExpect\\(";
		return fullExpression.split(andExpectTerm, -1).length-1;
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


	private String getMethodName(MethodCallExpr method) {
		return method.getName().toString();
	}
}
