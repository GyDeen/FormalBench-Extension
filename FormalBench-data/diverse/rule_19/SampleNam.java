
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SampleNam {

	public static int sampleNam(String[] sampleNames) {
		int count = 0;
		for (String name : sampleNames) {
			if (!name.isEmpty()) {
				if (Character.isLowerCase(name.charAt(0))) {
					continue;
				}
			}
			count += name.length();
		}
		return count;
	}
}
