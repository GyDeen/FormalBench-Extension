import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckPermutation {

	public static Boolean checkPermutation(String str1, String str2) {
		int[] letters = new int[26];
		int num = 0;
		while (num < str1.length()) {
			letters[str1.charAt(num) - 'a']++;
			num++;
		}
		int num = 0;
		while (num < str2.length()) {
			letters[str2.charAt(num) - 'a']--;
			num++;
		}
		int num = 0;
		while (num < letters.length) {
			if (letters[num] != 0) {
				return false;
			}
			num++;
		}
		return true;
	}
}
