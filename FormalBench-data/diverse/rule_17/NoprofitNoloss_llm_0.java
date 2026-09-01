import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NoprofitNoloss {

	public static Boolean noprofitNoloss(int actualCost, int saleAmount) {
		int profit = actualCost - saleAmount;
		int loss = saleAmount - actualCost;
		boolean result = profit == 0;
		boolean tmp = loss == 0;
		if (result && tmp) {
			return true;
		}
		return false;
	}
}
