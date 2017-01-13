package com.example.data;

import com.example.Buff.GetIntData;

/**状态数据*/
public class StatusData {
	int m_nFlyStatus;   //飞行模式
	int m_nVoltage;     //电压
	int m_nSendFlag;    //发送标志
	int m_nSensorStatus;//传感器状态
	int m_nCommStatus;  //通信状态
	int m_nPhotoFlag;   //拍照状态
	
	public StatusData(byte[] byBuff, int nOffset){
		m_nFlyStatus = GetIntData.getInt(byBuff, nOffset + 0, 2);
		m_nVoltage = GetIntData.getInt(byBuff, nOffset + 2, 2);
		m_nSendFlag = GetIntData.getInt(byBuff, nOffset + 4, 2);
		m_nSensorStatus = GetIntData.getInt(byBuff, nOffset + 6, 2);
		m_nCommStatus = GetIntData.getInt(byBuff, nOffset + 8, 2);
		m_nPhotoFlag = GetIntData.getInt(byBuff, nOffset + 10, 2);
	}
	
	public int getFlyStatus(){
	
		return m_nFlyStatus;
	}
	
	public double getVoltage(){
		double m_nVoltage1= m_nVoltage / 100.0;
		String s = Double.toString(m_nVoltage1);
		int i = s.indexOf('.');
		String sub = s.substring(0, i + 2);
		return Double.parseDouble(sub);
//		return m_nVoltage / 100.0;
	}
	
	public int getSendFlag(){
		return m_nSendFlag;
	}
	
	public int getSensorStatus(){
		return m_nSensorStatus;
	}
	
	public int getCommStatus(){
		return m_nCommStatus;
	}
	
	public int getPhotoFlag(){
		return m_nPhotoFlag;
	}
}
