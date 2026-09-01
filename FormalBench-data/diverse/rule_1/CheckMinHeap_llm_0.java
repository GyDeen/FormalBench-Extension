
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckMinHeap {

	public static Boolean checkMinHeap(int[] heap, int array) {
		if (array >= heap.length / 2) {
			return true;
		}
		int index = 2 * array + 1;
		int top = 2 * array + 2;
		if (index < heap.length
				&& heap[array] > heap[index]) {
			return false;
		}
		if (top < heap.length
				&& heap[array] > heap[top]) {
			return false;
		}
		return checkMinHeap(heap, index) && checkMinHeap(heap, top);
	}
}
