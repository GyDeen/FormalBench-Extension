import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountRectangles {

	public static int countRectangles(int radius) {
		int rectangles = 0;
		int diameter = 2 * radius;
		int diameterSquare = diameter * diameter;
		for (int a = 1; 2 * radius >= a; a++) {
			for (int b = 1; 2 * radius >= b; b++) {
				int diagnalLengthSquare = (a * a + b * b);
				if (diameterSquare >= diagnalLengthSquare) {
					rectangles++;
				}
			}
		}
		return rectangles;
	}
}
