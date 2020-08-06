package testanalyzer.parsing.asserts;

import java.util.List;

import com.github.javaparser.ast.body.MethodDeclaration;

import testanalyzer.model.TestInfo;
import testanalyzer.parsing.InternalMethodCollector;
import testanalyzer.parsing.checkers.AssertChecker;

public class AssertInfoHelper {
	AssertingMethods assertingMethods = new AssertingMethods();
	CalledMethods calledMethods = new CalledMethods();

	private int assertCount;
	private int assertNotNullCount;
	
	public void collectCalledMethods(MethodDeclaration method) {
		InternalMethodCollector collector = new InternalMethodCollector();
		method.accept(collector, calledMethods.listToFillFor(method.getNameAsString()));
	}

	public void addToAssertingMethodList(MethodDeclaration method) {
		assertingMethods.add(method.getNameAsString(), getNumberOfAsserts(method));
	}
	
	public int getNumberOfAsserts(MethodDeclaration method) {
		AssertChecker assertChecker = parseAsserts(method);
		this.assertCount= assertChecker.assertCount;
		this.assertNotNullCount = assertChecker.assertNotNullCount;
		return this.assertCount; 
	}

	private AssertChecker parseAsserts(MethodDeclaration method) {
		AssertChecker assertChecker = new AssertChecker();
		method.accept(assertChecker, null);
		return assertChecker;
	}
	
	
	public int getNumberOfNotNullAsserts(MethodDeclaration method) {
		return this.assertNotNullCount;
	}

	public void updateTestInfos(List<TestInfo> tests) {
		tests.forEach( ti -> {
			calledMethods.listFor(ti.testName)
				.forEach( calledMethod -> {
					ti.assertCount += 
							assertingMethods.getAssertCountFor(calledMethod); 
			});
		});
	}

}
