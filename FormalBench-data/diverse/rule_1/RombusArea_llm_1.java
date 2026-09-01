import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RombusArea {

	public static int rombusArea(int size, int n) {
		int r = 0;
		if (size < n) {
			int areas = size;
			size = n;
			n = areas;
		}
		r = (size * n) / 2;
		return r;
	}
}
