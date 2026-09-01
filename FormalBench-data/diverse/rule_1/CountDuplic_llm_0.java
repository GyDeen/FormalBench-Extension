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
		List<Integer> list = new ArrayList<>();
		List<Integer> count = new ArrayList<>();
		int total = 1;
		for (int i = 0; i < input.size() - 1; i++) {
			if (input.get(i) == input.get(i + 1)) {
				total += 1;
			} else {
				count.add(total);
				list.add(input.get(i));
				total = 1;
			}
		}
		count.add(total);
		list.add(input.get(input.size() - 1));
		returns.add(list);
		returns.add(count);
		return returns;
	}
}
