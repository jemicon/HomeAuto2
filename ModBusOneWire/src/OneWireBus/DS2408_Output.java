package OneWireBus;

import com.dalsemi.onewire.OneWireException;
import com.dalsemi.onewire.adapter.OneWireIOException;
import com.dalsemi.onewire.container.OneWireContainer3A;

public class DS2408_Output extends DeviceBase
{
	private OutputChannel ch0;
	private OutputChannel ch1;
	private OutputChannel ch2;
	private OutputChannel ch3;
	private OutputChannel ch4;
	private OutputChannel ch5;
	private OutputChannel ch6;
	private OutputChannel ch7;

	public DS2408_Output(String id, float pw01, float pw02, float pw03, float pw04, float pw05, float pw06, float pw07, float pw08) {
		super(id);
		ch0 = new OutputChannel(0, pw01);
		ch1 = new OutputChannel(1, pw02);
		ch2 = new OutputChannel(2, pw03);
		ch3 = new OutputChannel(3, pw04);
		ch4 = new OutputChannel(4, pw05);
		ch5 = new OutputChannel(5, pw06);
		ch6 = new OutputChannel(6, pw07);
		ch7 = new OutputChannel(7, pw08);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean SupportInput() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void Write() 
	{
		if (ch0.NeedToWrite())
		{
			DoWrite(ch0);
		}
		
		if (ch1.NeedToWrite())
		{
			DoWrite(ch1);
		}
		
		if (ch2.NeedToWrite())
		{
			DoWrite(ch2);
		}
		
		if (ch3.NeedToWrite())
		{
			DoWrite(ch3);
		}
		
		if (ch4.NeedToWrite())
		{
			DoWrite(ch4);
		}
		
		if (ch5.NeedToWrite())
		{
			DoWrite(ch5);
		}
		
		if (ch6.NeedToWrite())
		{
			DoWrite(ch6);
		}
		
		if (ch7.NeedToWrite())
		{
			DoWrite(ch7);
		}
	}
	
	public OutputChannel getCh0()
	{
		return this.ch0;
	}
	
	public OutputChannel getCh1()
	{
		return this.ch1;
	}
	
	public OutputChannel getCh2()
	{
		return this.ch2;
	}
	
	public OutputChannel getCh3()
	{
		return this.ch3;
	}
	
	public OutputChannel getCh4()
	{
		return this.ch4;
	}
	
	public OutputChannel getCh5()
	{
		return this.ch5;
	}
	
	public OutputChannel getCh6()
	{
		return this.ch6;
	}
	
	public OutputChannel getCh7()
	{
		return this.ch7;
	}

	@Override
	public void Read() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void InitWrite() {
		InitOutput(ch0);
		InitOutput(ch1);
		InitOutput(ch2);
		InitOutput(ch3);
		InitOutput(ch4);
		InitOutput(ch5);
		InitOutput(ch6);
		InitOutput(ch7);
	}

}
