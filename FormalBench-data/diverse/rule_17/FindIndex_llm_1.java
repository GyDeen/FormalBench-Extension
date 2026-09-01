import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindIndex {

	public static int findIndex(int n) {
		int result = n - 1;
		int x = (int) Math.round(Math.sqrt(2 * Math.pow(10, (result))));
		return x;
	}
}
