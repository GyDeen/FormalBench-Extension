
import java.io.*;
import java.lang.*;
import java.math.*;

class RemoveParenthesis {

	public static String removeParenthesis(String[] params) {
		if (params == null || params.length == 0) {
			return "";
		}

		String remove = params[0];
		remove = remove.replaceAll(" ?\\([^)]+\\)", "");

		return remove;
	}
}
