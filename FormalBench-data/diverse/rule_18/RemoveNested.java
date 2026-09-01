
import java.io.*;
import java.lang.*;
import java.util.Arrays;

class RemoveNested {

	public static int[] removeNested(Object[] testTup) {
		int[] temp = new int[testTup.length];
		int count = 0;

		for (Object obj : testTup) {
			if (obj instanceof Object[]) {
				Object[] l = (Object[]) obj;
				for (Object e : l) {
					if (e instanceof Integer) {
					}
				}
			} else if (obj instanceof Integer) {
				temp[count] = (Integer) obj;
				count++;
			}
		}
		return Arrays.copyOf(temp, count);
	}
}
