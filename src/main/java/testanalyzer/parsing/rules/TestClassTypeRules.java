package testanalyzer.parsing.rules;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import testanalyzer.parsing.TestClassType;

public class TestClassTypeRules extends VoidVisitorAdapter<Void> {

		public TestClassType type = TestClassType.Unit;
		
		@Override
		public void visit(ClassOrInterfaceDeclaration cls, Void arg) {
			 if (hasSpringBootTestAnnotation(cls)) {
	         	type = TestClassType.SpringBoot;
	         }
			 else if (hasRunWithAnnotation(cls)) {
				 if (runnerNameIs(cls, "SpringRunner.class") ||
					(runnerNameIs(cls,"SpringJUnit4ClassRunner.class")))
						 type=TestClassType.Spring;
			 }
		}

		private boolean runnerNameIs(ClassOrInterfaceDeclaration cls, String runnerName) {
			return getRunnerName(cls).contains(runnerName);
		}

		private String getRunnerName(ClassOrInterfaceDeclaration cls) {
			return cls.getAnnotationByName("RunWith").get().getChildNodes().get(1).toString();
		}

		private boolean hasRunWithAnnotation(ClassOrInterfaceDeclaration cls) {
			return cls.getAnnotationByName("RunWith").isPresent();
		}

		private boolean hasSpringBootTestAnnotation(ClassOrInterfaceDeclaration cls) {
			return cls.getAnnotationByName("SpringBootTest").isPresent();
		}
}
