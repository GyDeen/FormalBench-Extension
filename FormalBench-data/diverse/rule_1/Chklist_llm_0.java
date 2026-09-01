
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Chklist {

	public static Boolean chklist(String[] args) {
		if (args.length == 0) {
			return false;
		}
		for (int index = 0; index < args.length - 1; index++) {
			if (!args[index].equals(args[index + 1])) {
				return false;
			}
		}
		return true;
	}
}
