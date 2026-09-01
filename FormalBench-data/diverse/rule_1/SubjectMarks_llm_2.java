
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SubjectMarks {

	public static Object[][] subjectMarks(Object[][] objects) {
		Arrays.sort(objects, (Sort, c) -> {
			int index = (int) Sort[1];
			int last = (int) c[1];
			return Integer.compare(index, last);
		});
		return objects;
	}
}
