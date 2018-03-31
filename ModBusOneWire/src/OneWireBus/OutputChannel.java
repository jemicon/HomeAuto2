package OneWireBus;

import java.nio.ByteBuffer;

import net.wimpi.modbus.procimg.DigitalOut;
import net.wimpi.modbus.procimg.Register;

public class OutputChannel implements DigitalOut, Register
{
	private Boolean currentState = false;
	private Boolean newState = false;
	private int channel;
	private long lastUpdate = 0;
	private double onTime = 0.0;
	private double power = 10.0;
	
	public OutputChannel(int channel, float pw)
	{
		this.channel = channel;
		this.power = pw;
		this.lastUpdate = System.currentTimeMillis();
	}
	
	public void setCurrent(boolean b) {
		this.currentState = b;
		this.newState = b;
	}
	
	@Override
	public boolean isSet() {
		return this.currentState;
	}

	@Override
	public void set(boolean b) {
		this.newState = b;
	}

	public Boolean NeedToWrite()
	{
		return this.newState != this.currentState;
	}
	
	public Boolean NewState()
	{
		return newState;
	}
	
	public int Channel()
	{
		return channel;
	}
	
	public void Done()
	{
		if (currentState && !newState)
		{
			this.onTime += System.currentTimeMillis() - this.lastUpdate;
		}
		
		currentState = newState;
		this.lastUpdate = System.currentTimeMillis();
	}

	@Override
	public int getValue() {
		// TODO Auto-generated method stub
		int i =(int)(power * this.onTime / 3600000000.0);
		return i;
	}

	@Override
	public int toUnsignedShort() {
		int i =(int)(power * this.onTime / 3600000000.0);
		return i;
	}

	@Override
	public short toShort() {
		short i =(short)(power * this.onTime / 3600000000.0);
		return i;
	}

	@Override
	public byte[] toBytes() {
		byte[] b = ByteBuffer.allocate(2).putShort(this.toShort()).array();
		return b;
	}

	@Override
	public void setValue(int v) {
		double d = v;
		this.onTime = 3600000.0 * (d / this.power);
	}

	@Override
	public void setValue(short s) {
		double d = s;
		this.onTime = 3600000.0 * (d / this.power);
	}

	@Override
	public void setValue(byte[] bytes) {
		// TODO Auto-generated method stub
		
	}
	
}
