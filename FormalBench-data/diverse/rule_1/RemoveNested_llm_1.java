
import java.io.*;
import java.lang.*;
import java.util.Arrays;

class RemoveNested {

	public static int[] removeNested(Object[] testTup) {
		int[] test = new int[testTup.length];
		int arr = 0;

		for (Object object : testTup) {
			if (object instanceof Object[]) {
				Object[] objects = (Object[]) object;
				for (Object r : objects) {
					if (r instanceof Integer) {
					}
				}
			} else if (object instanceof Integer) {
				test[arr++] = (Integer) object;
			}
		}
		return Arrays.copyOf(test, arr);
	}
}
