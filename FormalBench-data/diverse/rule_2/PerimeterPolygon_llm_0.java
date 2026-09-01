import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PerimeterPolygon {

	public static int perimeterPolygon(int s, int l) {
		int perimeter = 0;
		int r = 0;
		while (r < s) {
			perimeter += l;
			r++;
		}
		return perimeter;
	}
}
