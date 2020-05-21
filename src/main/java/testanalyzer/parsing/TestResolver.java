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

import testanalyzer.TestClassQuality;
import testanalyzer.TestQuality;

public class TestResolver {

	private CompilationUnit cu;
	private JavaParserClassDeclaration cd;
	
	public TestResolver(String className, String path) throws FileNotFoundException {
		File file = new File(path);
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
		return getTestQualityData().numberOfValidTests;
	}


	public TestClassQuality getTestQualityData() {
		QualityChecker visitor = new QualityChecker();
		cu.accept(visitor, null);
		return visitor.qualityData;
	}


}
