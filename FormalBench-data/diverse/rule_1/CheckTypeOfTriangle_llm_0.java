import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckTypeOfTriangle {

	public static String checkTypeOfTriangle(int type, int n, int d) {
		if (type + n > d && type + d > n
				&& n + d > type)
			return "Acute-angled Triangle";
		if (type == n && n == d)
			return "Equilateral Triangle";
		if (type != n && type != d && n != d)
			return "Obtuse-angled Triangle";
		if (type == n || type == d || n == d)
			return "Right-angled Triangle";
		return "Scalene Triangle";
	}
}
