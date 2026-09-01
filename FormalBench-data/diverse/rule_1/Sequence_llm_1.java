import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Sequence {

	public static int sequence(int length) {
		if (length == 1 || length == 2) {
			return 1;
		} else {
			return sequence(sequence(length - 1)) + sequence(length - sequence(length - 1));
		}
	}
}
