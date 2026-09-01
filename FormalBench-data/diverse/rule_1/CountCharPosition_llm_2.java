import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountCharPosition {

	public static int countCharPosition(String Builder) {
		int ret = 0;
		for (int count = 0; count < Builder.length(); count++) {
			if ((count == Builder.charAt(count) - 'A')
					|| (count == Builder.charAt(count) - 'a')) {
				ret += 1;
			}
		}
		return ret;
	}
}
