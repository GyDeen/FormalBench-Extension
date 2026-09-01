import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveUppercase {

	public static String removeUppercase(String string) {
		return string.replaceAll("[A-Z]+", "");
	}
}
