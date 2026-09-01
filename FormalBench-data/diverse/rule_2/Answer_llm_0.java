import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Answer {

	public static List<Integer> answer(int l, int r) {
		List<Integer> result = new ArrayList<>();

		int i = 1;
		while (i < r - l + 1) {
			int temp = l * i;
			if (temp < r) {
				result.add(temp);
			} else {
				break;
			}
			i++;
		}
		return result;
	}
}
