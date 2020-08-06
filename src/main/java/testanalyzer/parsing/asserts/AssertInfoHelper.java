package testanalyzer.parsing.asserts;

import java.util.List;

import com.github.javaparser.ast.body.MethodDeclaration;

import testanalyzer.model.TestInfo;
import testanalyzer.parsing.rules.AssertRules;
import testanalyzer.parsing.rules.InternalMethodCollector;

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
		AssertRules assertChecker = parseAsserts(method);
		this.assertCount= assertChecker.assertCount;
		this.assertNotNullCount = assertChecker.assertNotNullCount;
		return this.assertCount; 
	}

	private AssertRules parseAsserts(MethodDeclaration method) {
		AssertRules assertChecker = new AssertRules();
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
