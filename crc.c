#include <stdio.h>

int main() {
    int data[20], divisor[10], temp[30], remainder[10];
    int data_bits, divisor_bits;
    int i, j;

    printf("Enter 8-bit data (bit by bit):\n");
    data_bits = 8;
    for (i = 0; i < data_bits; i++) {
        scanf("%d", &data[i]);
        temp[i] = data[i];  // Copy to temp for division
    }

    printf("Enter 4-bit divisor (bit by bit):\n");
    divisor_bits = 4;
    for (i = 0; i < divisor_bits; i++) {
        scanf("%d", &divisor[i]);
    }

    for (i = data_bits; i < data_bits + divisor_bits - 1; i++) {
        temp[i] = 0;
    }

    for (i = 0; i < data_bits; i++) {
        if (temp[i] == 1) {
            for (j = 0; j < divisor_bits; j++) {
                temp[i + j] ^= divisor[j];  // XOR operation
            }
        }
    }

    for (i = 0; i < divisor_bits - 1; i++) {
        remainder[i] = temp[data_bits + i];
    }

    printf("\nCRC Remainder: ");
    for (i = 0; i < divisor_bits - 1; i++) {
        printf("%d", remainder[i]);
    }

    printf("\nTransmitted Frame (data + CRC): ");
    for (i = 0; i < data_bits; i++) {
        printf("%d", data[i]);
    }
    for (i = 0; i < divisor_bits - 1; i++) {
        printf("%d", remainder[i]);
    }

    printf("\n");

    return 0;
}
