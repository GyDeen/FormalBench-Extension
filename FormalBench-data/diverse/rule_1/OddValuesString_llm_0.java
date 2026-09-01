import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class OddValuesString {

	public static String oddValuesString(String string) {
		String odd = "";
		for (int index = 0; index < string.length(); index++) {
			if (index % 2 == 0) {
				odd += string.charAt(index);
			}
		}
		return odd;
	}
}
