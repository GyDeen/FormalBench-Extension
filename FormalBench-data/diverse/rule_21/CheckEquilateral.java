import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckEquilateral {

	public static Boolean checkEquilateral(int x, int y, int z) {
		if ((y == x && z == y) || (z == x && z == y) || (y == x && z == x)) {
			return true;
		} else {
			return false;
		}
	}
}
