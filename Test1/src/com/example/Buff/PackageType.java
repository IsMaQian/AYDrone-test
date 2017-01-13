package com.example.Buff;

public class PackageType {

	public enum packageType {
        PtNon,
        PtIMU, 
        PtAcceleratedSpeed,//加速度
        PtGeomagnetism,//地磁
        PtRemoteControl,//遙控器
        PtStartClose,//开关
        PtWayPoint,//路点
        PtMotor,//电机
        PtSpeed,//速度
        PtGPS,//GPS
        PtStatus,//状态
        PtXBEE   //XBEE断点检查
    }
}
