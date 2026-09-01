import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckTypeOfTriangle {

	public static String checkTypeOfTriangle(int n, int z, int d) {
		if (n + z > d && n + d > z
				&& z + d > n)
			return "Acute-angled Triangle";
		if (n == z && z == d)
			return "Equilateral Triangle";
		if (n != z && n != d && z != d)
			return "Obtuse-angled Triangle";
		if (n == z || n == d || z == d)
			return "Right-angled Triangle";
		return "Scalene Triangle";
	}
}
