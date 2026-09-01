import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountRectangles {

	public static int countRectangles(int radius) {
		int rectangles = 0;
		int diameter = 2 * radius;
		int diameterSquare = diameter * diameter;
		int area = 1;
		while (area <= 2 * radius) {
			for (int b = 1; b <= 2 * radius; b++) {
				int diagnalLengthSquare = (area * area + b * b);
				if (diagnalLengthSquare <= diameterSquare) {
					rectangles++;
				}
			}
			area++;
		}
		return rectangles;
	}
}
