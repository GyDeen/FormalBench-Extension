import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Sequence {

	public static int sequence(int num) {
		if (num == 1 || num == 2) {
			return 1;
		} else {
			return sequence(sequence(num - 1)) + sequence(num - sequence(num - 1));
		}
	}
}
