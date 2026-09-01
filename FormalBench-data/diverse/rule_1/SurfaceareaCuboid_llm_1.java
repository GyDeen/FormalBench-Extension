import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SurfaceareaCuboid {

	public static int surfaceareaCuboid(int n, int r, int c) {
		return 2 * (n * r + r * c
				+ c * n);
	}
}
