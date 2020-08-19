package testanalyzer.parsing.asserts;

import java.util.List;

import com.github.javaparser.ast.body.MethodDeclaration;

import testanalyzer.model.TestInfo;
import testanalyzer.parsing.InternalMethodCollector;
import testanalyzer.parsing.checkers.AssertChecker;

public class AssertInfoHelper {
	AssertingMethods assertingMethods = new AssertingMethods();
	CalledMethods calledMethods = new CalledMethods();
	private AssertCountInfo assertInfo = new AssertCountInfo();
	
	
	public void collectCalledMethods(MethodDeclaration method) {
		InternalMethodCollector collector = new InternalMethodCollector();
		method.accept(collector, calledMethods.listToFillFor(method.getNameAsString()));
	}

	public void addInternalAsserts(MethodDeclaration method) {
		assertingMethods.add(method.getNameAsString(), 
				getNumberOfInternalAsserts(method));
	}
	
	public int getNumberOfInternalAsserts(MethodDeclaration method) {
		AssertChecker assertChecker = new AssertChecker();
		method.accept(assertChecker, null);
		return assertChecker.assertCount;
	}
	
	public AssertCountInfo getAssertCountInfo(MethodDeclaration method) {
		parseAsserts(method);
		return assertInfo;
	}

	private void parseAsserts(MethodDeclaration method) {
		AssertChecker assertChecker = new AssertChecker();
		method.accept(assertChecker, null);
		assertInfo.assertCount= assertChecker.assertCount;
		assertInfo.assertNotNullCount = assertChecker.assertNotNullCount;
		assertInfo.assertOnStatusCount = assertChecker.assertOnStatusCount;
	}

	public void updateTestInfos(List<TestInfo> tests) {
		tests.forEach( ti -> {
			calledMethods.listFor(ti.testName)
				.forEach( calledMethod -> {
					ti.totalAssertCount += 
							assertingMethods.getAssertCountFor(calledMethod); 
			});
		});
	}

}
