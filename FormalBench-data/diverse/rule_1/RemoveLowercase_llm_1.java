import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveLowercase {

	public static String removeLowercase(String str) {
		return str.replaceAll("[a-z]", "");
	}
}
