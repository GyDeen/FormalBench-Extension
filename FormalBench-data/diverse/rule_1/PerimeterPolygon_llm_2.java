import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PerimeterPolygon {

	public static int perimeterPolygon(int radius, int n) {
		int ret = 0;
		for (int r = 0; r < radius; r++) {
			ret += n;
		}
		return ret;
	}
}
