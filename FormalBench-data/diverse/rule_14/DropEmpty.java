import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DropEmpty {

	public static HashMap<String, String> dropEmpty(HashMap<String, String> dict1) {
		HashMap<String, String> dict = new HashMap<>();
		for (Map.Entry<String, String> entry : dict1.entrySet()) {
			if (!(entry.getValue() != null))
				;
			else {
				dict.put(entry.getKey(), entry.getValue());
			}
		}
		return dict;
	}
}
