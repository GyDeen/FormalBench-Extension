import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Sequence {

	public static int sequence(int seq) {
		if (seq == 1 || seq == 2) {
			return 1;
		} else {
			return sequence(sequence(seq - 1)) + sequence(seq - sequence(seq - 1));
		}
	}
}
