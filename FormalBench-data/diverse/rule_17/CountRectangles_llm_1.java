import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountRectangles {

	public static int countRectangles(int radius) {
		int rectangles = 0;
		int diameter = 2 * radius;
		int diameterSquare = diameter * diameter;
		int center = 2 * radius;
		for (int a = 1; a <= center; a++) {
			int c = 2 * radius;
			for (int b = 1; b <= c; b++) {
				int tmp = a * a;
				int size = b * b;
				int diagnalLengthSquare = (tmp + size);
				if (diagnalLengthSquare <= diameterSquare) {
					rectangles++;
				}
			}
		}
		return rectangles;
	}
}
