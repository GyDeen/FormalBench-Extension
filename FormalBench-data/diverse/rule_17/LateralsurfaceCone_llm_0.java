import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LateralsurfaceCone {

	public static Double lateralsurfaceCone(int r, int h) {
		int i = r * r;
		int tmp = h * h;
		double l = Math.sqrt(i + tmp);
		double LSA = Math.PI * r * l;
		return LSA;
	}
}
