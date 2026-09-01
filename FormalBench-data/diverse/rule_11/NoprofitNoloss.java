import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NoprofitNoloss {

	public static Boolean noprofitNoloss(int actualCost, int saleAmount) {
		int profit = actualCost - saleAmount, loss = saleAmount - actualCost;
		if (profit == 0 && loss == 0) {
			return true;
		}
		return false;
	}
}
