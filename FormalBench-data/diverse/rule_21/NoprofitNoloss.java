import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NoprofitNoloss {

	public static Boolean noprofitNoloss(int actualCost, int saleAmount) {
		int profit = actualCost - saleAmount;
		int loss = saleAmount - actualCost;
		if (0 == profit && 0 == loss) {
			return true;
		}
		return false;
	}
}
