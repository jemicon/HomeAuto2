package OneWireBus;

import com.dalsemi.onewire.OneWireException;
import com.dalsemi.onewire.adapter.OneWireIOException;
import com.dalsemi.onewire.container.OneWireContainer3A;
import com.dalsemi.onewire.container.OneWireContainer29;

public class DS2408_Dual extends DeviceBase
{
	private int Inputch0;
	private int Inputch1;
	private int Inputch2;
	private int Inputch3;
	
	private InputChannel ch0 = new InputChannel();
	private InputChannel ch1 = new InputChannel();
	private InputChannel ch2 = new InputChannel();
	private InputChannel ch3 = new InputChannel();
	private OutputChannel och0;
	private OutputChannel och1;
	private OutputChannel och2;
	private OutputChannel och3;

	public DS2408_Dual(String id, int ch0, int ch1, int ch2, int ch3, int outch0, int outch1, int outch2, int outch3,
			float pw01, float pw02, float pw03, float pw04) 
	{
		super(id);

		this.Inputch0 = ch0;
		this.Inputch1 = ch1;
		this.Inputch2 = ch2;
		this.Inputch3 = ch3;
		
		och0 = new OutputChannel(outch0, pw01);
		och1 = new OutputChannel(outch1, pw02);
		och2 = new OutputChannel(outch2, pw03);
		och3 = new OutputChannel(outch3, pw04);
	}

	@Override
	public void Read() 
	{
		OneWireContainer29 x = (OneWireContainer29)super.owd;
		
		ch0.setValue(x.getLevel(Inputch0, data));		
		ch1.setValue(x.getLevel(Inputch1, data));	
		ch2.setValue(x.getLevel(Inputch2, data));	
		ch3.setValue(x.getLevel(Inputch3, data));
	}
	
	public InputChannel getCh0()
	{
		return this.ch0;
	}
	
	public InputChannel getCh1()
	{
		return this.ch1;
	}
	
	public InputChannel getCh2()
	{
		return this.ch2;
	}
	
	public InputChannel getCh3()
	{
		return this.ch3;
	}
	
	public OutputChannel getOutCh0()
	{
		return this.och0;
	}
	
	public OutputChannel getOutCh1()
	{
		return this.och1;
	}
	
	public OutputChannel getOutCh2()
	{
		return this.och2;
	}
	
	public OutputChannel getOutCh3()
	{
		return this.och3;
	}
	
	@Override
	public void Write() 
	{
		if (och0.NeedToWrite())
		{
			DoWrite(och0);
		}
		
		if (och1.NeedToWrite())
		{
			DoWrite(och1);
		}
		
		if (och2.NeedToWrite())
		{
			DoWrite(och2);
		}
		
		if (och3.NeedToWrite())
		{
			DoWrite(och3);
		}
	}

	@Override
	public void InitWrite() 
	{
		InitOutput(och0);
		InitOutput(och1);
		InitOutput(och2);
		InitOutput(och3);
	}

	@Override
	public boolean SupportInput() 
	{
		// TODO Auto-generated method stub
		return true;
	}
}
