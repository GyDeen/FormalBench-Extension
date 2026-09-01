import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NoOfCubes {

	public static int noOfCubes(int n, int k) {
		int no = 0;
		int so = n - k + 1;
		int tmp = n - k + 1;
		int max = n - k + 1;
		no = (so) * (tmp) * (max);
		return no;
	}
}
