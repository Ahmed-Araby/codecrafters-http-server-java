package utils;

public class ArrayUtils {
    public static byte[] concat(byte[] arr1, byte[] arr2) {
        final byte[] container = new byte[arr1.length + arr2.length];

        for (int index = 0; index < arr1.length; index++) {
            container[index] = arr1[index];
        }

        for (int index = 0; index < arr2.length; index++) {
            container[index + arr1.length] = arr2[index];
        }

        return container;
    }
}
