
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MinCoins {

	public static int minCoins(int[] coins, int num, int c) {
		if (c == 0) {
			return 0;
		}

		if (c < 0) {
			return Integer.MAX_VALUE;
		}

		int minCoins = Integer.MAX_VALUE;

		for (int n = 0; n < num; n++) {
			int coin = coins[n];
			int tmp = c - coin;
			if (tmp >= 0) {
				int remaining = minCoins(coins, num, tmp);
				if (remaining != Integer.MAX_VALUE) {
					remaining += 1;
				}

				if (remaining < minCoins) {
					minCoins = remaining;
				}
			}
		}

		return minCoins;
	}
}
