import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AreaPentagon {

	public static Double areaPentagon(int p) {
		double b = (5 * Math.pow(p, 2)) / (4 * Math.tan(Math.PI / 5));
		return b;
	}
}
