
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ExtractEven {

	public static Object[] extractEven(Object[] testTuple) {
		List<Object> resList = new ArrayList<>();
		for (Object e : testTuple) {
			if (e instanceof Object[]) {
				Object[] temp = extractEven((Object[]) e);
				if (1 == temp.length)
					resList.add(temp[0]);
				else
					resList.add(temp);
			} else if (e instanceof Integer && 0 == ((Integer) e) % 2)
				resList.add(e);
		}
		return resList.toArray();
	}
}
