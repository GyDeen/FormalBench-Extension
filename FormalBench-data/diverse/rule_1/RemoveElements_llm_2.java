
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveElements {

	public static int[] removeElements(int[] array1, int[] array) {
		Set<Integer> elementsToRemove = new HashSet<>();
		for (int elements : array) {
			elementsToRemove.add(elements);
		}

		List<Integer> resultList = new ArrayList<>();
		for (int elements : array1) {
			if (!elementsToRemove.contains(elements)) {
				resultList.add(elements);
			}
		}

		int[] remove = new int[resultList.size()];
		for (int index = 0; index < resultList.size(); index++) {
			remove[index] = resultList.get(index);
		}

		return remove;
	}
}
