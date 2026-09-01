import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SurfaceareaCylinder {

	public static Double surfaceareaCylinder(int r, int h) {
		double surface = 2 * 3.1415 * r * r;
		double temp = 2 * 3.1415 * r * h;
		double surfacearea = ((surface) + (temp));
		return surfacearea;
	}
}
