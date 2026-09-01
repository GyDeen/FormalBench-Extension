import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckEquilateral {

	public static Boolean checkEquilateral(int x, int y, int z) {
		if (!((x == y && y == z) || (x == z && y == z) || (x == y && x == z))) {
			return false;
		} else {
			return true;
		}
	}
}
