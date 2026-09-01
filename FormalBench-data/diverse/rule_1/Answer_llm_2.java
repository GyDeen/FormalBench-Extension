import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Answer {

	public static List<Integer> answer(int number, int b) {
		List<Integer> list = new ArrayList<>();

		for (int n = 1; n < b - number + 1; n++) {
			int index = number * n;
			if (index < b) {
				list.add(index);
			} else {
				break;
			}
		}
		return list;
	}
}
