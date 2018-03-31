package OneWireBus;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import net.wimpi.modbus.Modbus;
import net.wimpi.modbus.ModbusCoupler;
import net.wimpi.modbus.net.ModbusTCPListener;


public class TestRunner {

	public static void main(String[] args) 
	{
		ExecutorService executor = Executors.newSingleThreadExecutor();	
		BusContainer container = new BusContainer();
		
		DS2408_Output o = new DS2408_Output("E1000000134D5329", 10,10,10,10,10,10,10,10);
		DS2408_Input i = new DS2408_Input("7D000000134D0529");
		i.getCh0().SetFunction(new ToggleFunction(o.getCh0()));
		i.getCh1().SetFunction(new ToggleFunction(o.getCh1()));
		i.getCh2().SetFunction(new ToggleFunction(o.getCh2()));
		i.getCh3().SetFunction(new ToggleFunction(o.getCh3()));
		i.getCh4().SetFunction(new ToggleFunction(o.getCh4()));
		i.getCh5().SetFunction(new ToggleFunction(o.getCh5()));
		i.getCh6().SetFunction(new ToggleFunction(o.getCh6()));
		i.getCh7().SetFunction(new ToggleFunction(o.getCh7()));
		
		DS2408_Dual x = new DS2408_Dual("B500000013665729",1,3,5,7,0,2,4,6, 10,10,10,10);
		x.getCh0().SetFunction(new ToggleFunction(x.getOutCh0()));
		x.getCh1().SetFunction(new ToggleFunction(x.getOutCh1()));
		x.getCh2().SetFunction(new ToggleFunction(x.getOutCh2()));
		x.getCh3().SetFunction(new ToggleFunction(x.getOutCh3()));
		
		container.AddDevice(i);
		container.AddDevice(o);
		container.AddDevice(x);
		
		o = new DS2408_Output("9600000013696F29", 10,10,10,10,10,10,10,10);
		i = new DS2408_Input("9100000013665829");
		i.getCh0().SetFunction(new ToggleFunction(o.getCh0()));
		i.getCh1().SetFunction(new ToggleFunction(o.getCh1()));
		i.getCh2().SetFunction(new ToggleFunction(o.getCh2()));
		i.getCh3().SetFunction(new ToggleFunction(o.getCh3()));
		i.getCh4().SetFunction(new ToggleFunction(o.getCh4()));
		i.getCh5().SetFunction(new ToggleFunction(o.getCh5()));
		i.getCh6().SetFunction(new ToggleFunction(o.getCh6()));
		i.getCh7().SetFunction(new ToggleFunction(o.getCh7()));
		
		x = new DS2408_Dual("E300000013692F29",1,3,5,7,0,2,4,6, 10,10,10,10);
		x.getCh0().SetFunction(new ToggleFunction(x.getOutCh0()));
		x.getCh1().SetFunction(new ToggleFunction(x.getOutCh1()));
		x.getCh2().SetFunction(new ToggleFunction(x.getOutCh2()));
		x.getCh3().SetFunction(new ToggleFunction(x.getOutCh3()));
		
		container.AddDevice(i);
		container.AddDevice(o);
		container.AddDevice(x);
		
		executor.execute(container);
		
		ModbusTCPListener listener = null;
		int port = Modbus.DEFAULT_PORT;
		
		  //3. Set the image on the coupler
		  ModbusCoupler.getReference().setProcessImage(container.ProcessImage());
		  ModbusCoupler.getReference().setMaster(false);
		  ModbusCoupler.getReference().setUnitID(15); 
		  
		//4. Create a listener with 3 threads in pool
		  listener = new ModbusTCPListener(3);
		  listener.setPort(port);
		  listener.start();  
		
		try
		  {
		    System.in.read();
		  }
		  catch (IOException e)
		  {
			  
		  } finally {
		}
		
	}

}
