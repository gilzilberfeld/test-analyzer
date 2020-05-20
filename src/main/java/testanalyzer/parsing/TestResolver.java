package testanalyzer.parsing;

import java.io.File;
import java.io.FileNotFoundException;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.symbolsolver.javaparser.Navigator;
import com.github.javaparser.symbolsolver.javaparsermodel.declarations.JavaParserClassDeclaration;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;

public class TestResolver {

	private CompilationUnit cu;
	private JavaParserClassDeclaration cd;
	
	public TestResolver(File file, String className) throws FileNotFoundException {
		
		this.cu = StaticJavaParser.parse(file);
		this.cd = new JavaParserClassDeclaration(
					Navigator.demandClass(cu, className), 
					new ReflectionTypeSolver());
	}

	
	public boolean isNotTestClass() {
        TestClassChecker testChecker = new TestClassChecker();
        cu.accept(testChecker, null);
		return !testChecker.hasTestMethods;
	}

	public int getNumberOfTests() {
		TestCounter visitor = new TestCounter();
		cu.accept(visitor, null);
		return visitor.numberOfTests;
	}


}
