package com.example.data;

import com.example.Buff.GetIntData;

/**加速度数据*/
public class AcceleratedSpeed {
	int m_aX;
	int m_aY;
	int m_aZ;
	
	public AcceleratedSpeed(byte[] byBuff, int nOffset){
		m_aX = GetIntData.getInt(byBuff, nOffset + 0, 2);
		m_aY = GetIntData.getInt(byBuff, nOffset + 2, 2);
		m_aZ = GetIntData.getInt(byBuff, nOffset + 4, 2);
	}
	public double getX(){
        return m_aX/1000.0;
    }
	
	public double getY(){
        return m_aY/1000.0;
    }
	
	public double getZ(){
        return m_aZ/1000.0;
    }
	AcceleratedSpeed accspeed;
}
