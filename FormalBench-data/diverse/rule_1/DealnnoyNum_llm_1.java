import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DealnnoyNum {

	public static int dealnnoyNum(int num, int k) {
		if (k == 0 || num == 0) {
			return 1;
		}
		return dealnnoyNum(k - 1, num) + dealnnoyNum(k - 1, num - 1)
				+ dealnnoyNum(k, num - 1);
	}
}
