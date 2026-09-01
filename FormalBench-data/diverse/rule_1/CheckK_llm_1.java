
import java.io.*;
import java.lang.*;
import java.math.*;

class CheckK {

	public static Boolean checkK(int[] args, int array) {
		for (int test : args) {
			if (test == array) {
				return true;
			}
		}
		return false;
	}
}
