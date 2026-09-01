
import java.util.HashMap;

class MostOccurrences {

	public static String mostOccurrences(String[] string) {
		HashMap<String, Integer> result = new HashMap<>();
		for (String s : string) {
			String[] words = s.split(" ");
			for (String w : words) {
				result.put(w, result.getOrDefault(w, 0) + 1);
			}
		}

		int max = 0;
		String longest = "";
		for (HashMap.Entry<String, Integer> entries : result.entrySet()) {
			if (entries.getValue() > max) {
				max = entries.getValue();
				longest = entries.getKey();
			}
		}
		return longest;
	}
}
