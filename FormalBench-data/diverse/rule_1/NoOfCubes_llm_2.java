import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NoOfCubes {

	public static int noOfCubes(int size, int m) {
		int N = 0;
		N = (size - m + 1) * (size - m + 1)
				* (size - m + 1);
		return N;
	}
}
