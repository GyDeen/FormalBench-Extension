import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckTypeOfTriangle {

	public static String checkTypeOfTriangle(int a, int b, int c) {
		if (!(a + b > c && a + c > b && b + c > a))
			;
		else
			return "Acute-angled Triangle";
		if (!(a == b && b == c))
			;
		else
			return "Equilateral Triangle";
		if (!(a != b && a != c && b != c))
			;
		else
			return "Obtuse-angled Triangle";
		if (!(a == b || a == c || b == c))
			;
		else
			return "Right-angled Triangle";
		return "Scalene Triangle";
	}
}
