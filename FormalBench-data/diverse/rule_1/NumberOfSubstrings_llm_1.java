import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NumberOfSubstrings {

	public static int numberOfSubstrings(String Builder) {
		int result = 0;
		for (int index = 0; index < Builder.length(); index++) {
			for (int c = index + 1; c <= Builder
					.length(); c++) {
				if (Builder.substring(index, c).length() > 0) {
					result++;
				}
			}
		}
		return result;
	}
}
