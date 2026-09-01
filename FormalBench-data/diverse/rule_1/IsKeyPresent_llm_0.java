import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsKeyPresent {

	public static Boolean isKeyPresent(HashMap<Integer, Integer> map, int key) {
		if (map.containsKey(key)) {
			return true;
		}
		return false;
	}
}
