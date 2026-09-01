import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckEquilateral {

	public static Boolean checkEquilateral(int x, int y, int z) {
		boolean check = x == y && y == z;
		boolean tmp = x == z && y == z;
		boolean result = x == y && x == z;
		if ((check) || (tmp) || (result)) {
			return true;
		} else {
			return false;
		}
	}
}
