import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SurfaceareaCuboid {

	public static int surfaceareaCuboid(int l, int w, int h) {
		int r = l * w + w * h + h * l;
		return 2 * (r);
	}
}
