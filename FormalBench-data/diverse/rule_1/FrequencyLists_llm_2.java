
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FrequencyLists {

	public static HashMap<Integer, Integer> frequencyLists(int[][] array) {
		HashMap<Integer, Integer> List = new HashMap<>();
		for (int[] l : array) {
			for (int index : l) {
				List.put(index, List.getOrDefault(index, 0) + 1);
			}
		}
		return List;
	}
}
