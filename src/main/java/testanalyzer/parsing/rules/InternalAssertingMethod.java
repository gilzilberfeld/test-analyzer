package testanalyzer.parsing.rules;

import com.github.javaparser.ast.body.MethodDeclaration;

public class InternalAssertingMethod {

	public int assertCount;
	public String methodName;
	
	public InternalAssertingMethod(MethodDeclaration method) {
		methodName = method.getNameAsString();
	}

	public int getNumberOfAsserts() {
		return assertCount ;
	}

}
