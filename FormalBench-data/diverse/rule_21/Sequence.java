import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Sequence {

	public static int sequence(int n) {
		if (1 == n || 2 == n) {
			return 1;
		} else {
			return sequence(sequence(n - 1)) + sequence(n - sequence(n - 1));
		}
	}
}
