import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsKeyPresent {

	public static Boolean isKeyPresent(HashMap<Integer, Integer> maps, int key) {
		if (maps.containsKey(key)) {
			return true;
		}
		return false;
	}
}
