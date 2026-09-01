import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Answer {

	public static List<Integer> answer(int l, int r) {
		List<Integer> result = new ArrayList<>();

		int index = 1;
		while (index < r - l + 1) {
			int temp = l * index;
			if (temp < r) {
				result.add(temp);
			} else {
				break;
			}
			index++;
		}
		return result;
	}
}
