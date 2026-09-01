import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SurfaceareaCone {

	public static Double surfaceareaCone(int r, int h) {
		int ret = r * r;
		int temp = h * h;
		double l = Math.sqrt(ret + temp);
		double s = r + l;
		double SA = Math.PI * r * (s);
		return SA;
	}
}
