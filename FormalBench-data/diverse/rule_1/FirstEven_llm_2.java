
import java.io.*;
import java.lang.*;
import java.math.*;

class FirstEven {

	public static int firstEven(int[] args) {
		int result = 0;
		for (int index = 0; index < args.length; index++) {
			if (args[index] % 2 == 0) {
				result = args[index];
				break;
			}
		}
		return result;
	}
}
