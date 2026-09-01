import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PerimeterPolygon {

	public static int perimeterPolygon(int radius, int c) {
		int ret = 0;
		for (int n = 0; n < radius; n++) {
			ret += c;
		}
		return ret;
	}
}
