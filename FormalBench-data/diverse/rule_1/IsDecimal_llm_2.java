import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsDecimal {

	public static Boolean isDecimal(String input) {
		return input.matches("^[0-9]+(\\.[0-9]{1,2})?$");
	}
}
