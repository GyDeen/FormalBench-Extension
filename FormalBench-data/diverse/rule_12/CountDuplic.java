import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountDuplic {

	public static List<List<Integer>> countDuplic(List<Integer> lists) {
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> element = new ArrayList<>();
		if (lists == null || lists.size() == 0) {
			return result;
		}
		int running_count = 1;
		List<Integer> frequency = new ArrayList<>();
		for (int i = 0; i < lists.size() - 1; i++) {
			if (lists.get(i) == lists.get(i + 1)) {
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
