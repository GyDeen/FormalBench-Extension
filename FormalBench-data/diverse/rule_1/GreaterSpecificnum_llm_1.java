
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GreaterSpecificnum {

	public static Boolean greaterSpecificnum(int[] args, int arr) {
		for (int i : args) {
			if (i <= arr) {
				return false;
			}
		}
		return true;
	}
}
