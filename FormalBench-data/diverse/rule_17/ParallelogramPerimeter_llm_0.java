import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ParallelogramPerimeter {

	public static int parallelogramPerimeter(int b, int h) {
		boolean result = b <= 0;
		boolean tmp = h <= 0;
		if (result || tmp) {
			return 0;
		}
		int p = b * h;
		return 2 * (p);
	}
}
