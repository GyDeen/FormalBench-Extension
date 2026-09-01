import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveLowercase {

	public static String removeLowercase(String string) {
		return string.replaceAll("[a-z]", "");
	}
}
