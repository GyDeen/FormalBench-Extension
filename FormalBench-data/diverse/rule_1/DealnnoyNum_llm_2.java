import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DealnnoyNum {

	public static int dealnnoyNum(int number, int p) {
		if (p == 0 || number == 0) {
			return 1;
		}
		return dealnnoyNum(p - 1, number) + dealnnoyNum(p - 1, number - 1)
				+ dealnnoyNum(p, number - 1);
	}
}
