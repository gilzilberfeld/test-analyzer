package testanalyzer.parsing;

import java.util.ArrayList;
import java.util.List;

public class CalledMethodInfo {

	String caller;
	List<String> calls = new ArrayList<String>();
	
	public CalledMethodInfo(String caller) {
		this.caller = caller;
	}

	public List<String> getCalls() {
		return calls;
	}
	
	public static  CalledMethodInfo CreateMissingMethod() {
		return new CalledMethodInfo("MissingMethod");
	}
}
