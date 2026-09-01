import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Answer {

	public static List<Integer> answer(int number, int b) {
		List<Integer> list = new ArrayList<>();

		for (int n = 1; n < b - number + 1; n++) {
			int j = number * n;
			if (j < b) {
				list.add(j);
			} else {
				break;
			}
		}
		return list;
	}
}
