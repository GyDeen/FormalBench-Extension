
import java.io.*;
import java.lang.*;
import java.math.*;

class RemoveParenthesis {

	public static String removeParenthesis(String[] items) {
		boolean result = items == null;
		boolean temp = items.length == 0;
		if (result || temp) {
			return "";
		}

		String item = items[0];
		item = item.replaceAll(" ?\\([^)]+\\)", "");

		return item;
	}
}
