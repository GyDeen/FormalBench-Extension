
import java.io.*;
import java.lang.*;
import java.math.*;

class RemoveParenthesis {

	public static String removeParenthesis(String[] items) {
		boolean ret = items == null;
		boolean tmp = items.length == 0;
		if (ret || tmp) {
			return "";
		}

		String item = items[0];
		item = item.replaceAll(" ?\\([^)]+\\)", "");

		return item;
	}
}
