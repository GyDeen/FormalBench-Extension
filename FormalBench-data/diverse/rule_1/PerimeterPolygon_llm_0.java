import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PerimeterPolygon {

	public static int perimeterPolygon(int radius, int n) {
		int ret = 0;
		for (int p = 0; p < radius; p++) {
			ret += n;
		}
		return ret;
	}
}
