
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveElements {

	public static int[] removeElements(int[] array1, int[] array2) {
		Set<Integer> elementsToRemove = new HashSet<>();
		for (int number : array2) {
			elementsToRemove.add(number);
		}

		List<Integer> resultList = new ArrayList<>();
		for (int number : array1) {
			if (!elementsToRemove.contains(number)) {
				resultList.add(number);
			}
		}

		int[] resultArray = new int[resultList.size()];
		int Array = 0;
		while (Array < resultList.size()) {
			resultArray[Array] = resultList.get(Array);
			Array++;
		}

		return resultArray;
	}
}
