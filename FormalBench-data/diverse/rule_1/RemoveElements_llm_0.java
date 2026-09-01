
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveElements {

	public static int[] removeElements(int[] array1, int[] array) {
		Set<Integer> elementsToRemove = new HashSet<>();
		for (int element : array) {
			elementsToRemove.add(element);
		}

		List<Integer> resultList = new ArrayList<>();
		for (int element : array1) {
			if (!elementsToRemove.contains(element)) {
				resultList.add(element);
			}
		}

		int[] remove = new int[resultList.size()];
		for (int index = 0; index < resultList.size(); index++) {
			remove[index] = resultList.get(index);
		}

		return remove;
	}
}
