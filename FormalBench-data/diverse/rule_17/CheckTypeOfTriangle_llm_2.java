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
		boolean tmp = a == b;
		boolean j = b == c;
		if (tmp && j)
			return "Equilateral Triangle";
		boolean ret = a != b && a != c;
		if (ret && b != c)
			return "Obtuse-angled Triangle";
		if (a == b || a == c || b == c)
			return "Right-angled Triangle";
		return "Scalene Triangle";
	}
}
