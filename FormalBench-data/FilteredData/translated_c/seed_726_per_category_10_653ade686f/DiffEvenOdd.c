#include <stddef.h>
#include <stdint.h>

typedef struct {
    int32_t *data;
    size_t length;
} Int32Array;

int32_t diffEvenOdd(Int32Array array) {
    int32_t firstEven = -1;
    int32_t firstOdd = -1;

    for (size_t i = 0; i < array.length; i++) {
        int32_t num = array.data[i];
        if (firstEven == -1 && num % 2 == 0) {
            firstEven = num;
        }
        if (firstOdd == -1 && num % 2 != 0) {
            firstOdd = num;
        }
        if (firstEven != -1 && firstOdd != -1) {
            break;
        }
    }

    return (int32_t)((uint32_t)firstEven - (uint32_t)firstOdd);
}
