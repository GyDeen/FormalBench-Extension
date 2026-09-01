import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LateralsurfaceCuboid {

	public static int lateralsurfaceCuboid(int num, int n, int k) {
		int ret = (n * k) * 2;
		ret = ret + (num * 2 * k);
		return ret;
	}
}
