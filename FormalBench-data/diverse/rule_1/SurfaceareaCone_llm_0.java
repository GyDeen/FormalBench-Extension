import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SurfaceareaCone {

	public static Double surfaceareaCone(int n, int g) {
		double result = Math.sqrt(n * n + g * g);
		double d = Math.PI * n * (n + result);
		return d;
	}
}
