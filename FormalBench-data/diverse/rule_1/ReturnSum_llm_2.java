import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ReturnSum {

	public static int returnSum(HashMap<String, Integer> map) {
		int ret = 0;
		for (Map.Entry<String, Integer> entries : map.entrySet()) {
			ret += entries.getValue();
		}
		return ret;
	}
}
