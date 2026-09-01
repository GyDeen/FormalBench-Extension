import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MultiList {

	public static List<List<Integer>> multiList(int num, int max) {
		List<List<Integer>> multi = new ArrayList<>();
		for (int i = 0; i < num; i++) {
			List<Integer> list = new ArrayList<>();
			for (int col = 0; col < max; col++) {
				list.add(i * col);
			}
			multi.add(list);
		}
		return multi;
	}
}
