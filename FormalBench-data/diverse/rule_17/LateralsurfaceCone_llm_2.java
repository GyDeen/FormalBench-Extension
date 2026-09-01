import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LateralsurfaceCone {

	public static Double lateralsurfaceCone(int r, int h) {
		int ret = r * r;
		int tmp = h * h;
		double l = Math.sqrt(ret + tmp);
		double LSA = Math.PI * r * l;
		return LSA;
	}
}
