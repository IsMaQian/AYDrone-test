package com.example.Buff;

public class GetDoubleData {
	public static double byteArrayToDouble(byte[] Array, int Pos) {  
	    long accum = 0;  
	    accum = Array[Pos + 0] & 0xFF;  
	    accum |= (long) (Array[Pos + 1] & 0xFF) << 8;  
	    accum |= (long) (Array[Pos + 2] & 0xFF) << 16;  
	    accum |= (long) (Array[Pos + 3] & 0xFF) << 24;  
	    accum |= (long) (Array[Pos + 4] & 0xFF) << 32;  
	    accum |= (long) (Array[Pos + 5] & 0xFF) << 40;  
	    accum |= (long) (Array[Pos + 6] & 0xFF) << 48;  
	    accum |= (long) (Array[Pos + 7] & 0xFF) << 56;  
	    return Double.longBitsToDouble(accum);  
	}  
}
