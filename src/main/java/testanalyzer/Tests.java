package testanalyzer;

import java.io.File;
import java.io.FileNotFoundException;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.symbolsolver.javaparser.Navigator;
import com.github.javaparser.symbolsolver.javaparsermodel.declarations.JavaParserClassDeclaration;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;

public class Tests {

	private int numberOfMethods;
	private JavaParserClassDeclaration cd;
	
	public Tests(JavaParserClassDeclaration cd) {
		this.cd = cd;
	    numberOfMethods = cd.getDeclaredMethods().size();
	}
	
	public int getCount() {
		return numberOfMethods;
	}

}
