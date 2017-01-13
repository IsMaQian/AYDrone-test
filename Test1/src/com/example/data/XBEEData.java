package com.example.data;

import com.example.Buff.GetIntData;

/**XBEEÍ¨¶Ï¼ì²âÊý¾Ý*/
public class XBEEData {
	int m_nXBEE;
	
	public XBEEData(byte[] byBuff, int nOffset){
		m_nXBEE = GetIntData.getInt(byBuff, nOffset + 0, 2);
	}
	
	public int getXBEE(){
        return m_nXBEE;
    }
}
