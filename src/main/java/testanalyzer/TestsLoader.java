package testanalyzer;

import java.io.File;
import java.io.FileNotFoundException;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.symbolsolver.javaparser.Navigator;
import com.github.javaparser.symbolsolver.javaparsermodel.declarations.JavaParserClassDeclaration;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;

public class TestsLoader {

	public static String Root;

	public static Tests load(String className) throws FileNotFoundException {
		CompilationUnit cu = StaticJavaParser.parse(new File(Root+className+".java"));
		TypeSolver typeSolver = new ReflectionTypeSolver();

		return new Tests( new JavaParserClassDeclaration(
						Navigator.demandClass(cu, className), 
						typeSolver));
	}

}
