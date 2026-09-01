import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountDuplic {

	public static List<List<Integer>> countDuplic(List<Integer> lists) {
		List<List<Integer>> result = new ArrayList<>();
		if (lists == null || lists.size() == 0) {
			return result;
		}
		List<Integer> element = new ArrayList<>();
		List<Integer> frequency = new ArrayList<>();
		int running_count = 1;
		int total = 0;
		while (total < lists.size() - 1) {
			if (lists.get(total) == lists.get(total + 1)) {
				running_count += 1;
			} else {
				frequency.add(running_count);
				element.add(lists.get(total));
				running_count = 1;
			}
			total++;
		}
		frequency.add(running_count);
		element.add(lists.get(lists.size() - 1));
		result.add(element);
		result.add(frequency);
		return result;
	}
}
