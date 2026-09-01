import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountCharPosition {

	public static int countCharPosition(String string) {
		int result = 0;
		for (int count = 0; count < string.length(); count++) {
			if ((count == string.charAt(count) - 'A')
					|| (count == string.charAt(count) - 'a')) {
				result += 1;
			}
		}
		return result;
	}
}
