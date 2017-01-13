package com.example.data;

import com.example.Buff.GetIntData;

/**电机数据*/
public class MotorData {
	int m_Motor_Front;
	int m_Motor_Back;
	int m_Motor_Left;
	int m_Motor_Right;
	int m_Motor_X;
	int m_Motor_Y;
	
	public MotorData(byte[] byBuff, int nOffset){
		m_Motor_Front = GetIntData.getInt(byBuff, nOffset + 0, 2);
		m_Motor_Back = GetIntData.getInt(byBuff, nOffset + 2, 2);
		m_Motor_Left = GetIntData.getInt(byBuff, nOffset + 4, 2);
		m_Motor_Right = GetIntData.getInt(byBuff, nOffset + 6, 2);
		m_Motor_X = GetIntData.getInt(byBuff, nOffset + 8, 2);
		m_Motor_Y = GetIntData.getInt(byBuff, nOffset + 10, 2);
	}
	
	public double getMotor_Front(){
        return m_Motor_Front / 10.0;
    }
	
	public double getMotor_Back(){
        return m_Motor_Back / 10.0;
    }
	
	public double getMotor_Left(){
        return m_Motor_Left / 10.0;
    }
	
	public double getMotor_Right(){
        return m_Motor_Right / 10.0;
    }
	
	public double getMotor_X(){
        return m_Motor_X / 10.0;
    }
	
	public double getMotor_Y(){
        return m_Motor_Y / 10.0;
    }
	
	
}
