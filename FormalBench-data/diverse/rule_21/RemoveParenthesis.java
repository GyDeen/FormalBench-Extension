
import java.io.*;
import java.lang.*;
import java.math.*;

class RemoveParenthesis {

	public static String removeParenthesis(String[] items) {
		if (null == items || 0 == items.length) {
			return "";
		}

		String item = items[0];
		item = item.replaceAll(" ?\\([^)]+\\)", "");

		return item;
	}
}
