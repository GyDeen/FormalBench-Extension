import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RombusArea {

	public static int rombusArea(int n, int c) {
		int r = 0;
		if (n < c) {
			int areas = n;
			n = c;
			c = areas;
		}
		r = (n * c) / 2;
		return r;
	}
}
