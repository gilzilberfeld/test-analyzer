package testanalyzer;

import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.symbolsolver.javaparser.Navigator;
import com.github.javaparser.symbolsolver.javaparsermodel.declarations.JavaParserClassDeclaration;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;

public class Tests {

	private TestResolver resolver;
	
	
	public Tests(TestResolver resolver) {
		this.resolver = resolver;
	}

	public int getCount() {
		if (resolver.isNotTestClass())
			return 0;
		return resolver.getNumberOfTests();
	}

}
