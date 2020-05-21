package testanalyzer;

import java.io.File;
import java.io.FileNotFoundException;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.symbolsolver.javaparser.Navigator;
import com.github.javaparser.symbolsolver.javaparsermodel.declarations.JavaParserClassDeclaration;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;

import testanalyzer.parsing.TestResolver;

public class TestClassLocator {

	public static String Root;

	public static Tests loadClass(String className) throws FileNotFoundException {
		String filePath = Root+className+".java";
		TestResolver resolver = new TestResolver(className,filePath);
		
		return new Tests(resolver);
	}

}
