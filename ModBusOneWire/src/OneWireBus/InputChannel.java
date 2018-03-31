package OneWireBus;

import net.wimpi.modbus.procimg.DigitalIn;

public class InputChannel implements DigitalIn
{
	private Boolean state = false;
	private Boolean longState = false;
	private long timeOfUpdate = 0;
	private IChange function = null;
	private Boolean nextstate = false; 

	public void setValue(Boolean b)
	{
		long t = System.currentTimeMillis();
		if (b && !this.state)
		{
			timeOfUpdate = t;
		}
		
		if (this.state != b)
		{
			if (this.function != null)
			{
				this.function.OnInputChange(this.state, b);
			}
		}
		
		if (b)
		{
			this.nextstate = b;
		}
		
		this.state = b;
		if (b)
		{
			if ((t - timeOfUpdate) > 2000)
			{
				longState = true;
			}
		}
	}
	
	@Override
	public boolean isSet() 
	{
		boolean b =  this.nextstate;
		this.nextstate = this.state;
		return b;
	}
	
	public void SetFunction(IChange func)
	{
		this.function = func;	
	}

}
