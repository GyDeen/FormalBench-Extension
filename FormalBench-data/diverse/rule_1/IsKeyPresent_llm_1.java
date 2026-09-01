import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsKeyPresent {

	public static Boolean isKeyPresent(HashMap<Integer, Integer> map, int k) {
		if (map.containsKey(k)) {
			return true;
		}
		return false;
	}
}
