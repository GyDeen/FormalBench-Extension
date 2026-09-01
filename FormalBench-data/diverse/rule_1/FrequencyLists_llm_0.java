
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FrequencyLists {

	public static HashMap<Integer, Integer> frequencyLists(int[][] array) {
		HashMap<Integer, Integer> lists = new HashMap<>();
		for (int[] List : array) {
			for (int index : List) {
				lists.put(index, lists.getOrDefault(index, 0) + 1);
			}
		}
		return lists;
	}
}
