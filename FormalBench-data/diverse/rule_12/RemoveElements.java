
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveElements {

	public static int[] removeElements(int[] array1, int[] array2) {
		Set<Integer> elementsToRemove = new HashSet<>();
		List<Integer> resultList = new ArrayList<>();

		for (int number : array2) {
			elementsToRemove.add(number);
		}
		for (int number : array1) {
			if (!elementsToRemove.contains(number)) {
				resultList.add(number);
			}
		}

		int[] resultArray = new int[resultList.size()];
		for (int i = 0; i < resultList.size(); i++) {
			resultArray[i] = resultList.get(i);
		}

		return resultArray;
	}
}
