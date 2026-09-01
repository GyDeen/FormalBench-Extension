import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RombusArea {

	public static int rombusArea(int n, int b) {
		int r = 0;
		if (n < b) {
			int areas = n;
			n = b;
			b = areas;
		}
		r = (n * b) / 2;
		return r;
	}
}
