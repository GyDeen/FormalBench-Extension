import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SurfaceareaCone {

	public static Double surfaceareaCone(int r, int h) {
		int ret = r * r;
		int tmp = h * h;
		double l = Math.sqrt(ret + tmp);
		double s = r + l;
		double SA = Math.PI * r * (s);
		return SA;
	}
}
