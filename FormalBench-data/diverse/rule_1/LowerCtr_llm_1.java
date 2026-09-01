import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LowerCtr {

	public static int lowerCtr(String Builder) {
		int lower = 0;
		for (int c = 0; c < Builder.length(); c++) {
			if (Character.isLowerCase(Builder.charAt(c))) {
				lower++;
			}
		}
		return lower;
	}
}
