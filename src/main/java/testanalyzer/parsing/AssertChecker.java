package testanalyzer.parsing;

import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NormalAnnotationExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class AssertChecker extends VoidVisitorAdapter<Void> {

	public int count = 0 ;
	
	@Override
	public void visit(MethodCallExpr method, Void arg) {
		String name = method.getName().toString();
		if (name.contains("assert"))
			count++;
	}

}
