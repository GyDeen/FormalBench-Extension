import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MultiList {

	public static List<List<Integer>> multiList(int size, int num) {
		List<List<Integer>> multi = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			List<Integer> list = new ArrayList<>();
			for (int col = 0; col < num; col++) {
				list.add(i * col);
			}
			multi.add(list);
		}
		return multi;
	}
}
