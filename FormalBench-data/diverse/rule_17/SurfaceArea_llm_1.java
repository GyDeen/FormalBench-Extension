import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SurfaceArea {

	public static int surfaceArea(int b, int s) {
		int ret = 2 * s * b;
		int temp = b * b;
		return ret + temp;
	}
}
