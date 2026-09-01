
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SampleNam {

	public static int sampleNam(String[] args) {
		int sample = 0;
		for (String string : args) {
			if (!string.isEmpty() && Character.isLowerCase(string.charAt(0))) {
				continue;
			}
			sample += string.length();
		}
		return sample;
	}
}
