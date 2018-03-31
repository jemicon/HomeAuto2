package OneWireBus;

import java.util.concurrent.Callable;

import com.dalsemi.onewire.*;
import com.dalsemi.onewire.adapter.*;
import com.dalsemi.onewire.container.*;

public class DeviceRead implements Runnable
{
	private DeviceBase device;
	
	public DeviceRead(DeviceBase device)
	{
		this.device = device;
	}
	
	@Override
	public void run() 
	{
		long t1 = System.currentTimeMillis();
		
		/*try 
		{
			if (!device.owd.isPresent()) return;
		} catch (OneWireIOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (OneWireException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		
		OneWireContainer29 actual = (OneWireContainer29)device.owd;
		
		try 
		{
			device.data = actual.readDevice();
			device.Read();
			
		} catch (OneWireIOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OneWireException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		long t2 = System.currentTimeMillis();
		System.out.println("cycletime : " + (t2 - t1));
	}
}
