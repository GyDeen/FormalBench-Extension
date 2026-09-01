import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckTypeOfTriangle {

	public static String checkTypeOfTriangle(int a, int b, int c) {
		if (c < a + b && b < a + c && a < b + c)
			return "Acute-angled Triangle";
		if (a == b && b == c)
			return "Equilateral Triangle";
		if (b != a && c != a && c != b)
			return "Obtuse-angled Triangle";
		if (a == b || a == c || b == c)
			return "Right-angled Triangle";
		return "Scalene Triangle";
	}
}
