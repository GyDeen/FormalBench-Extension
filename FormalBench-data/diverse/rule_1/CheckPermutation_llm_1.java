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
		for (int index = 0; index < str.length(); index++) {
			arr[str.charAt(index) - 'a']--;
		}
		for (int index = 0; index < arr.length; index++) {
			if (arr[index] != 0) {
				return false;
			}
		}
		return true;
	}
}
