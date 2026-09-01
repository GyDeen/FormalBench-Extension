
import java.io.*;
import java.lang.*;
import java.math.*;

class CountElim {

	public static int countElim(Object[] objects) {
		int result = 0;

		for (Object object : objects) {

			if (object instanceof Object[]) {
				return result;
			}

			result++;
		}

		return result;
	}
}
