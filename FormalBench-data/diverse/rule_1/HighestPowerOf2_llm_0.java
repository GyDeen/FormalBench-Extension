import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class HighestPowerOf2 {

	public static int highestPowerOf2(int num) {
		int highest = 1;
		while (highest <= num) {
			highest *= 2;
		}
		return highest / 2;
	}
}
