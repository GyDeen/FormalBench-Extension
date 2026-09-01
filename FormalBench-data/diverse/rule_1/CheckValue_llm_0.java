import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckValue {

	public static Boolean checkValue(HashMap<String, Integer> map, int value) {
		for (String string : map.keySet()) {
			if (map.get(string) != value) {
				return false;
			}
		}
		return true;
	}
}
