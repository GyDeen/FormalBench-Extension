
import java.io.*;
import java.lang.*;
import java.math.*;

class CheckNone {

	public static Boolean checkNone(Integer[] args) {
		for (int test = 0; test < args.length; test++) {
			if (args[test] == null) {
				return true;
			}
		}
		return false;
	}
}
