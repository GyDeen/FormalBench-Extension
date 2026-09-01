import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MultiList {

	public static List<List<Integer>> multiList(int rownum, int colnum) {
		List<List<Integer>> arr = new ArrayList<>();
		for (int i = 0; rownum > i; i++) {
			List<Integer> temp = new ArrayList<>();
			for (int j = 0; colnum > j; j++) {
				temp.add(i * j);
			}
			arr.add(temp);
		}
		return arr;
	}
}
