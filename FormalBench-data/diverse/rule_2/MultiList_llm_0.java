import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MultiList {

	public static List<List<Integer>> multiList(int rownum, int colnum) {
		List<List<Integer>> arr = new ArrayList<>();
		int len = 0;
		while (len < rownum) {
			List<Integer> temp = new ArrayList<>();
			for (int j = 0; j < colnum; j++) {
				temp.add(len * j);
			}
			arr.add(temp);
			len++;
		}
		return arr;
	}
}
