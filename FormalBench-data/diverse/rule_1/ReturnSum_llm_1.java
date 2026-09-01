import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ReturnSum {

	public static int returnSum(HashMap<String, Integer> hash) {
		int ret = 0;
		for (Map.Entry<String, Integer> e : hash.entrySet()) {
			ret += e.getValue();
		}
		return ret;
	}
}
