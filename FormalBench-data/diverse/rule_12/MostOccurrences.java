
import java.util.HashMap;

class MostOccurrences {

	public static String mostOccurrences(String[] testArray) {
		HashMap<String, Integer> map = new HashMap<>();
		int max = 0;

		for (String s : testArray) {
			String[] words = s.split(" ");
			for (String word : words) {
				map.put(word, map.getOrDefault(word, 0) + 1);
			}
		}
		String res = "";
		for (HashMap.Entry<String, Integer> entry : map.entrySet()) {
			if (entry.getValue() > max) {
				max = entry.getValue();
				res = entry.getKey();
			}
		}
		return res;
	}
}
