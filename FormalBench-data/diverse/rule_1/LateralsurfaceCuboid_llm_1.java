import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LateralsurfaceCuboid {

	public static int lateralsurfaceCuboid(int num, int r, int n) {
		int ret = (r * n) * 2;
		ret = ret + (num * 2 * n);
		return ret;
	}
}
