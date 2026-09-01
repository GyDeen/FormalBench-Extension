
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MinCoins {

	public static int minCoins(int[] coins, int m, int v) {
		if (v == 0) {
			return 0;
		}

		if (v < 0) {
			return Integer.MAX_VALUE;
		}

		int minCoins = Integer.MAX_VALUE;

		int max = 0;
		while (max < m) {
			int coin = coins[max];
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
			max++;
		}

		return minCoins;
	}
}
