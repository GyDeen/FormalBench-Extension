
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GreaterSpecificnum {

	public static Boolean greaterSpecificnum(int[] array, int num) {
		for (int value : array) {
			if (!(value <= num))
				;
			else {
				return false;
			}
		}
		return true;
	}
}
