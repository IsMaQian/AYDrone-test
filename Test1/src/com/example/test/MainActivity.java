package com.example.test;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.format.Time;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMap.OnMapClickListener;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends Activity implements LocationSource,AMapLocationListener {
	 protected static final PolylineOptions locationList = null;
	 String DIR="";
	 String FILENAME="";
	 static MapView mMapView ;
	 private AMap aMap;
	 static String cityName;
	 static String province;
	 String district;
	 String streetNum;
	 String street;
	 /**IMU����*/
		String strRoll;//��ת��
		String strPitch;//������
		String strYaw;//�����
		String strRollRate;//������Rollrate
		String strPichRate;//������Pitchrate
		String strYawRate;//������Yawrate
		 
		/**���ٶ�����*/
		String strAccX;//x������ٶ�
		String strAccY;//y������ٶ�
		String strAccZ;//z������ٶ�
		
		/**�ش�����*/
		String strGeoX;
	    String strGeoY;
		String strGeoZ;
		
		/**ң��������*/
		String strReRoll;
		String strRePitch;
		String strReYaw;	
		String strReThrottle;
		
		/**��������*/
		String strSW1;
		String strSW2;
		String strSW3;
		String strSW4;
		
		/**·������*/
		String strwayID;
		String strwayCount;
		String strwayIndex;
		
		/**�������*/
		String strMotor_Front;
		String strMotor_Back;
		String strMotor_Left;
		String strMotor_Right;
		String strMotor_X;
		String strMotor_Y;
		
		/**�ٶ�����*/
		String strLateral;
		String strLongitudinal;
		String strAbout;
		
		/**GPS����*/
		String strLatitude;
		String strLongitude;
		String strStarCount;
		String strHight;
		
		/**״̬����*/
		String strFlyStatus;
		String strVoltage;
		String strSendFlag;
		String strSensorStatus;
		String strCommStatus;
		String strPhotoFlag;
		
		/**XBEEͨ�ϼ������*/
		String strXBEE;
	 private AMapLocationClient mLocationClient = null;
	    //����mLocationOption���󣬶�λ����
	 public AMapLocationClientOption mLocationOption = null;
	    //��ʶ�������ж��Ƿ�ֻ��ʾһ�ζ�λ��Ϣ���û����¶�λ
	    private boolean isFirstLoc = true; 
	    public int title_name_count = -1;
	    double angle; 
	    Marker marker;
	    Marker marker1;
	    LatLng pos;
	    Polyline polyline;
	    //�����Ļ����
	    int Click =-1;
	    int ret=-1;
	    int Date=1;
	    int zb=1;
	    Time time;
	    int year ; 
		int month ; 
		int day ; 
		int minute ; 
		int hour; 
		int sec ; 
	    double[] Lng = new double[50];
		double[] Lat = new double[50];
		 
	    String state;
	    MarkerOptions markerOptions;
	    private LatLng currentPt;
	    private LatLng currentPt1;
	    //��ǰ�Ķ�λ��  
	    LatLng oldLatLng = null;
	    LatLng oldlatlng;
	    //���ڵĵ�
	    LatLng newLatLng;
	    LatLng newlatlng;
	    //�Ƿ��ǵ�һ�ζ�λ  
	    boolean isFirstLatLng=true;  
//	    LatLng  newLatLng=new LatLng(32.011, 118.782);
//	    LatLng newLatLng1=new LatLng(31.011, 120.782);
	static int gloalV; // ȫ���Ե�
	static String sIP;
	static int port;
	String path2;
	private String scoketIP = "192.168.1.113:8088";
	// ��������ϵ������ı���
	// ��������ϵ�һ����ť
//	������Ϣ����
	Button displaySendMsgLayoutBtn;
	boolean isSendMsgLayoutShow;
	EditText input;
	EditText input1;
	Button send;
	Button link;
	Button choose;
	Button delect;
	Button become;
	LinearLayout sendMsgLL;
	ToggleButton tb;
//	������Ϣ����
	Button displayReveMsgLayoutBtn;
	Button save;
	boolean isReceMsgLayoutShow;
	static TextView show;
	TextView star;
	TextView height;
	TextView voltage;
	TextView fly_model;
	Button move;
	Button delect1;
	LinearLayout receMsgLL;
	Handler handler;
	static boolean isConnecting = false;
	ClientThread clientThread;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 //��ȡ��ͼ�ؼ�����
	    mMapView = (MapView) findViewById(R.id.map);
	    //��activityִ��onCreateʱִ��mMapView.onCreate(savedInstanceState)��ʵ�ֵ�ͼ�������ڹ���
	    mMapView.onCreate(savedInstanceState);
		input = (EditText) findViewById(R.id.input);
		input1 = (EditText) findViewById(R.id.input1);
		send = (Button) findViewById(R.id.send);
		delect1 = (Button) findViewById(R.id.delect1);
		link = (Button) findViewById(R.id.link);
		choose=(Button) findViewById(R.id.choose);
		delect=(Button) findViewById(R.id.delect);
		become=(Button) findViewById(R.id.become);
		show = (TextView) findViewById(R.id.show);
		save=(Button) findViewById(R.id.save);
		star = (TextView) findViewById(R.id.star);
		height = (TextView) findViewById(R.id.height);
		voltage = (TextView) findViewById(R.id.voltage);
		fly_model = (TextView) findViewById(R.id.fly_model);
		show.setMovementMethod(ScrollingMovementMethod.getInstance());
		sendMsgLL = (LinearLayout) findViewById(R.id.send_ll);
		displaySendMsgLayoutBtn = (Button) findViewById(R.id.display_send_msg_btn);
		receMsgLL = (LinearLayout) findViewById(R.id.receive_ll);
		displayReveMsgLayoutBtn = (Button) findViewById(R.id.display_receive_msg_btn);
		move = (Button) findViewById(R.id.move);
		tb = (ToggleButton) findViewById(R.id.tb);
		input1.setText(scoketIP);
		//��ȡ�ֻ���ǰʱ��
		time = new Time(); 
		time.setToNow(); 
		year = time.year; 
		month = time.month; 
		day = time.monthDay; 
		minute = time.minute; 
		hour = time.hour; 
		sec = time.second; 
		final String blank = "\n";
		 if (aMap == null) {
	            aMap = mMapView.getMap();
	            //������ʾ��λ��ť ���ҿ��Ե��
	            aMap.setLocationSource(this);//�����˶�λ�ļ���
	            // �Ƿ���ʾ��λ��ť
	        }
	        //��ʼ��λ
	        location();
	        //�������߶ȡ���ѹ������ģʽ���ݵ���ʾ
	        star.setText(null);
			height.setText(null);
			voltage.setText(null);
			fly_model.setText(null);
//        ��ʾ���������֮��ĸ�����
		handler = new Handler() // ��
		{
			@Override
			public void handleMessage(Message msg) {

				// �����Ϣ���������߳�
				switch (msg.what) {
				case 1:
					angle=Double.parseDouble(clientThread.strYaw)*(57.2957795);
					strRoll=ClientThread.strRoll ;
					strPitch=ClientThread.strPitch;
					strYaw= ClientThread.strYaw ; 
					strRollRate= ClientThread.strRollRate ;
					strPichRate= ClientThread.strPichRate; 
					strYawRate=ClientThread.strYawRate ;
					
					break;
				case 2:
					strAccX=ClientThread.strAccX ; 
					strAccY=ClientThread.strAccY ;
					strAccZ=ClientThread.strAccZ ;
					break;
				case 3:
					strGeoX=ClientThread.strGeoX ; 
					strGeoY=ClientThread.strGeoY ;
					strGeoZ=ClientThread.strGeoZ ;
					break;
				case 4:
					strReRoll=ClientThread.strReRoll ; 
					strRePitch=ClientThread.strRePitch;
					strReYaw=ClientThread.strReYaw ;
					strReThrottle=ClientThread.strReThrottle ;
					break;	
				case 5:
					strSW1=ClientThread.strSW1 ; 
					strSW2=ClientThread.strSW2 ;
					strSW3=ClientThread.strSW3 ;
					strSW4=ClientThread.strSW4 ;
					break;	
				case 6:
					strwayID=ClientThread.strwayID ;
					strwayCount=ClientThread.strwayCount ;
					strwayIndex=ClientThread.strwayIndex ;
					break;	
				case 7:
					strMotor_Front=ClientThread.strMotor_Front ;
					strMotor_Back=ClientThread.strMotor_Back ;
					strMotor_Left=ClientThread.strMotor_Left ;
					strMotor_Right=ClientThread.strMotor_Right ;
					strMotor_X=ClientThread.strMotor_X ;
					strMotor_Y=ClientThread.strMotor_Y ;
					break;	
				case 8:
					strLateral=ClientThread.strLateral ;
					strLongitudinal=ClientThread.strLongitudinal ;
					strAbout=ClientThread.strAbout ;
					break;	
				case 9:
					presentPoint();
					strLatitude=ClientThread.strLatitude; 
					strLongitude=ClientThread.strLongitude ;
					strStarCount=ClientThread.strStarCount ;
					strHight=ClientThread.strHight ;
					break;	
				case 10:
					strFlyStatus=ClientThread.strFlyStatus ;
					strVoltage=ClientThread.strVoltage ;
					strSendFlag=ClientThread.strSendFlag ;
					strSensorStatus=ClientThread.strSensorStatus ; 
					strCommStatus=ClientThread.strCommStatus ;
					strPhotoFlag=ClientThread.strPhotoFlag ;
					break;	
				case 11:
					strXBEE=ClientThread.strXBEE ;
				}
				show.setText
						(blank +"IMU数据为:" + blank+
								"滚转角为：" + strRoll + blank
								+ "俯仰角为：" + strPitch + blank
								+ "航向角为："+ strYaw + blank
								+ "陀螺仪Rollrate为："+ strRollRate + blank
								+ "陀螺仪Pitchrate为：" + strPichRate+ blank
								+ "陀螺仪Yawrate为：" + strYawRate + blank
								+ blank
								+"加速度数据为:" + blank+
								"X方向加速度为："+strAccX +blank
								+ "Y方向加速度为："+strAccY +blank
								+ "Z方向加速度为："+strAccZ +blank
								+ blank
								+"地磁数据为:" + blank+
								"X方向地磁数据为："+strGeoX +blank
								+ "Y方向地磁数据为："+strGeoY +blank
								+ "Z方向地磁数据为："+strGeoZ +blank
								+blank +
								"遥控器数据为:" + blank+
								"遥控器Roll量为："+strReRoll +blank
								+ "遥控器Pitch量为："+strRePitch +blank
								+ "遥控器Yaw量："+strReYaw +blank
								+ "遥控器Throttle量："+strReThrottle +blank
								+blank +
								"开关数据为:" + blank+
								"开关SW1为："+strSW1 +blank
								+ "开关SW2为："+strSW2 +blank
								+ "开关SW3为："+strSW3 +blank
								+ "开关SW4为："+strSW4 +blank
								+blank
								+"路点数据为:" + blank+
								"路点ID为："+strwayID +blank
								+ "路点数为："+strwayCount +blank
								+ "第几个路点为："+strwayIndex +blank
								+blank +"电机数据为:" + blank+
								"Motor_Front为："+strMotor_Front +blank
								+ "Motor_Back为："+strMotor_Back +blank
								+ "Motor_Left为："+strMotor_Left +blank
								+ "Motor_Right为："+strMotor_Right +blank
								+ "Motor_X为："+strMotor_X +blank
								+ "Motor_Y为："+strMotor_Y +blank
								+blank +
								"速度数据为:" + blank+
								"横向速度为："+strLateral +blank
								+ "纵向速度为："+strLongitudinal +blank
								+ "上下速度为："+strAbout +blank
								+blank +
								"GPS数据为:" + blank+
								"纬度为："+strLatitude+blank
								+ "经度为："+strLongitude +blank
								+ "星数为："+strStarCount +blank
								+ "高度为："+strHight +blank
								+blank +
								"状态数据为:" + blank
								+"飞行模式为："+strFlyStatus +blank
								+ "电压为："+strVoltage +blank
								+ "发送标志为："+strSendFlag +blank
								+"传感器状态为："+strSensorStatus +blank
								+ "通信状态为："+strCommStatus +blank
								+ "拍照标志为："+strPhotoFlag +blank
								+blank
								+"XBEE通断检测数据为:" + blank+
								"接收一个255数据为："+strXBEE +blank);


				star.setText(strStarCount);
			height.setText(strHight);
			voltage.setText(strVoltage);
			fly_model.setText(strFlyStatus);
			}
          //��ʾ�ɻ�λ�ñ��marker
			private void presentPoint() {
				// TODO Auto-generated method stub
				String lng=ClientThread.strLongitude;//����
				double lng1=Double.parseDouble(lng);
				String lat=ClientThread.strLatitude;//γ��
				double lat1=Double.parseDouble(lat);
				pos = new LatLng(lat1, lng1);
				newLatLng=pos;
				 if(isFirstLatLng){  
                  //��¼��һ�εĶ�λ��Ϣ  
				  oldLatLng = newLatLng; 
                  isFirstLatLng = false;  
              }  
              //λ���б仯  
           	   if(oldLatLng != newLatLng){ 
                  Log.e("Amap", lat1 + "," + lng1);
                  marker.remove();
                  setUpMap( oldLatLng , newLatLng ); 
                  oldLatLng = newLatLng;  }
				  CameraUpdate cu=CameraUpdateFactory.changeLatLng(pos);
				   //���µ�ͼ��ʾ����
				   aMap.moveCamera(cu);
				   //����MarkerOptions����
				   MarkerOptions markerOptions=new MarkerOptions()
				   //����MarkerOptions�����λ��
				   .position(pos)
				   .setFlat(true)
			    //����markerOptions����
			       .icon(BitmapDescriptorFactory.fromResource(R.drawable.position));
			        marker=aMap.addMarker(markerOptions);
			        marker.setRotateAngle((float)angle);
			        marker.showInfoWindow(); // ����Ĭ����ʾ��Ϣ��
			}
			
               //�����ɻ����й켣
			private void setUpMap(LatLng oldData, LatLng newData) {
				// TODO Auto-generated method stub
				 aMap.addPolyline((new PolylineOptions()) 
			                .add(oldData, newData) 
			                .width(10)
			                .geodesic(true)
			                .color(Color.RED));  
			}
		};
		//��ͨ��ͼ���ǵ�ͼ
		tb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
		{
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
			{
				if(isChecked)
				{
					// ����ʹ�����ǵ�ͼ
					aMap.setMapType(AMap.MAP_TYPE_SATELLITE);
				}
				else
				{
					// ����ʹ����ͨ��ͼ
					aMap.setMapType(AMap.MAP_TYPE_NORMAL);
				}
			}
		});
	   
		//������ʾ����
		displaySendMsgLayoutBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (!isSendMsgLayoutShow) {
					sendMsgLL.setVisibility(View.VISIBLE);
					receMsgLL.setVisibility(View.GONE);
					isSendMsgLayoutShow = true;
					displaySendMsgLayoutBtn.setText("����������Ϣ");
				} else {
					sendMsgLL.setVisibility(View.INVISIBLE);
					displaySendMsgLayoutBtn.setText("��ʾ������Ϣ");
					receMsgLL.setVisibility(View.GONE);
					isSendMsgLayoutShow = false;
				}
			}
		});
		//������ʾ���
		displayReveMsgLayoutBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (!isReceMsgLayoutShow) {
					sendMsgLL.setVisibility(View.GONE);
					receMsgLL.setVisibility(View.VISIBLE);
					isReceMsgLayoutShow = true;
					displayReveMsgLayoutBtn.setText("���ؽ�����Ϣ");
				} else {
					sendMsgLL.setVisibility(View.GONE);
					displayReveMsgLayoutBtn.setText("��ʾ������Ϣ");
					receMsgLL.setVisibility(View.INVISIBLE);
					isReceMsgLayoutShow = false;
				}
			}
		});
		// �ͻ�������ClientThread�̴߳����������ӡ���ȡ���Է�����������
		clientThread = new ClientThread(handler);
		//����
		link.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (isConnecting) {
					isConnecting = false;
					try {
						if (clientThread.s != null) {
							clientThread.s.close();
							clientThread.s = null;
							clientThread.br.close();
							clientThread.br = null;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

					new Thread(clientThread).interrupt(); 
					link.setText("��ʼ����");
					input1.setEnabled(true);
					handler.removeCallbacks(clientThread);
				}

				else if (MainActivity.this.isIPValid()) {
					isConnecting = true;
					link.setText("ȡ������");
					input1.setEnabled(false);
					// ����һ��������������߳�
					new Thread(clientThread).start();
				} // ��
			}
		});
		//����
		send.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String msgText=input.getText().toString();
				if (msgText.length() <= 0) {
					Toast.makeText(getApplicationContext(), "�������ݲ���Ϊ�գ�", Toast.LENGTH_SHORT)
							.show();
				} else {
				try {
					// ���û����·��Ͱ�ť�󣬽��û���������ݷ�װ��Message
					// Ȼ���͸����̵߳�Handler
					Message msg = new Message();
					msg.what = 0x345;
					msg.obj = input.getText().toString();
					clientThread.revHandler.sendMessage(msg);
					markerOptions.draggable(false);
					// ���input�ı���
					if (isConnecting) {
					input.setText("");
					
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			}
		});
		//ѡ��·��
		choose.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
//				updateMapState();
				// TODO Auto-generated method stub
				aMap.setOnMapClickListener(new OnMapClickListener() {
					
					@Override
					public void onMapClick(LatLng point) {
						// TODO Auto-generated method stub
						choose.setEnabled(false);
						currentPt=point;
						Click++;
						updateMapState();
					}
				});
			}
		});
		//�h��·�c
		delect.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Click=-1;
				title_name_count=-1;
				input.setText("");
				aMap.clear();
			}
		});
		//��ȡ�����ļ�
		become.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
//				LatLng pos=new LatLng(32.013423, 118.795208);
//				CameraUpdate cu=CameraUpdateFactory.changeLatLng(pos);
//				aMap.moveCamera(cu);
//				read();
				ret=-1;
				Intent intent = new Intent(MainActivity.this, SDFileActivity.class);
				startActivityForResult(intent, 0);
			}
		});
		//���
		move.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				show.setText("");
			}
		});
		 //����
		save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				DIR="����վ����";
				//���ֻ�ʱ�������ļ�
				FILENAME=month + "." +
						  day + "-"  + 
						  hour + ":"  +
						  minute + ":"+
						  sec + ".txt";
				write(show.getText().toString());
				DIR="����վ��������";
				FILENAME= 
//						  month + "." +
//						  day + "-"  + 
//						  +hour + ":"  +
//						  minute + ":"+
//						  sec + 
						  district+
						  street+
						  streetNum+
						  ".txt";
				
				List<LatLng> pts1 = new ArrayList<LatLng>();
				if(Click>0){
				for(int i=0;i<=Click;i++){
					LatLng newlatlng1=new LatLng(Lat[i],Lng[i]);
					pts1.add(newlatlng1);
					write(pts1.get(i).toString());//�������д��SD��
				}
				}
			}
		});
		 //���ص�ͼ
		delect1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View source) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, OfflineMapActivity.class);
				startActivity(intent);
			}
		});
			}
	// ��д�÷������÷����Իص��ķ�ʽ����ȡָ��Activity���صĽ��
		@Override
		public void onActivityResult(int requestCode
				, int resultCode, Intent intent)
		{
			// ��requestCode��resultCodeͬʱΪ0ʱ��Ҳ���Ǵ����ض��Ľ��
			if (requestCode == 0 && resultCode == 0)
			{
				// ȡ��Intent���Extras����
				Bundle path = intent.getExtras();
				// ȡ��Bundle�е�����
				String path1 = path.getString("path");
				path2=path1.substring(24);
//				Toast.makeText(this, path2, Toast.LENGTH_LONG).show();
				read();
			}
		}
       //��ȡSD�������겢����
	    protected void read() {
		// TODO Auto-generated method stub
		try{
			
		if (Environment.getExternalStorageState().equals(
		        Environment.MEDIA_MOUNTED)) { // ���sdcard����
		      File file = new File(Environment.getExternalStorageDirectory()
		    	  .getCanonicalPath()
		    	  +path2);
		   // �ж��Ƿ���ڸ��ļ�
              if (file.exists()) {
                  // ���ļ�������
		      FileInputStream fileR=new FileInputStream(file);
		      BufferedReader read=new BufferedReader(new InputStreamReader(fileR));
		      String st = null;
              while ((st = read.readLine()) != null){
            	  ++ret;
            	  String latln1=st.substring(10, st.length()-1);
            	  String[] st1=latln1.split(",", 2);
            	  Lat[ret]=Double.parseDouble(st1[0]);
            	  Lng[ret]=Double.parseDouble(st1[1]);
              }
              Click=ret;
              setup();//����marker��polyline
              Toast.makeText(this, "drawing", Toast.LENGTH_LONG).show();
		} else {
            Toast.makeText(this, "��Ŀ¼���ļ�������", Toast.LENGTH_LONG).show();
        }
		}
		}catch(Exception e){
			e.printStackTrace();
		}
//		Toast.makeText(this, Click+"����Ϊ:"+ Lat[Click] +Lng[Click], Toast.LENGTH_LONG).show();
	}

	// �ļ�д��������
	  private void write(String content) {
	    if (Environment.getExternalStorageState().equals(
	        Environment.MEDIA_MOUNTED)) { // ���sdcard����
	      File file = new File(Environment.getExternalStorageDirectory()
	          .toString()
	          +File.separator
	          +"����վ"
	          + File.separator
	          + DIR
	          + File.separator
	          + FILENAME); // ����File�����
	      if (!file.getParentFile().exists()) { // ���ļ��в�����
	        file.getParentFile().mkdirs(); // �����ļ���
	      }
	      PrintStream out = null; // ��ӡ�������������
	      try {
	        out = new PrintStream(new FileOutputStream(file,true)); // ׷���ļ�
	        out.println(content);
	      } catch (Exception e) {
	        e.printStackTrace();
	      } finally {
	        if (out != null) {
	          out.close(); // �رմ�ӡ��
	          Toast.makeText(MainActivity.this, "saved", Toast.LENGTH_LONG).show();
	        }
	      }
	    } else { // SDCard�����ڣ�ʹ��Toast��ʾ�û�
	      Toast.makeText(this, "����ʧ�ܣ�SD�������ڣ�", Toast.LENGTH_LONG).show();
	    }
	  }
		//
		private void updateMapState() {
		// TODO Auto-generated method stub
		if(currentPt==null){
			state="";
		}else{
			state=String.format("%f %f", currentPt.latitude,currentPt.longitude);
			Lng[Click] = currentPt.longitude;
			Lat[Click] = currentPt.latitude;
			title_name_count++;
			String title_name = null;
			title_name = String.format("%d",title_name_count );
			input.setText(state);
			//����Marker�����
			LatLng point=currentPt;
			markerOptions=new MarkerOptions()
			.position(point)
			.title(title_name)
			.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
			.draggable(true);
			marker1=aMap.addMarker(markerOptions);
			marker1.setZIndex(3);
			newlatlng=new LatLng(Lat[Click],Lng[Click]);
			oldlatlng=new LatLng(Lat[Click-1],Lng[Click-1]);
			
			if(Click>=1){
				setUpMap( oldlatlng , newlatlng ); 
			}
		}
		//��marker������ק
		aMap.setOnMarkerDragListener(new AMap.OnMarkerDragListener() {
			
			@Override
			public void onMarkerDragStart(Marker marker) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onMarkerDragEnd(Marker marker) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void onMarkerDrag(Marker marker) {
				// TODO Auto-generated method stub
				currentPt1=marker.getPosition();
				state=String.format("%f %f", currentPt1.latitude,currentPt1.longitude);
			    String markerid=marker.getTitle();
			    int markerId=Integer.parseInt(markerid);
			    Lng[markerId] = currentPt1.longitude;
			    Lat[markerId] = currentPt1.latitude;
			    input.setText(state);
			    setup();
			}
		});
		}
	private void setUpMap(LatLng oldData, LatLng newData) {
        // TODO Auto-generated method stub
		
      aMap.addPolyline((new PolylineOptions()) 
      .add(oldData, newData) 
      .width(8)
      .zIndex(3)
      .geodesic(true)
      .color(Color.YELLOW));
      
}

	private void setup() {
		// TODO Auto-generated method stub
	    aMap.clear();
		List<LatLng> pts = new ArrayList<LatLng>(); 
		for(int i=0;i<=Click;i++){
		LatLng newlatlng1=new LatLng(Lat[i],Lng[i]);
		pts.add(newlatlng1);
		String title_name = String.format("%d",i );
		MarkerOptions markerOptions=new MarkerOptions()
		             .position(newlatlng1)
		             .draggable(true)
		             .title(title_name)
		             .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
		aMap.addMarker(markerOptions);
		  }
		PolylineOptions polylineOptions=new PolylineOptions()
		             .addAll(pts) 
                     .width(8)
                     .zIndex(3)
                     .visible(true)
                     .geodesic(true)
                     .color(Color.GREEN);
		polyline=aMap.addPolyline(polylineOptions);
		CameraUpdate cu=CameraUpdateFactory.changeLatLng(new LatLng(Lat[Click],Lng[Click]));
		aMap.moveCamera(cu);
		
	}
	//�Զ���λ
	private void location() {
		// TODO Auto-generated method stub
		mLocationClient = new AMapLocationClient(getApplicationContext());
        //���ö�λ�ص�����
        mLocationClient.setLocationListener(this);
        //��ʼ����λ����
        mLocationOption = new AMapLocationClientOption();
        //���ö�λģʽΪHight_Accuracy�߾���ģʽ��Battery_SavingΪ�͹���ģʽ��Device_Sensors�ǽ��豸ģʽ
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //�����Ƿ񷵻ص�ַ��Ϣ��Ĭ�Ϸ��ص�ַ��Ϣ��
        mLocationOption.setNeedAddress(true);
        //�����Ƿ�ֻ��λһ��,Ĭ��Ϊfalse
        mLocationOption.setOnceLocation(true);
        //�����Ƿ�ǿ��ˢ��WIFI��Ĭ��Ϊǿ��ˢ��
        mLocationOption.setWifiActiveScan(true);
        //�����Ƿ�����ģ��λ��,Ĭ��Ϊfalse��������ģ��λ��
//        mLocationOption.setMockEnable(false);
        //���ö�λ���,��λ����,Ĭ��Ϊ2000ms
//        mLocationOption.setInterval(2000);
        //���ؾ�γ�ȵ�ͬʱ���ص�ַ����
        mLocationOption.setNeedAddress(true);
        //����λ�ͻ��˶������ö�λ����
        mLocationClient.setLocationOption(mLocationOption);
        //������λ
        mLocationClient.startLocation();
	}

	//IP��ַ��ʽ�Ƿ���ȷ
	private boolean isIPValid() {
		// TODO Auto-generated method stub

		String IPStr = input1.getText().toString();
		int start = IPStr.indexOf(":");
		if (input1.getText().toString().trim().equals("")) {
			Toast.makeText(MainActivity.this, "IP����Ϊ�գ�", Toast.LENGTH_SHORT)
					.show();
			return false;
		} else if ((start == -1) || (start + 1 >= IPStr.length())) {
			Toast.makeText(MainActivity.this, "IP��ַ���Ϸ�", Toast.LENGTH_SHORT)
					.show();
			return false;
		}
		sIP = IPStr.substring(0, start);
		String sPort = IPStr.substring(start + 1);
		port = Integer.parseInt(sPort);
		return true;
	}
	 @Override
	  protected void onDestroy() {
	    super.onDestroy();
	    //��activityִ��onDestroyʱִ��mMapView.onDestroy()��ʵ�ֵ�ͼ�������ڹ���
	    mMapView.onDestroy();
	    mLocationClient.stopLocation();//ֹͣ��λ
        mLocationClient.onDestroy();//���ٶ�λ�ͻ��ˡ�
	  }
	 @Override
	 protected void onResume() {
	    super.onResume();
	    //��activityִ��onResumeʱִ��mMapView.onResume ()��ʵ�ֵ�ͼ�������ڹ���
	    mMapView.onResume();
	    }
	 @Override
	 protected void onPause() {
	    super.onPause();
	    //��activityִ��onPauseʱִ��mMapView.onPause ()��ʵ�ֵ�ͼ�������ڹ���
	    mMapView.onPause();
	    }
	 @Override
	 protected void onSaveInstanceState(Bundle outState) {
	    super.onSaveInstanceState(outState);
	    //��activityִ��onSaveInstanceStateʱִ��mMapView.onSaveInstanceState (outState)��ʵ�ֵ�ͼ�������ڹ���
	    mMapView.onSaveInstanceState(outState);
	  }

	@Override
	public void activate(OnLocationChangedListener onLocationChangedListener) {
	}


	@Override
	public void deactivate() {
	}

	@Override
	public void onLocationChanged(AMapLocation aMapLocation) {
		// TODO Auto-generated method stub
		 if (aMapLocation != null) {
	            if (aMapLocation.getErrorCode() == 0) {
	                //��λ�ɹ��ص���Ϣ�����������Ϣ
	                aMapLocation.getLocationType();//��ȡ��ǰ��λ�����Դ�������綨λ���������ٷ���λ���ͱ�
	                aMapLocation.getLatitude();//��ȡγ��
	                aMapLocation.getLongitude();//��ȡ����
	                aMapLocation.getAccuracy();//��ȡ������Ϣ
	                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	                Date date = new Date(aMapLocation.getTime());
	                df.format(date);//��λʱ��
	                aMapLocation.getAddress();//��ַ�����option������isNeedAddressΪfalse����û�д˽�������綨λ����л��е�ַ��Ϣ��GPS��λ�����ص�ַ��Ϣ��
	                aMapLocation.getCountry();//������Ϣ
	                aMapLocation.getProvince();//ʡ��Ϣ
	                aMapLocation.getCity();//������Ϣ
	                aMapLocation.getDistrict();//������Ϣ
	                aMapLocation.getStreet();//�ֵ���Ϣ
	                aMapLocation.getStreetNum();//�ֵ����ƺ���Ϣ
	                aMapLocation.getCityCode();//���б���
	                aMapLocation.getAdCode();//��������

	                cityName=aMapLocation.getCity();
	                province=aMapLocation.getProvince();
	                district=aMapLocation.getDistrict();
	                street=aMapLocation.getStreet();
	                streetNum=aMapLocation.getStreetNum();
	                // ��������ñ�־λ����ʱ���϶���ͼʱ�����᲻�Ͻ���ͼ�ƶ�����ǰ��λ��
	                if (isFirstLoc) {
	                    //�������ż���
	                    aMap.moveCamera(CameraUpdateFactory.zoomTo(20));
	                    //����ͼ�ƶ�����λ��
	                    aMap.moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude())));
	                    //�����λ��ť �ܹ�����ͼ�������ƶ�����λ��
	                 //   mListener.onLocationChanged(aMapLocation);
	                    //���ͼ��
//	                      aMap.addMarker(getMarkerOptions(amapLocation));
	                    //��ȡ��λ��Ϣ
	                    StringBuffer buffer = new StringBuffer();
	                    buffer.append(aMapLocation.getLatitude() + "  "
	                            + aMapLocation.getLongitude() + "\n"
	                            +aMapLocation.getCountry() + ""
	                            + aMapLocation.getProvince() + ""
	                            + aMapLocation.getCity() + ""
	                            + aMapLocation.getDistrict() + ""
	                            + aMapLocation.getStreet() + ""
	                            + aMapLocation.getStreetNum());
	                    Toast.makeText(getApplicationContext(), buffer.toString(), Toast.LENGTH_LONG).show();
	                    isFirstLoc = false;
	                }
	            } else {
	                //��ʾ������ϢErrCode�Ǵ����룬errInfo�Ǵ�����Ϣ������������
	                Log.e("AmapError", "location Error, ErrCode:"
	                        + aMapLocation.getErrorCode() + ", errInfo:"
	                        + aMapLocation.getErrorInfo());
	                Toast.makeText(getApplicationContext(), "��λʧ��", Toast.LENGTH_LONG).show();
	            }
	        }
	}
    
	
}

