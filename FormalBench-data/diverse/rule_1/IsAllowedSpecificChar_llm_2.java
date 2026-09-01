import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsAllowedSpecificChar {

	public static Boolean isAllowedSpecificChar(String char) {
		return char.matches("[a-zA-Z0-9]*");
	}
}
