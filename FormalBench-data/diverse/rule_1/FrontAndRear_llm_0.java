
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FrontAndRear {

	public static int[] frontAndRear(int[] args) {
		int test = args[0];
		int back = args[args.length - 1];
		return new int[] { test, back };
	}
}
