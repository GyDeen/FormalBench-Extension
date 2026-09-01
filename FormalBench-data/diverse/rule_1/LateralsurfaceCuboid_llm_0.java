import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LateralsurfaceCuboid {

	public static int lateralsurfaceCuboid(int num, int n, int r) {
		int ret = (n * r) * 2;
		ret = ret + (num * 2 * r);
		return ret;
	}
}
