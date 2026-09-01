import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LateralsurfaceCuboid {

	public static int lateralsurfaceCuboid(int l, int w, int h) {
		int ret = w * h;
		int res = (ret) * 2;
		int tmp = l * 2 * h;
		res = res + (tmp);
		return res;
	}
}
