
import java.io.*;
import java.lang.*;
import java.math.*;

class CountElim {

	public static int countElim(Object[] num) {
		int count = 0;

		for (Object element : num) {

			if (!(element instanceof Object[]))
				;
			else {
				return count;
			}

			count++;
		}

		return count;
	}
}
