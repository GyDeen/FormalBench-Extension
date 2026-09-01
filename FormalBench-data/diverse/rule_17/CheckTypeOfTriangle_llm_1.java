import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckTypeOfTriangle {

	public static String checkTypeOfTriangle(int a, int b, int c) {
		boolean result = a + b > c && a + c > b;
		boolean temp = b + c > a;
		if (result && temp)
			return "Acute-angled Triangle";
		boolean ret = a == b;
		boolean tmp = b == c;
		if (ret && tmp)
			return "Equilateral Triangle";
		boolean test = a != b && a != c;
		if (test && b != c)
			return "Obtuse-angled Triangle";
		if (a == b || a == c || b == c)
			return "Right-angled Triangle";
		return "Scalene Triangle";
	}
}
