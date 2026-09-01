import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckValue {

	public static Boolean checkValue(HashMap<String, Integer> hash, int value) {
		for (String string : hash.keySet()) {
			if (hash.get(string) != value) {
				return false;
			}
		}
		return true;
	}
}
