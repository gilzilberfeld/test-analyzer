package testanalyzer.parsing;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.symbolsolver.javaparser.Navigator;
import com.github.javaparser.symbolsolver.javaparsermodel.declarations.JavaParserClassDeclaration;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;

import testanalyzer.Quality;

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
		QualityChecker visitor = new QualityChecker();
		cu.accept(visitor, null);
		return visitor.numberOfValidTests;
	}


	public List<Quality> getTestQualityData() {
		List<Quality> qualityData = new ArrayList<Quality>();
		QualityChecker visitor = new QualityChecker();
		cu.accept(visitor, null);
		qualityData.add(visitor.result);
		return qualityData;
	}


}
