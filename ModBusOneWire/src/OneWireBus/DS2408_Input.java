package OneWireBus;

import com.dalsemi.onewire.container.OneWireContainer29;
import com.dalsemi.onewire.container.OneWireContainer3A;

public class DS2408_Input extends DeviceBase
{
	private InputChannel ch0 = new InputChannel();
	private InputChannel ch1 = new InputChannel();
	private InputChannel ch2 = new InputChannel();
	private InputChannel ch3 = new InputChannel();
	private InputChannel ch4 = new InputChannel();
	private InputChannel ch5 = new InputChannel();
	private InputChannel ch6 = new InputChannel();
	private InputChannel ch7 = new InputChannel();
	
	public DS2408_Input(String id) {
		super(id);
	}

	@Override
	public boolean SupportInput() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public void Read() 
	{
		OneWireContainer29 x = (OneWireContainer29)super.owd;
		ch0.setValue(x.getLevel(7, data));
		ch1.setValue(x.getLevel(6, data));
		ch2.setValue(x.getLevel(5, data));
		ch3.setValue(x.getLevel(4, data));
		ch4.setValue(x.getLevel(3, data));
		ch5.setValue(x.getLevel(2, data));
		ch6.setValue(x.getLevel(1, data));
		ch7.setValue(x.getLevel(0, data));
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
	
	public InputChannel getCh4()
	{
		return this.ch4;
	}
	
	public InputChannel getCh5()
	{
		return this.ch5;
	}
	
	public InputChannel getCh6()
	{
		return this.ch6;
	}
	
	public InputChannel getCh7()
	{
		return this.ch7;
	}

	@Override
	public void Write() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void InitWrite() {
		// TODO Auto-generated method stub
		
	}
}
