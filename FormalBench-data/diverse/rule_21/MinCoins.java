
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MinCoins {

	public static int minCoins(int[] coins, int m, int v) {
		if (0 == v) {
			return 0;
		}

		if (v < 0) {
			return Integer.MAX_VALUE;
		}

		int minCoins = Integer.MAX_VALUE;

		for (int i = 0; i < m; i++) {
			int coin = coins[i];
			int remainingValue = v - coin;
			if (remainingValue >= 0) {
				int totalCoins = minCoins(coins, m, remainingValue);
				if (totalCoins != Integer.MAX_VALUE) {
					totalCoins += 1;
				}

				if (totalCoins < minCoins) {
					minCoins = totalCoins;
				}
			}
		}

		return minCoins;
	}
}
