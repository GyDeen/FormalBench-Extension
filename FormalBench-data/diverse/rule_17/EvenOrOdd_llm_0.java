import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class EvenOrOdd {

	public static String evenOrOdd(String n) {
		int i = Integer.parseInt(n, 16);
		int result = i % 2;
		if (result == 0) {
			return "Even";
		} else {
			return "Odd";
		}
	}
}
