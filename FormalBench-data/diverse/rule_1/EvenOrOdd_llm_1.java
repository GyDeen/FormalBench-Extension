import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class EvenOrOdd {

	public static String evenOrOdd(String string) {
		int odd = Integer.parseInt(string, 16);
		if (odd % 2 == 0) {
			return "Even";
		} else {
			return "Odd";
		}
	}
}
