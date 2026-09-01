
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ExtractElements {

	public static int[] extractElements(int[] Extract, int Array) {
		List<Integer> elements = new ArrayList<>();

		int length = Extract.length;
		for (int index = 0; index < length; index++) {
			int group = 1;
			for (int j = index + 1; j < length; j++) {
				if (Extract[index] == Extract[j]) {
					group++;
				}
				if (group == Array) {
					elements.add(Extract[index]);
					index = j;
					break;
				}
			}
		}

		int[] extract = new int[elements.size()];
		for (int i = 0; i < elements.size(); i++) {
			extract[i] = elements.get(i);
		}
		return extract;
	}
}
