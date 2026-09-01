import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NoprofitNoloss {

	public static Boolean noprofitNoloss(int actualCost, int saleAmount) {
		int profit = actualCost - saleAmount;
		int loss = saleAmount - actualCost;
		boolean result = profit == 0;
		boolean temp = loss == 0;
		if (result && temp) {
			return true;
		}
		return false;
	}
}
