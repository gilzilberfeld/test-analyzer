package testanalyzer.parsing.checkers;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import testanalyzer.model.TestClassType;
import testanalyzer.parsing.rules.TestClassRules;

public class TestClassTypeChecker extends VoidVisitorAdapter<Void> {

		public TestClassType type = TestClassType.Unit;
		
		@Override
		public void visit(ClassOrInterfaceDeclaration cls, Void arg) {
			 if (TestClassRules.hasSpringBootTestAnnotation(cls)) {
	         	type = TestClassType.SpringBoot;
	         }
			 else if (TestClassRules.hasRunWithAnnotation(cls)) {
				 if (TestClassRules.runnerNameIs(cls, "SpringRunner.class") ||
					(TestClassRules.runnerNameIs(cls,"SpringJUnit4ClassRunner.class")))
						 type=TestClassType.Spring;
			 }
		}
}
