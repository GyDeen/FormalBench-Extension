
import java.io.*;
import java.lang.*;
import java.util.Arrays;

class RemoveNested {

	public static int[] removeNested(Object[] testTup) {
		int[] temp = new int[testTup.length];
		int count = 0;

		for (Object obj : testTup) {
			if (!(obj instanceof Object[])) {
				if (obj instanceof Integer) {
					temp[count++] = (Integer) obj;
				}
			} else {
				Object[] l = (Object[]) obj;
				for (Object e : l) {
					if (e instanceof Integer) {
					}
				}
			}
		}
		return Arrays.copyOf(temp, count);
	}
}
