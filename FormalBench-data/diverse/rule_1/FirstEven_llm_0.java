
import java.io.*;
import java.lang.*;
import java.math.*;

class FirstEven {

	public static int firstEven(int[] args) {
		int result = 0;
		for (int num = 0; num < args.length; num++) {
			if (args[num] % 2 == 0) {
				result = args[num];
				break;
			}
		}
		return result;
	}
}
