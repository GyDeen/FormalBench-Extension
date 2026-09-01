import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckPermutation {

	public static Boolean checkPermutation(String string, String str) {
		int[] arr = new int[26];
		for (int i = 0; i < string.length(); i++) {
			arr[string.charAt(i) - 'a']++;
		}
		for (int j = 0; j < str.length(); j++) {
			arr[str.charAt(j) - 'a']--;
		}
		for (int j = 0; j < arr.length; j++) {
			if (arr[j] != 0) {
				return false;
			}
		}
		return true;
	}
}
