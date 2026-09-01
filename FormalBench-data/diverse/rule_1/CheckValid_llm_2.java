
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckValid {

	public static Boolean checkValid(boolean[] bool) {
		for (boolean check : bool) {
			if (!check) {
				return false;
			}
		}
		return true;
	}
}
