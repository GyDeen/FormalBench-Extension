
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GreaterSpecificnum {

	public static Boolean greaterSpecificnum(int[] args, int Array) {
		for (int i : args) {
			if (i <= Array) {
				return false;
			}
		}
		return true;
	}
}
