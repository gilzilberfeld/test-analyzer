package testanalyzer.parsing;

import java.util.List;

import com.github.javaparser.ast.body.MethodDeclaration;

import testanalyzer.Quality;

public class QualityCounter {

	public List<Quality> QualityData;
//	
//	@Override
//	public void visit(MethodDeclaration method, Void arg) {
//		if (isTest(method)) {
//			if (!Ignored(method)) {
//				numberOfTests++;
//			}
//        }
//	}
//
//	private boolean Ignored(MethodDeclaration method) {
//		return method.getAnnotationByName("Disabled").isPresent();
//	}
//
//	private boolean isTest(MethodDeclaration method) {
//		return method.getAnnotationByName("Test").isPresent();
//	}
//

}
