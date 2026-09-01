import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PerimeterPolygon {

	public static int perimeterPolygon(int s, int l) {
		int perimeter = 0;
		int radius = 0;
		while (radius < s) {
			perimeter += l;
			radius++;
		}
		return perimeter;
	}
}
