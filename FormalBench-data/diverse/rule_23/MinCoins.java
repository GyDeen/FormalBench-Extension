
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MinCoins {

	public static int minCoins(int[] coins, int m, int v) {
		if (v == 0) {
			return 0;
		}

		if (0 > v) {
			return Integer.MAX_VALUE;
		}

		int minCoins = Integer.MAX_VALUE;

		for (int i = 0; m > i; i++) {
			int coin = coins[i];
			int remainingValue = v - coin;
			if (0 <= remainingValue) {
				int totalCoins = minCoins(coins, m, remainingValue);
				if (Integer.MAX_VALUE != totalCoins) {
					totalCoins += 1;
				}

				if (minCoins > totalCoins) {
					minCoins = totalCoins;
				}
			}
		}

		return minCoins;
	}
}
