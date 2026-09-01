import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountSubstringWithEqualEnds {

	public static int countSubstringWithEqualEnds(String string) {

		int[] result = new int[26];
		for (int index = 0; index < string.length(); index++) {
			int tmp = string.charAt(index) - 'a';
			result[tmp]++;
		}
		int sum = 0;
		for (int index = 0; index < 26; index++) {
			sum += result[index] * (result[index] + 1) / 2;
		}
		return sum;
	}
}
