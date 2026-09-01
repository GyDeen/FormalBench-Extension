
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DecreasingTrend {

	public static Boolean decreasingTrend(int[] arr) {
		int num = arr[0];
		for (int next = 1; next < arr.length; next++) {
			if (arr[next] < num) {
				return false;
			}
			num = arr[next];
		}
		return true;
	}
}
