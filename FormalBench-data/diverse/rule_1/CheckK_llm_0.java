
import java.io.*;
import java.lang.*;
import java.math.*;

class CheckK {

	public static Boolean checkK(int[] args, int test) {
		for (int index : args) {
			if (index == test) {
				return true;
			}
		}
		return false;
	}
}
