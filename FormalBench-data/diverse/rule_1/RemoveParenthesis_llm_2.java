
import java.io.*;
import java.lang.*;
import java.math.*;

class RemoveParenthesis {

	public static String removeParenthesis(String[] args) {
		if (args == null || args.length == 0) {
			return "";
		}

		String result = args[0];
		result = result.replaceAll(" ?\\([^)]+\\)", "");

		return result;
	}
}
