package com.example.data;

import com.example.Buff.GetIntData;

/**开关数据*/
public class StartCloseData {
	int m_SW1;
	int m_SW2;
	int m_SW3;
	int m_SW4;
	
	public StartCloseData(byte[] byBuff, int nOffset){
		m_SW1 = GetIntData.getInt(byBuff, nOffset + 0, 2);
		m_SW2 = GetIntData.getInt(byBuff, nOffset + 2, 2);
		m_SW3 = GetIntData.getInt(byBuff, nOffset + 4, 2);
		m_SW4 = GetIntData.getInt(byBuff, nOffset + 6, 2);
	}
	
	public double getSW1(){
        return m_SW1;
    }
	
	public double getSW2(){
        return m_SW2;
    }
	
	public double getSW3(){
        return m_SW3;
    }
	
	public double getSW4(){
        return m_SW4;
    }
}
