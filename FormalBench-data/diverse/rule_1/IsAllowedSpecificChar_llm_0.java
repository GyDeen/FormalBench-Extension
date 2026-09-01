import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsAllowedSpecificChar {

	public static Boolean isAllowedSpecificChar(String str) {
		return str.matches("[a-zA-Z0-9]*");
	}
}
