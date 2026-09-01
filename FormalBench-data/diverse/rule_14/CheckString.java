import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckString {

	public static Boolean checkString(String str) {
		if (!(str.matches("[a-zA-Z]+[0-9]+")))
			;
		else {
			return true;
		}
		return false;
	}
}
