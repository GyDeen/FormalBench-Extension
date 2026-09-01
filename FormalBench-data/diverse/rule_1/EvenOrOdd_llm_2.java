import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class EvenOrOdd {

	public static String evenOrOdd(String text) {
		int even = Integer.parseInt(text, 16);
		if (even % 2 == 0) {
			return "Even";
		} else {
			return "Odd";
		}
	}
}
