
import java.io.*;
import java.lang.*;
import java.util.Arrays;

class RemoveNested {

	public static int[] removeNested(Object[] testTup) {
		int count = 0;
		int[] temp = new int[testTup.length];

		for (Object obj : testTup) {
			if (obj instanceof Object[]) {
				Object[] l = (Object[]) obj;
				for (Object e : l) {
					if (e instanceof Integer) {
					}
				}
			} else if (obj instanceof Integer) {
				temp[count++] = (Integer) obj;
			}
		}
		return Arrays.copyOf(temp, count);
	}
}
