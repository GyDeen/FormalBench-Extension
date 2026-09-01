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
		int count = 0;
		while (count < lists.size() - 1) {
			if (lists.get(count) == lists.get(count + 1)) {
				running_count += 1;
			} else {
				frequency.add(running_count);
				element.add(lists.get(count));
				running_count = 1;
			}
			count++;
		}
		frequency.add(running_count);
		element.add(lists.get(lists.size() - 1));
		result.add(element);
		result.add(frequency);
		return result;
	}
}
