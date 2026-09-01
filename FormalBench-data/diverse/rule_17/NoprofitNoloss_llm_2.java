import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NoprofitNoloss {

	public static Boolean noprofitNoloss(int actualCost, int saleAmount) {
		int profit = actualCost - saleAmount;
		int loss = saleAmount - actualCost;
		boolean ret = profit == 0;
		boolean tmp = loss == 0;
		if (ret && tmp) {
			return true;
		}
		return false;
	}
}
