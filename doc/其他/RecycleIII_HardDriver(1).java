  
// @author briskman@163.com
// @date 20190704

// TODO ȱӦ�ò��װ



///////////////////////////////////////////////////////////////////////////////
// DEMO
///////////////////////////////////////////////////////////////////////////////

// ��������
public void CreateCab{

	List<CabBase> listCabs=new ArrayList<>();
	
	CabBase mainCab = new MainCab(0, "����", 1, "����������");
	CabBase subCabPaper = new SubCab(1, "ֽ���", 2, "ֽ��񣬴�ѹ������");
	CabBase subCabCotton = new SubCab(2, "��֯��", 3, "��֯��");
	CabBase subCabPlastic = new SubCab(3, "������", 2, "������");
	CabBase subCabMetal = new SubCab(4, "������", 2, "������");
	
	listCabs.add(mainCab);
	listCabs.add(subCabPaper);
	listCabs.add(subCabCotton);
	listCabs.add(subCabPlastic);
	listCabs.add(subCabMetal);
	

}

	///////////////////////////////////////////////////////////////////////////////
	// ����ģ��ֽ��Ͷ��,��ʼͶ��
public bool SimPaperTestStartButton()
{
	
	// �û�ѡ��ֽ��Ͷ��
	CabBase currentCab = subCabPaper;
	
	
	// Ͷ��ǰ����
	float weightOld = currentCab.Weight;
	
	// ��Ͷ����
	currentCab.OpenDeliveryDoor(true);
	
	// �ӳ�
	Sleep(100);
	
	//���Ͷ���Ŵ�״̬
	if (currentCab.DeliveryDoorOpenFlag == false)
	{
		// �û���ʾ����Ͷ����ʧ��
		return false;
	}
	
	// ������ˢ���Ѿ�Ͷ�ݵ�����
	float weightIncrease = currentCab.Weight-weightOld;
	
	// ���������������ųɹ�
	
	return true;
}

public bool SimPaperTestStopButton()
{
	
	// �û�ѡ��ֽ��Ͷ��
	CabBase currentCab = subCabPaper;
	
	// �ر�����
	currentCab.CloseDeliveryDoor(false);
	
	// �ӳ�
	Sleep(100);
	
	//���Ͷ���Ŵ�״̬
	if (currentCab.DeliveryDoorOpenFlag == true)
	{
		// �û���ʾ���ر�Ͷ����ʧ�ܡ���ʾ�ٴιرա��п��������赲����������
		return false;
	}	
	
	// �رպ����
	float weightNow = subCabPaper.Weight;
	
	// ��������
	float weightDelta = weightNow-currentCab��
	
	// ȡ������Ϣ,���������߱��ط���
	float price = getFromCloudPrice;
	
	// ���β����ķ���
	float fee = price *weightDelta;
	
	// ������ʾ Ͷ�����
	
	
}

// ���ڸ��£� 1~5��
public void Timer_Update()
{
	float MaxAlarmTemperature =60��
	float MaxCabCapacity = 90;
	for (CabBase current : listCabs) {
            
            // ��ȡ���������������ݻ��浽��̨
            cabCapacity = current.Capacity;
            
            // ��ȡ�¶ȣ����ݻ��浽��̨
            capTemp = current.Temperatue;
            
            if (capTemp > MaxAlarmTemperature)
            {
            	// ���
            	current.OpenFireFighting();
            	
            	//����,��΢��֪ͨ
            }
            
            // ��ȡ���������������ݻ��浽��̨����ʾÿ����������
            cabCapacity = current.Capacity;
            
            if (cabCapacity > MaxCabCapacity)
            {
            	
            	
            	//֪ͨ����,��΢��֪ͨ
            }
        }


}

///////////////////////////////////////////////////////////////////////////////
// ����������
public class CabBase
{
	// ���캯��
	// @param index ��������
	// @param name  ����
	// @param commAddress  ͨѶ��ַ
	// @param memo ��������
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
	
	// �������� 0%~100%����ʾ�Ƿ���. >90%ϵͳ��֪ͨ����Ӧ�Ļ�����Ա�����
	public float Capacity
	{
		get;
	}
	
	// ��ȡ�����¶�, ��λ�ȡ�>60�ȿ����Ż�ϵͳ������֪ͨ����Ԫ
	public float Temprature
	{
		get;
	}
	
	// ���ض�������λKg. 
	public float Weight
	{
		get;
	}
	
	// ����ָʾ����ʾ�������Ѿ���
	public bool FullFlag
	{
		get;
	}
	
	// ��ͨ�û��򿪵ģ�Ͷ�������Ͷ���Ŵ򿪱�־�� true== ��
	// ����ǰ���أ����ź���أ� ������Ʒ�
	// �˴�����һ��������
	public bool DeliveryDoorOpenFlag
	{
		get;
	}
	
	// ����Ա���ţ�������Ʒ���ա��˴����ڽ��㡣
	// ����ǰ���أ����ź���أ� ������Ʒ�
	// �˴�����һ���Ǽ��ᣬ��Ϊ�Ѿ�ȡ��
	public bool RecyclingDoorOpenFlag
	{
		get;
	}
	
	// ���տ��ǲ��Ƿźã�true=�Ѿ���λ
	// RecyclingDoor �رպ�Ҫ���˱�־���жϻ��տ�ź�
	public bool RecyclingBoxReadyFlag
	{
		get;
	}
	
	// �����ָʾ��
	// @param openFlag true-�򿪣� false -�ر�
	public void OpenPanelLight(bool openFlag = true)
	{
	}
	
	// ��Ͷ���ţ���Ϊ��ͨ�û�Ͷ��
	// @ref �򿪻��߹رն�Ҫ��������ǣ�DeliveryDoorOpenFlag
	// @param openFlag true-�򿪣� false -�ر�
	public void OpenDeliveryDoor(bool openFlag = true)
	{
	}
	
	// �򿪻����ţ� ����Ա���߻���Ա��
	// @ref �򿪻��߹رն�Ҫ��������ǣ�RecyclingDoorOpenFlag
	public void OpenRecyclingDoor()
	{
	}
	
	// �����������ϵͳ������򿪡� һ���ж��¶ȹ��߻ᱣ�����ж����ݣ�Temprature
	public void OpenFireFighting()
	{
	}
	
	// �򿪳���
	// @param openFlag true-�򿪣� false -�ر�
	public void OpenDeodorization(bool openFlag = true)
	{
		
	}
	

}

// �������
public class MainCab extends CabBase
{
	// ҩƷ�Ŵ�״̬
	public bool DrugsDoorOpenFlag
	{
		get;
	}
	
	// ���Ͷ���Ŵ򿪱�־
	public bool BatteryDoorOpenFlag
	{
		get;
	}
	
	// �����޲���ƿͶ����
	public bool CansDoorOpenFlag
	{
		get;
		
	}
	
	// �����޼���
	// OpenCansDoor() ��ʼ�������رպ�ֹͣ����
	public int CansCount
	{
		get;
	}
	
	// ��ҩƷͶ���ţ���Ϊ��ͨ�û�Ͷ��
	// @ref �򿪻��߹رն�Ҫ��������ǣ�DrugsDoorOpenFlag
	public void OpenDrugsDoor()
	{
	}
	
	// �򿪵��Ͷ���ţ� ��Ϊ��ͨ�û�Ͷ��
	// @ref �򿪻��߹رն�Ҫ��������ǣ�BatteryDoorOpenFlag
	public void OpenBatteryDoor()
	{
	}
	
	// �򿪵��Ͷ���ţ� ��Ϊ��ͨ�û�Ͷ��
	// �򿪺�ʼ������CansCount
	// @ref �򿪻��߹رն�Ҫ��������ǣ�CansDoorOpenFlag
	public void OpenCansDoor()
	{
	}
	
}

// ����
public class SubCab extends CabBase
{
	// ���·���ֽ������.��ʼѹ��������ʱ��Լ2���ӡ� ѹ���ڼ䲻�ܴ�Ͷ����
		public void StartCompress()
		{
		}
		
		// ���·���ֽ������.ֹͣѹ����
		public void StopCompress()
		{
		}
		
		// ���·���ֽ������.��λѹ��״̬.��λ�󣬲��ܴ�Ͷ����
		public void ResetCompress()
		{
		}	
}


/////////////////////////////////////////////////////////////////////////////////
// Ӳ������
public class HardDriver
{
	public enum EnumChan {
    CH_MAIN=0, CH_SUB1, CH_SUB2, CH_SUB3, CH_SUB_4,CH_SUB5, CH_MAIN;
	}
	
	// �����¶ȣ���
	private float m_fTemperature=0;
	
	// ���� 20~150cm
	private float m_fDistance =0;
	
	// ���� Kg
	private float m_fWeight=0;	
	
	// ������ȡģ����
	public bool HD_ReadAnalogInAll(EnumChan ch)
	{
		m_fTemperature =0;
		m_fDistance=0;
		m_fWeight=0;
		return false;
	}
	
	// ���س�Ƥ��ʼ���� ���浥����ť
	public bool HD_WriteWeighingPeeling(EnumChan ch)
	{
	}
	
	// ������ȡ���������� X0~X13
	public bool HD_ReadDigitalInAll(EnumChan ch)
	{
	}
	
	// һ����������ȡ
	// ����Xn״̬�� true = 1�� false = 0xn
	// @param xn ���������� X0~X13
	// @param bForceUpdateFlag ǿ�Ƹ���
	public bool HD_ReadDigitalIn((EnumChan ch, int , bool bForceUpdateFlag = false)
	{
		if (bForceUpdateFlag == true)
			HD_ReadDigitalInAll(ch);
			
			
		switch (xn)
		{
		}
		
	}
	
	// ������ȡ���������� Y0~Y7
	public bool HD_WriteDigitalOutAll(EnumChan ch)
	{
	}
	
	// һ������������
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