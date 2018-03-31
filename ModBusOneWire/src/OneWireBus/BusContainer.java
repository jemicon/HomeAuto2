package OneWireBus;

import java.util.Vector;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import net.wimpi.modbus.procimg.*;

import com.dalsemi.onewire.OneWireAccessProvider;
import com.dalsemi.onewire.OneWireException;
import com.dalsemi.onewire.adapter.DSPortAdapter;
import com.dalsemi.onewire.adapter.OneWireIOException;

public class BusContainer implements Runnable
{
	Thread t;
	ExecutorService executor;
	Vector<DeviceBase> devices;
	DSPortAdapter adapter = null;
	OneWireProcessImage processImage;
	
	public BusContainer()
	{
		executor = Executors.newSingleThreadExecutor();
		devices = new Vector<DeviceBase>();
		processImage = new OneWireProcessImage();
	}
	
	public ProcessImage ProcessImage()
	{
		return processImage;
	}
	
	/*
	 * Start the thread and begins polling of inputs/registers.
	 */
	public void Start()
	{
		try {
			OneWireAccessProvider.clearUseOverridingAdapter();
			//adapter = OneWireAccessProvider.getAdapter("{DS9490}", "USB1");
			adapter = OneWireAccessProvider.getAdapter("DS9097U", "/dev/ttyS0");
			//adapter = OneWireAccessProvider.getAdapter("DS9097U", "COM4");
			adapter.beginExclusive(true);
			adapter.setSpeed(0);
			
		} catch (OneWireIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OneWireException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * Adds device to container and register it.
	 */
	public void AddDevice(DeviceBase device)
	{
		devices.addElement(device);
				
		if (device.getClass().getSimpleName().equalsIgnoreCase("DS2408_Input"))
		{
			this.processImage.addDigitalIn((DS2408_Input)device);
		}
		
		if (device.getClass().getSimpleName().equalsIgnoreCase("DS2408_Output"))
		{
			this.processImage.addDigitalOut((DS2408_Output)device);
		}
		
		if (device.getClass().getSimpleName().equalsIgnoreCase("DS2408_Dual"))
		{
			DS2408_Dual x = (DS2408_Dual) device;
			this.processImage.addDigitalIn(x.getCh0());
			this.processImage.addDigitalIn(x.getCh1());
			this.processImage.addDigitalIn(x.getCh2());
			this.processImage.addDigitalIn(x.getCh3());
			
			this.processImage.addDigitalOut(x.getOutCh0());
			this.processImage.addDigitalOut(x.getOutCh1());
			this.processImage.addDigitalOut(x.getOutCh2());
			this.processImage.addDigitalOut(x.getOutCh3());
			
			this.processImage.addRegister(x.getOutCh0());
			this.processImage.addRegister(x.getOutCh1());
			this.processImage.addRegister(x.getOutCh2());
			this.processImage.addRegister(x.getOutCh3());
		}
	}

	@Override
	public void run() 
	{
		Start();
		for(DeviceBase device : devices)
		{
			device.owd = adapter.getDeviceContainer(device.id);
			int i = device.owd.getMaxSpeed();
			device.owd.setSpeed(1, false);
			try 
			{
				device.owd.doSpeed();
				
			} catch (OneWireException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			device.InitWrite();
		}
		
		while (true)
		{
			long t1 = System.currentTimeMillis();
			DoCycle();
			long t2 = System.currentTimeMillis();
			try 
			{
				if (180 - (t2 - t1) > 0)
				{
					System.out.println(t2-t1);
					Thread.sleep(180 - (t2 - t1));
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void DoCycle()
	{
		for(DeviceBase device : devices)
		{
			if (device.SupportInput())
			{
				Runnable x = new DeviceRead(device);
				x.run();
			}
		}
		
		for(DeviceBase device : devices)
		{
			device.Write();
		}
			
	}
}
