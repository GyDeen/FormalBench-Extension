import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckValue {

	public static Boolean checkValue(HashMap<String, Integer> map, int value) {
		for (String str : map.keySet()) {
			if (map.get(str) != value) {
				return false;
			}
		}
		return true;
	}
}
