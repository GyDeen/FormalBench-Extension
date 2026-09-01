
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DecreasingTrend {

	public static Boolean decreasingTrend(int[] arr) {
		int num = arr[0];
		for (int cur = 1; cur < arr.length; cur++) {
			if (arr[cur] < num) {
				return false;
			}
			num = arr[cur];
		}
		return true;
	}
}
