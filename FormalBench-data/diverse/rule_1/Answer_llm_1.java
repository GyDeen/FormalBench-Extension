import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Answer {

	public static List<Integer> answer(int number, int n) {
		List<Integer> list = new ArrayList<>();

		for (int p = 1; p < n - number + 1; p++) {
			int j = number * p;
			if (j < n) {
				list.add(j);
			} else {
				break;
			}
		}
		return list;
	}
}
