import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountRectangles {

	public static int countRectangles(int count) {
		int ret = 0;
		int diameter = 2 * count;
		int center = diameter * diameter;
		for (int i = 1; i <= 2 * count; i++) {
			for (int c = 1; c <= 2 * count; c++) {
				int diagnalLengthSquare = (i * i + c * c);
				if (diagnalLengthSquare <= center) {
					ret++;
				}
			}
		}
		return ret;
	}
}
