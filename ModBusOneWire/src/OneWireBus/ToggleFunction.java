package OneWireBus;

public class ToggleFunction implements IChange
{
	private OutputChannel ch;
	
	public ToggleFunction(OutputChannel outputCh)
	{
		this.ch = outputCh;
	}

	@Override
	public void OnInputChange(boolean oldValue, boolean newValue) 
	{
		boolean state;
		if (oldValue == false && newValue == true)
		{
			state = this.ch.isSet();
			this.ch.set(!state);
		}
	}
}
