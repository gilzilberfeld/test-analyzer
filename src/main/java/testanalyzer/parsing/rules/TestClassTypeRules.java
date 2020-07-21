package testanalyzer.parsing.rules;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import testanalyzer.parsing.TestClassType;

public class TestClassTypeRules extends VoidVisitorAdapter<Void> {

		public TestClassType type = TestClassType.Unit;
		
		@Override
		public void visit(ClassOrInterfaceDeclaration cls, Void arg) {
			 if (cls.getAnnotationByName("SpringBootTest").isPresent()) {
	         	type = TestClassType.SpringBoot;
	         }
		}
}
