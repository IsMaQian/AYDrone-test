package com.example.data;

import com.example.Buff.GetIntData;

/**速度数据*/
public class SpeedData {
	int m_nLateral;         //横向速度
	int m_nLongitudinal;    //纵向速度
	int m_nAbout;           //上下速度
	
	public SpeedData(byte[] byBuff, int nOffset){
		m_nLateral = GetIntData.getInt(byBuff, nOffset + 0, 2);
		m_nLongitudinal = GetIntData.getInt(byBuff, nOffset + 2, 2);
		m_nAbout = GetIntData.getInt(byBuff, nOffset + 4, 2);
	}
	
	public double getLateral(){
		return m_nLateral / 100.0;
	}
	
	public double getLongitudinal(){
		return m_nLongitudinal / 100.0;
	}
	
	public double getAbout(){
		return m_nAbout / 100.0;
	}
}
