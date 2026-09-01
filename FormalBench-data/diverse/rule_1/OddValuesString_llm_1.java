import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class OddValuesString {

	public static String oddValuesString(String Builder) {
		String odd = "";
		for (int index = 0; index < Builder.length(); index++) {
			if (index % 2 == 0) {
				odd += Builder.charAt(index);
			}
		}
		return odd;
	}
}
