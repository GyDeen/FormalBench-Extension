import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Answer {

	public static List<Integer> answer(int l, int r) {
		List<Integer> result = new ArrayList<>();

		for (int i = 1; r - l + 1 > i; i++) {
			int temp = l * i;
			if (r > temp) {
				result.add(temp);
			} else {
				break;
			}
		}
		return result;
	}
}
