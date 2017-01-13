package com.example.data;

import com.example.Buff.GetIntData;
import com.example.Buff.GetDoubleData;

/**GPS数据*/
public class GPSData {
	//因为经纬度需要8个字节，所以不能用int来存，需要用long型
	double m_nLatitude;      //纬度
	double m_nLongitude;     //经度
	int m_nStarCount;     //卫星数量
	int m_nHight;         //高度
	
	
	public GPSData(byte[] byBuff, int nOffset){
		m_nLatitude = GetDoubleData.byteArrayToDouble(byBuff, nOffset);
		m_nLongitude = GetDoubleData.byteArrayToDouble(byBuff, nOffset+8);
		m_nStarCount = GetIntData.getInt(byBuff, nOffset + 16, 2);
		m_nHight = GetIntData.getInt(byBuff, nOffset + 18, 2);
	}
	
	public Double getLatitude(){
		return m_nLatitude;
	}
	
	public Double getLongitude(){
		return m_nLongitude;
	}
	
	public int getStarCount(){
		return m_nStarCount;
	}
	
	public double getHight(){
		return m_nHight / 100.0;
	}
}
