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
		List<Integer> elements = new ArrayList<>();
		int count = 1;
		for (int i = 0; i < input.size() - 1; i++) {
			if (input.get(i) == input.get(i + 1)) {
				count += 1;
			} else {
				elements.add(count);
				list.add(input.get(i));
				count = 1;
			}
		}
		elements.add(count);
		list.add(input.get(input.size() - 1));
		returns.add(list);
		returns.add(elements);
		return returns;
	}
}
