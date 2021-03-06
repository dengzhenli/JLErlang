package org.fatty.erlang.lert.util;

public class ByteArrayUtils {

    public static long readLong(byte[] a, long offset, int lenght) {
        long ret = 0;
        for (int i = lenght - 1; i > 0; i--) {
            ret |= ((a[(int) offset++] & 0xFF) << 8 * i) & 0xFFFFFFFFL;
        }
        ret |= a[(int) offset++] & 0xFF;
        return ret;
    }

    public static int readInt(byte[] a, long offset, int lenght) {
        int ret = 0;
        for (int i = lenght - 1; i > 0; i--) {
            ret |= (a[(int) offset++] & 0xFF) << 8 * i;
        }
        ret |= (int) a[(int) offset++] & 0xFF;
        return ret;
    }

    public static byte[] int2Bytes(int num) {
        byte[] byteNum = new byte[4];
        for (int ix = 0; ix < 4; ++ix) {
            int offset = 32 - (ix + 1) * 8;
            byteNum[ix] = (byte) ((num >> offset) & 0xff);
        }
        return byteNum;
    }

    public static int bytes2Int(byte[] byteNum) {
        int num = 0;
        for (int ix = 0; ix < 4; ++ix) {
            num <<= 8;
            num |= (byteNum[ix] & 0xff);
        }
        return num;
    }

    public static byte int2OneByte(int num) {
        return (byte) (num & 0x000000ff);
    }

    public static int oneByte2Int(byte byteNum) {
        return byteNum > 0 ? byteNum : (128 + (128 + byteNum));
    }

    public static byte[] long2Bytes(long num) {
        byte[] byteNum = new byte[8];
        for (int ix = 0; ix < 8; ++ix) {
            int offset = 64 - (ix + 1) * 8;
            byteNum[ix] = (byte) ((num >> offset) & 0xff);
        }
        return byteNum;
    }

    public static long bytes2Long(byte[] byteNum) {
        long num = 0;
        for (int ix = 0; ix < 8; ++ix) {
            num <<= 8;
            num |= (byteNum[ix] & 0xff);
        }
        return num;
    }


}
