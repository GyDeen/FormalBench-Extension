import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DealnnoyNum {

	public static int dealnnoyNum(int num, int p) {
		if (p == 0 || num == 0) {
			return 1;
		}
		return dealnnoyNum(p - 1, num) + dealnnoyNum(p - 1, num - 1)
				+ dealnnoyNum(p, num - 1);
	}
}
