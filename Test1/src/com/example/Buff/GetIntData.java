package com.example.Buff;

public class GetIntData {
	///byBuff:字节数组，源数据
		///nOffset：从第几个字节开始获取Int
		///nSize:要获取多少个字节，一般是4个字节
	public static short getInt(byte[] byBuff,int nOffset,int nSize){
	
		short nRes=0;
		 if (nOffset + nSize > byBuff.length){
		    	return 0;
		    }
		 for (int i = 0; i < nSize; i++){
		        nRes = (short) (nRes |(short)( (byBuff[i + nOffset] & 0x00FF)<< (8 * (nSize-i-1)) ));
		    }
		    return nRes;
		} 
	}


