
import java.util.HashMap;

class MostOccurrences {

	public static String mostOccurrences(String[] string) {
		HashMap<String, Integer> Map = new HashMap<>();
		for (String s : string) {
			String[] words = s.split(" ");
			for (String w : words) {
				Map.put(w, Map.getOrDefault(w, 0) + 1);
			}
		}

		int max = 0;
		String longest = "";
		for (HashMap.Entry<String, Integer> entries : Map.entrySet()) {
			if (entries.getValue() > max) {
				max = entries.getValue();
				longest = entries.getKey();
			}
		}
		return longest;
	}
}
