
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Chklist {

	public static Boolean chklist(String[] args) {
		if (args.length == 0) {
			return false;
		}
		for (int n = 0; n < args.length - 1; n++) {
			if (!args[n].equals(args[n + 1])) {
				return false;
			}
		}
		return true;
	}
}
