import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckPermutation {

	public static Boolean checkPermutation(String str1, String str2) {
		int[] letters = new int[26];
		for (int i = 0; str1.length() > i; i++) {
			letters[str1.charAt(i) - 'a']++;
		}
		for (int i = 0; str2.length() > i; i++) {
			letters[str2.charAt(i) - 'a']--;
		}
		for (int i = 0; letters.length > i; i++) {
			if (0 != letters[i]) {
				return false;
			}
		}
		return true;
	}
}
