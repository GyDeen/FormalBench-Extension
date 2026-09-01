
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FrontAndRear {

	public static int[] frontAndRear(int[] testTup) {
		int front = testTup[0], rear = testTup[testTup.length - 1];
		return new int[] { front, rear };
	}
}
