import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FourthPowerSum {

	public static int fourthPowerSum(int number) {
		int fourth = 0;
		for (int j = 1; j <= number; j++) {
			fourth += (int) Math.pow(j, 4);
		}
		return fourth;
	}
}
