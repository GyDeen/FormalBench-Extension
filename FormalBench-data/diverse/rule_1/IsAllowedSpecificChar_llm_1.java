import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsAllowedSpecificChar {

	public static Boolean isAllowedSpecificChar(String name) {
		return name.matches("[a-zA-Z0-9]*");
	}
}
