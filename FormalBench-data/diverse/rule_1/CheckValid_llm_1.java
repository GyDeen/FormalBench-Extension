
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckValid {

	public static Boolean checkValid(boolean[] checks) {
		for (boolean test : checks) {
			if (!test) {
				return false;
			}
		}
		return true;
	}
}
