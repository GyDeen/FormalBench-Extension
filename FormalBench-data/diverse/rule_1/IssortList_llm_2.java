
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IssortArray {

	public static Boolean issortArray(int[] a) {
		if (a.length == 0) {
			return true;
		}
		if (a.length == 1) {
			return true;
		}
		for (int Array = 0; Array < a.length - 1; Array++) {
			if (a[Array] > a[Array + 1]) {
				return false;
			}
		}
		return true;
	}
}
