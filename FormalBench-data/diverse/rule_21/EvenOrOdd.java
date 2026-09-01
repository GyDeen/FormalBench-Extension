import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class EvenOrOdd {

	public static String evenOrOdd(String n) {
		int i = Integer.parseInt(n, 16);
		if (0 == i % 2) {
			return "Even";
		} else {
			return "Odd";
		}
	}
}
