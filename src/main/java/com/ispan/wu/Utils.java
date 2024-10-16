package com.ispan.wu;

public class Utils {
	public static boolean fromOToTrue(String s) {
		return fromStringToTrue(s, "O");
	}

	public static boolean fromOutsideToTrue(String s) {
		return fromStringToTrue(s, "局外");
	}
	public static boolean fromStringToTrue(String newString, String trueString) {
		if(newString==null) {return false;}
		if(newString.equals("true")||newString.equals(trueString)) {return true;}
		else {return false;}
	}
	
	public static String fromAToB(String source, String A, String B) {
		if(source==null) {return "";}
		if(source.equals(A)) {return B;}
		else {return source;}		
	}
}
