import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxOfThree {

	public static int maxOfThree(int n, int number, int count) {
		int num = n > number ? n : number;
		return num > count ? num : count;
	}
}
