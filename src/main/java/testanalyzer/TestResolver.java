package testanalyzer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.symbolsolver.javaparser.Navigator;
import com.github.javaparser.symbolsolver.javaparsermodel.declarations.JavaParserClassDeclaration;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;

public class TestResolver {

	private CompilationUnit cu;
	private JavaParserClassDeclaration cd;
	private boolean hasTestMethods;
	
	
	public TestResolver(File file, String className) throws FileNotFoundException {
		
		this.cu = StaticJavaParser.parse(file);
		this.cd = new JavaParserClassDeclaration(
					Navigator.demandClass(cu, className), 
					new ReflectionTypeSolver());
	}

	
	public boolean isNotTestClass() {
        hasTestMethods = false;
        cu.accept(new VoidVisitorAdapter<Void>() {
            @Override
            public void visit(MethodDeclaration method, Void arg) {
                if (method.getAnnotationByName("Test").isPresent()) {
                	hasTestMethods = true;
                }
            }
        }, null);
        
		return !hasTestMethods;
	}

	public int getNumberOfTests() {
		return cd.getDeclaredMethods().size();	
	}


}
