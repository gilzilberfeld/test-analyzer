package testanalyzer.parsing;

import java.util.List;

import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class InternalMethodCollector extends VoidVisitorAdapter<List<String>> {

	@Override
	public void visit(MethodCallExpr method, List<String> methodCalls) {
		methodCalls.add(method.getNameAsString());
	}

}
