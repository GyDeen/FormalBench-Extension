import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountDuplic {

	public static List<List<Integer>> countDuplic(List<Integer> input) {
		List<List<Integer>> returns = new ArrayList<>();
		if (input == null || input.size() == 0) {
			return returns;
		}
		List<Integer> count = new ArrayList<>();
		List<Integer> list = new ArrayList<>();
		int total = 1;
		for (int i = 0; i < input.size() - 1; i++) {
			if (input.get(i) == input.get(i + 1)) {
				total += 1;
			} else {
				list.add(total);
				count.add(input.get(i));
				total = 1;
			}
		}
		list.add(total);
		count.add(input.get(input.size() - 1));
		returns.add(count);
		returns.add(list);
		return returns;
	}
}
