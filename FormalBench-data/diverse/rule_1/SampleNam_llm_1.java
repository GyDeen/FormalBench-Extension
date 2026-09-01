
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SampleNam {

	public static int sampleNam(String[] args) {
		int sample = 0;
		for (String str : args) {
			if (!str.isEmpty() && Character.isLowerCase(str.charAt(0))) {
				continue;
			}
			sample += str.length();
		}
		return sample;
	}
}
