
import java.io.*;
import java.lang.*;
import java.math.*;

class RemoveParenthesis {

	public static String removeParenthesis(String[] params) {
		if (params == null || params.length == 0) {
			return "";
		}

		String result = params[0];
		result = result.replaceAll(" ?\\([^)]+\\)", "");

		return result;
	}
}
