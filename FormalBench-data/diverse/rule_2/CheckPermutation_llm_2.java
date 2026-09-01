import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckPermutation {

	public static Boolean checkPermutation(String str1, String str2) {
		int[] letters = new int[26];
		int array = 0;
		while (array < str1.length()) {
			letters[str1.charAt(array) - 'a']++;
			array++;
		}
		int array = 0;
		while (array < str2.length()) {
			letters[str2.charAt(array) - 'a']--;
			array++;
		}
		int array = 0;
		while (array < letters.length) {
			if (letters[array] != 0) {
				return false;
			}
			array++;
		}
		return true;
	}
}
