import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxOfThree {

	public static int maxOfThree(int num1, int num2, int num3) {
		int max = num2 < num1 ? num1 : num2;
		return num3 < max ? max : num3;
	}
}
