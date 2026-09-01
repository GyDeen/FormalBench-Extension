
import java.io.*;
import java.lang.*;
import java.math.*;

class CheckNone {

	public static Boolean checkNone(Integer[] args) {
		for (int index = 0; index < args.length; index++) {
			if (args[index] == null) {
				return true;
			}
		}
		return false;
	}
}
