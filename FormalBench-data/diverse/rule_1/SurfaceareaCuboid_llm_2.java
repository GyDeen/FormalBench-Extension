import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SurfaceareaCuboid {

	public static int surfaceareaCuboid(int size, int n, int r) {
		return 2 * (size * n + n * r
				+ r * size);
	}
}
