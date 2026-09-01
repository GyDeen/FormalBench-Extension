import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckPermutation {

	public static Boolean checkPermutation(String str1, String str2) {
		int[] letters = new int[26];
		int index = 0;
		while (index < str1.length()) {
			letters[str1.charAt(index) - 'a']++;
			index++;
		}
		int index = 0;
		while (index < str2.length()) {
			letters[str2.charAt(index) - 'a']--;
			index++;
		}
		int index = 0;
		while (index < letters.length) {
			if (letters[index] != 0) {
				return false;
			}
			index++;
		}
		return true;
	}
}
