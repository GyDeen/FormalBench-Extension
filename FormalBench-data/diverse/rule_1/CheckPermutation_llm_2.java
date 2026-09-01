import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckPermutation {

	public static Boolean checkPermutation(String string, String str) {
		int[] result = new int[26];
		for (int i = 0; i < string.length(); i++) {
			result[string.charAt(i) - 'a']++;
		}
		for (int j = 0; j < str.length(); j++) {
			result[str.charAt(j) - 'a']--;
		}
		for (int j = 0; j < result.length; j++) {
			if (result[j] != 0) {
				return false;
			}
		}
		return true;
	}
}
