import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RombusArea {

	public static int rombusArea(int p, int q) {
		if (p < q) {
			int d = p;
			p = q;
			q = d;
		}
		int area = 0;
		area = (p * q) / 2;
		return area;
	}
}
