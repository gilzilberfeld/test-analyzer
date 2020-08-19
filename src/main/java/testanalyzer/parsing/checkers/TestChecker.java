package testanalyzer.parsing.checkers;

import java.util.List;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import testanalyzer.model.TestClassInfo;
import testanalyzer.model.TestInfo;
import testanalyzer.parsing.asserts.AssertCountInfo;
import testanalyzer.parsing.asserts.AssertInfoHelper;
import testanalyzer.parsing.rules.TestRules;

public class TestChecker extends VoidVisitorAdapter<Void> {

	private  TestClassInfo testClassInfo;
	AssertInfoHelper assertInfo = new AssertInfoHelper();
	int count = 0;

	public TestChecker(TestClassInfo testClassInfo, AssertInfoHelper assertInfo) {
		this.testClassInfo = testClassInfo;
		this.assertInfo = assertInfo;
	}
	
	@Override
	public void visit(MethodDeclaration method, Void arg) {
		if (TestRules.isTest(method)) {
			if (!TestRules.isIgnored(method)) {
				createTestInfo(method);
				assertInfo.collectCalledMethods(method);
			}
		}
		else 
			assertInfo.addInternalAsserts(method);
	}
	
	
	private void createTestInfo(MethodDeclaration method) {
		TestInfo testInfo = testClassInfo.createTestInfo(method.getNameAsString());
		if (TestRules.hasExpected(method)) {
			testInfo.totalAssertCount = 1;
		}
		AssertCountInfo assertCountInfo = assertInfo.getAssertCountInfo(method);
		testInfo.totalAssertCount += assertCountInfo.assertCount;
		testInfo.assertNotNullCount += assertCountInfo.assertNotNullCount;
		testInfo.assertStatusCount += assertCountInfo.assertOnStatusCount;
		testInfo.type = TestRules.getType(method, testClassInfo.type);
		testClassInfo.incrementTests();
	}


}
