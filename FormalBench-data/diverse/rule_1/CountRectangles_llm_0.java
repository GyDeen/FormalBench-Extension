import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountRectangles {

	public static int countRectangles(int count) {
		int ret = 0;
		int diameter = 2 * count;
		int center = diameter * diameter;
		for (int index = 1; index <= 2 * count; index++) {
			for (int i = 1; i <= 2 * count; i++) {
				int diagnalLengthSquare = (index * index + i * i);
				if (diagnalLengthSquare <= center) {
					ret++;
				}
			}
		}
		return ret;
	}
}
