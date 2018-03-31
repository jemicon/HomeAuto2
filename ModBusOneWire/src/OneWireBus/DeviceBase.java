package OneWireBus;

import com.dalsemi.onewire.*;
import com.dalsemi.onewire.adapter.*;
import com.dalsemi.onewire.container.*;

public abstract class DeviceBase
{
	protected OneWireContainer owd;
	protected String familyType;
	protected byte[] data;
	protected String id;
	
	public DeviceBase(String id) 
	{
		this.id = id;
	}
	
	public abstract boolean SupportInput();
	
	public abstract void Read();
	
	public abstract void Write();
	
	public abstract void InitWrite();
	
	protected void DoWrite(OutputChannel ch)
	{
		OneWireContainer29 device = (OneWireContainer29) owd;
		try 
		{
			this.data = device.readDevice();
			device.setLatchState(ch.Channel(), !ch.NewState(), true, data);
			device.writeDevice(data);
			ch.Done();
		} catch (OneWireIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OneWireException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	protected void InitOutput(OutputChannel ch)
	{
		OneWireContainer29 device = (OneWireContainer29) owd;
		try {
			this.data = device.readDevice();
			ch.setCurrent(!device.getLatchState(ch.Channel(), this.data));
			
		} catch (OneWireIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OneWireException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
