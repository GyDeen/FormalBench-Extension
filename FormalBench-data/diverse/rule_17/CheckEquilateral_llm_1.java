import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckEquilateral {

	public static Boolean checkEquilateral(int x, int y, int z) {
		boolean check = x == y && y == z;
		boolean temp = x == z && y == z;
		boolean tmp = x == y && x == z;
		if ((check) || (temp) || (tmp)) {
			return true;
		} else {
			return false;
		}
	}
}
