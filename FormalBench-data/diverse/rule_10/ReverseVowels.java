import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ReverseVowels {

	public static String reverseVowels(String str1) {
        String vowels;
		vowels = "aeiouAEIOU"
		char[] str;
		str = str1.toCharArray()
		int j = str.length - 1;
		int i = 0;
		while (i < j) {
            while (i < j && !vowels.contains(String.valueOf(str[i]))) {
                i++;
            }
            while (i < j && !vowels.contains(String.valueOf(str[j]))) {
                j--;
            }
            char temp;
			temp = str[i]
			str[i] = str[j];
            str[j] = temp;
            i++;
            j--;
        }
        return new String(str);
    }
}
