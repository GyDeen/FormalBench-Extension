import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveUppercase {

	public static String removeUppercase(String text) {
		return text.replaceAll("[A-Z]+", "");
	}
}
