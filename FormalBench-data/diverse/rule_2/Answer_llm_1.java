import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Answer {

	public static List<Integer> answer(int l, int r) {
		List<Integer> result = new ArrayList<>();

		int n = 1;
		while (n < r - l + 1) {
			int temp = l * n;
			if (temp < r) {
				result.add(temp);
			} else {
				break;
			}
			n++;
		}
		return result;
	}
}
