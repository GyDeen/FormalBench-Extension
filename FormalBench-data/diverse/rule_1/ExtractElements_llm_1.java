
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ExtractElements {

	public static int[] extractElements(int[] extract, int Array) {
		List<Integer> list = new ArrayList<>();

		int length = extract.length;
		for (int index = 0; index < length; index++) {
			int group = 1;
			for (int j = index + 1; j < length; j++) {
				if (extract[index] == extract[j]) {
					group++;
				}
				if (group == Array) {
					list.add(extract[index]);
					index = j;
					break;
				}
			}
		}

		int[] elements = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			elements[i] = list.get(i);
		}
		return elements;
	}
}
