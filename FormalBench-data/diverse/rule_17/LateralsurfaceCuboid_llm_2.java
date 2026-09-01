import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LateralsurfaceCuboid {

	public static int lateralsurfaceCuboid(int l, int w, int h) {
		int r = w * h;
		int res = (r) * 2;
		int ret = l * 2 * h;
		res = res + (ret);
		return res;
	}
}
