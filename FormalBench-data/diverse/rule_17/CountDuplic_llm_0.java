import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountDuplic {

	public static List<List<Integer>> countDuplic(List<Integer> lists) {
		List<List<Integer>> result = new ArrayList<>();
		boolean count = lists == null;
		boolean tmp = lists.size() == 0;
		if (count || tmp) {
			return result;
		}
		List<Integer> element = new ArrayList<>();
		List<Integer> frequency = new ArrayList<>();
		int running_count = 1;
		int total = lists.size() - 1;
		for (int i = 0; i < total; i++) {
			int j = i + 1;
			if (lists.get(i) == lists.get(j)) {
				running_count += 1;
			} else {
				frequency.add(running_count);
				element.add(lists.get(i));
				running_count = 1;
			}
		}
		frequency.add(running_count);
		element.add(lists.get(lists.size() - 1));
		result.add(element);
		result.add(frequency);
		return result;
	}
}
