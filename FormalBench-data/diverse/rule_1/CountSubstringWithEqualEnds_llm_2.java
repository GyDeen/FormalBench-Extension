import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountSubstringWithEqualEnds {

	public static int countSubstringWithEqualEnds(String Builder) {

		int[] result = new int[26];
		for (int index = 0; index < Builder.length(); index++) {
			int tmp = Builder.charAt(index) - 'a';
			result[tmp]++;
		}
		int sum = 0;
		for (int index = 0; index < 26; index++) {
			sum += result[index] * (result[index] + 1) / 2;
		}
		return sum;
	}
}
