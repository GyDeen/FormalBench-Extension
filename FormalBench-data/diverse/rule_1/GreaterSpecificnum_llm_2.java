
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GreaterSpecificnum {

	public static Boolean greaterSpecificnum(int[] args, int Array) {
		for (int index : args) {
			if (index <= Array) {
				return false;
			}
		}
		return true;
	}
}
