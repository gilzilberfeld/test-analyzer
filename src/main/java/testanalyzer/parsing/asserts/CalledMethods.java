package testanalyzer.parsing.asserts;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalledMethods {
	Map<String, CalledMethodInfo> called = new HashMap<String, CalledMethodInfo>();

	public List<String> listToFillFor(String caller) {
		CalledMethodInfo info = new CalledMethodInfo(caller);
		called.put(caller, info);
		return info.getCalls();	
	}
	
	public List<String> listFor(String caller) {
		CalledMethodInfo info = called.getOrDefault(caller, CalledMethodInfo.CreateMissingMethod()); 
		return  info.getCalls();
	}
}
