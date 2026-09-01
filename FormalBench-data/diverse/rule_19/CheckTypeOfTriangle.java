import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckTypeOfTriangle {

	public static String checkTypeOfTriangle(int a, int b, int c) {
		if (a + b > c && a + c > b) {
			if (b + c > a)
				return "Acute-angled Triangle";
		}
		if (a == b) {
			if (b == c)
				return "Equilateral Triangle";
		}
		if (a != b && a != c) {
			if (b != c)
				return "Obtuse-angled Triangle";
		}
		if (a == b || a == c || b == c)
			return "Right-angled Triangle";
		return "Scalene Triangle";
	}
}
