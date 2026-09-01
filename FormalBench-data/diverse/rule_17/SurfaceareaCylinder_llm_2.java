import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SurfaceareaCylinder {

	public static Double surfaceareaCylinder(int r, int h) {
		double center = 2 * 3.1415 * r * r;
		double tmp = 2 * 3.1415 * r * h;
		double surfacearea = ((center) + (tmp));
		return surfacearea;
	}
}
