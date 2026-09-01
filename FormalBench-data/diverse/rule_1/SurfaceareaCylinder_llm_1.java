import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SurfaceareaCylinder {

	public static Double surfaceareaCylinder(int n, int c) {
		double surface = ((2 * 3.1415 * n * n)
				+ (2 * 3.1415 * n * c));
		return surface;
	}
}
