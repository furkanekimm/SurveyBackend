package com.example.survey.helper;

public class CoreUtil {
	public static boolean isEmptyString(String string) {
		if(string == null || string.isEmpty()) {
			return true;
		}
		return false;
	}
	
	public static boolean isEmptyObject(Object object) {
		if(object != null) {
			return false;
		}
		return true;
	}
	
	public static boolean checkPointRange(Integer point) {
		if(point != null && point >=0 && point<=10) {
			return true;
		}
		return false;
	}
	
	
}
