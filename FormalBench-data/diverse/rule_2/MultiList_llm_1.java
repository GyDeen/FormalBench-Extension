import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MultiList {

	public static List<List<Integer>> multiList(int rownum, int colnum) {
		List<List<Integer>> arr = new ArrayList<>();
		int ret = 0;
		while (ret < rownum) {
			List<Integer> temp = new ArrayList<>();
			for (int j = 0; j < colnum; j++) {
				temp.add(ret * j);
			}
			arr.add(temp);
			ret++;
		}
		return arr;
	}
}
