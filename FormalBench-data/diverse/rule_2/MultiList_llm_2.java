import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MultiList {

	public static List<List<Integer>> multiList(int rownum, int colnum) {
		List<List<Integer>> arr = new ArrayList<>();
		int i = 0;
		while (i < rownum) {
			List<Integer> temp = new ArrayList<>();
			for (int j = 0; j < colnum; j++) {
				temp.add(i * j);
			}
			arr.add(temp);
			i++;
		}
		return arr;
	}
}
