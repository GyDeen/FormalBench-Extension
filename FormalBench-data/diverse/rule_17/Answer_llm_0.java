import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Answer {

	public static List<Integer> answer(int l, int r) {
		List<Integer> result = new ArrayList<>();

		int n = r - l + 1;
		for (int i = 1; i < n; i++) {
			int temp = l * i;
			if (temp < r) {
				result.add(temp);
			} else {
				break;
			}
		}
		return result;
	}
}
