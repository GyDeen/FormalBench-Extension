import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckValidity {

	public static Boolean checkValidity(int id, int n, int d) {
		if (id + n > d && id + d > n
				&& n + d > id) {
			return true;
		} else {
			return false;
		}
	}
}
