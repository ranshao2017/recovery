  
// @author briskman@163.com
// @date 20190704

// TODO 缺应用层封装



///////////////////////////////////////////////////////////////////////////////
// DEMO
///////////////////////////////////////////////////////////////////////////////

// 创建函数
public void CreateCab{

	List<CabBase> listCabs=new ArrayList<>();
	
	CabBase mainCab = new MainCab(0, "主柜", 1, "控制箱主柜");
	CabBase subCabPaper = new SubCab(1, "纸类柜", 2, "纸类柜，带压缩功能");
	CabBase subCabCotton = new SubCab(2, "棉织类", 3, "棉织类");
	CabBase subCabPlastic = new SubCab(3, "塑料类", 2, "塑料类");
	CabBase subCabMetal = new SubCab(4, "金属类", 2, "金属类");
	
	listCabs.add(mainCab);
	listCabs.add(subCabPaper);
	listCabs.add(subCabCotton);
	listCabs.add(subCabPlastic);
	listCabs.add(subCabMetal);
	

}

	///////////////////////////////////////////////////////////////////////////////
	// 以下模拟纸类投递,开始投递
public bool SimPaperTestStartButton()
{
	
	// 用户选择纸类投递
	CabBase currentCab = subCabPaper;
	
	
	// 投递前称重
	float weightOld = currentCab.Weight;
	
	// 打开投递门
	currentCab.OpenDeliveryDoor(true);
	
	// 延迟
	Sleep(100);
	
	//检查投递门打开状态
	if (currentCab.DeliveryDoorOpenFlag == false)
	{
		// 用户提示，打开投递门失败
		return false;
	}
	
	// 主界面刷新已经投递的重量
	float weightIncrease = currentCab.Weight-weightOld;
	
	// 语音播报，打开箱门成功
	
	return true;
}

public bool SimPaperTestStopButton()
{
	
	// 用户选择纸类投递
	CabBase currentCab = subCabPaper;
	
	// 关闭箱门
	currentCab.CloseDeliveryDoor(false);
	
	// 延迟
	Sleep(100);
	
	//检查投递门打开状态
	if (currentCab.DeliveryDoorOpenFlag == true)
	{
		// 用户提示，关闭投递门失败。提示再次关闭。有可能人手阻挡，防护保护
		return false;
	}	
	
	// 关闭后称重
	float weightNow = subCabPaper.Weight;
	
	// 增加重量
	float weightDelta = weightNow-currentCab；
	
	// 取单价信息,服务器或者本地返回
	float price = getFromCloudPrice;
	
	// 本次产生的费用
	float fee = price *weightDelta;
	
	// 语音提示 投递完成
	
	
}

// 定期更新， 1~5秒
public void Timer_Update()
{
	float MaxAlarmTemperature =60；
	float MaxCabCapacity = 90;
	for (CabBase current : listCabs) {
            
            // 读取垃圾箱容量，数据缓存到后台
            cabCapacity = current.Capacity;
            
            // 读取温度，数据缓存到后台
            capTemp = current.Temperatue;
            
            if (capTemp > MaxAlarmTemperature)
            {
            	// 灭火
            	current.OpenFireFighting();
            	
            	//报警,发微信通知
            }
            
            // 读取垃圾箱容量，数据缓存到后台。显示每个箱体容量
            cabCapacity = current.Capacity;
            
            if (cabCapacity > MaxCabCapacity)
            {
            	
            	
            	//通知回收,发微信通知
            }
        }


}

///////////////////////////////////////////////////////////////////////////////
// 柜体管理基类
public class CabBase
{
	// 构造函数
	// @param index 队列索引
	// @param name  命名
	// @param commAddress  通讯地址
	// @param memo 补充描述
	public CabBase(int index, String name, int commAddress, String memo)
	{
		this._index = index;
		this._name = name;
		this._commAddress = commAddress;
		this._memo = memo;
	}
	
	int 		_index=0;
	String 	_name;
	int 		_commAddress;
	String 	_memo;
	
	// 箱体容量 0%~100%。表示是否满. >90%系统发通知给对应的回收人员，清洁
	public float Capacity
	{
		get;
	}
	
	// 获取箱体温度, 单位度。>60度可能着火，系统报警，通知管理元
	public float Temprature
	{
		get;
	}
	
	// 称重读数，单位Kg. 
	public float Weight
	{
		get;
	}
	
	// 箱满指示，表示回收箱已经满
	public bool FullFlag
	{
		get;
	}
	
	// 普通用户打开的，投递物体的投递门打开标志。 true== 打开
	// 开门前称重，开门后称重， 重量差计费
	// 此处重量一般是增加
	public bool DeliveryDoorOpenFlag
	{
		get;
	}
	
	// 回收员打开门，进行物品回收。此处存在结算。
	// 开门前称重，开门后称重， 重量差计费
	// 此处重量一般是减轻，因为已经取出
	public bool RecyclingDoorOpenFlag
	{
		get;
	}
	
	// 回收框是不是放好，true=已经到位
	// RecyclingDoor 关闭后，要检查此标志，判断回收框放好
	public bool RecyclingBoxReadyFlag
	{
		get;
	}
	
	// 打开面板指示灯
	// @param openFlag true-打开； false -关闭
	public void OpenPanelLight(bool openFlag = true)
	{
	}
	
	// 打开投递门，打开为普通用户投递
	// @ref 打开或者关闭都要检查这个标记：DeliveryDoorOpenFlag
	// @param openFlag true-打开； false -关闭
	public void OpenDeliveryDoor(bool openFlag = true)
	{
	}
	
	// 打开回收门， 管理员或者回收员打开
	// @ref 打开或者关闭都要检查这个标记：RecyclingDoorOpenFlag
	public void OpenRecyclingDoor()
	{
	}
	
	// 打开灭火器，若系统配置则打开。 一般判断温度过高会保护，判断依据：Temprature
	public void OpenFireFighting()
	{
	}
	
	// 打开除臭
	// @param openFlag true-打开； false -关闭
	public void OpenDeodorization(bool openFlag = true)
	{
		
	}
	

}

// 主柜操作
public class MainCab extends CabBase
{
	// 药品门打开状态
	public bool DrugsDoorOpenFlag
	{
		get;
	}
	
	// 电池投递门打开标志
	public bool BatteryDoorOpenFlag
	{
		get;
	}
	
	// 易拉罐玻璃瓶投递门
	public bool CansDoorOpenFlag
	{
		get;
		
	}
	
	// 易拉罐计数
	// OpenCansDoor() 开始计数，关闭后停止计数
	public int CansCount
	{
		get;
	}
	
	// 打开药品投递门，打开为普通用户投递
	// @ref 打开或者关闭都要检查这个标记：DrugsDoorOpenFlag
	public void OpenDrugsDoor()
	{
	}
	
	// 打开电池投递门， 打开为普通用户投递
	// @ref 打开或者关闭都要检查这个标记：BatteryDoorOpenFlag
	public void OpenBatteryDoor()
	{
	}
	
	// 打开电池投递门， 打开为普通用户投递
	// 打开后开始计数，CansCount
	// @ref 打开或者关闭都要检查这个标记：CansDoorOpenFlag
	public void OpenCansDoor()
	{
	}
	
}

// 副柜
public class SubCab extends CabBase
{
	// 以下方法纸类柜才有.开始压缩，持续时间约2分钟。 压缩期间不能打开投递门
		public void StartCompress()
		{
		}
		
		// 以下方法纸类柜才有.停止压缩，
		public void StopCompress()
		{
		}
		
		// 以下方法纸类柜才有.复位压缩状态.复位后，才能打开投递门
		public void ResetCompress()
		{
		}	
}


/////////////////////////////////////////////////////////////////////////////////
// 硬件驱动
public class HardDriver
{
	public enum EnumChan {
    CH_MAIN=0, CH_SUB1, CH_SUB2, CH_SUB3, CH_SUB_4,CH_SUB5, CH_MAIN;
	}
	
	// 环境温度，度
	private float m_fTemperature=0;
	
	// 距离 20~150cm
	private float m_fDistance =0;
	
	// 重量 Kg
	private float m_fWeight=0;	
	
	// 批量读取模拟量
	public bool HD_ReadAnalogInAll(EnumChan ch)
	{
		m_fTemperature =0;
		m_fDistance=0;
		m_fWeight=0;
		return false;
	}
	
	// 称重除皮初始化， 界面单独按钮
	public bool HD_WriteWeighingPeeling(EnumChan ch)
	{
	}
	
	// 批量读取数字量输入 X0~X13
	public bool HD_ReadDigitalInAll(EnumChan ch)
	{
	}
	
	// 一个数字量读取
	// 返回Xn状态， true = 1； false = 0xn
	// @param xn 数字量输入 X0~X13
	// @param bForceUpdateFlag 强制更新
	public bool HD_ReadDigitalIn((EnumChan ch, int , bool bForceUpdateFlag = false)
	{
		if (bForceUpdateFlag == true)
			HD_ReadDigitalInAll(ch);
			
			
		switch (xn)
		{
		}
		
	}
	
	// 批量读取数字量控制 Y0~Y7
	public bool HD_WriteDigitalOutAll(EnumChan ch)
	{
	}
	
	// 一个数字量控制
	// @param yn Y0~Y7
	// @param flag 1/0
	public bool HD_WriteDigitalOut((EnumChan ch, int yn,int flag,  bool bForceUpdateFlag = false)
	{
		switch (yn)
		{
		}
		
		if (true == bForceUpdateFlag)
		{
			HD_WriteDigitalOutAll(ch);
		}
	}
	
}