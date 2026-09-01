
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MoveFirst {

	public static int[] moveFirst(int[] args) {
		if (args == null || args.length == 0) {
			return args;
		}

		int[] test = new int[args.length];
		test[0] = args[args.length - 1];

		System.arraycopy(args, 0, test, 1, args.length - 1);

		return test;
	}
}
