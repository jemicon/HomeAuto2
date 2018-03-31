package OneWireBus;

import java.util.Vector;

import net.wimpi.modbus.procimg.DigitalIn;
import net.wimpi.modbus.procimg.DigitalOut;
import net.wimpi.modbus.procimg.IllegalAddressException;
import net.wimpi.modbus.procimg.InputRegister;
import net.wimpi.modbus.procimg.ProcessImageImplementation;
import net.wimpi.modbus.procimg.Register;

/**
 * 
 */
public class OneWireProcessImage
    implements ProcessImageImplementation {

  //instance attributes
  protected Vector m_DigitalInputs;
  protected Vector m_DigitalOutputs;
  protected Vector m_InputRegisters;
  protected Vector m_Registers;
  protected boolean m_Locked = false;

  /**
   * Constructs a new <tt>OneWireProcessImage</tt> instance.
   */
  public OneWireProcessImage() {
    m_DigitalInputs = new Vector();
    m_DigitalOutputs = new Vector();
    m_InputRegisters = new Vector();
    m_Registers = new Vector();
  }//SimpleProcessImage

  public boolean isLocked() {
    return m_Locked;
  }//isLocked

  public void setLocked(boolean locked) {
    m_Locked = locked;
  }//setLocked

  public void addDigitalIn(DigitalIn di) {
    if (!isLocked()) {
      m_DigitalInputs.addElement(di);
    }
  }//addDigitalIn
  
  public void addDigitalIn(DS2408_Input di) {
	    if (!isLocked()) {
	      m_DigitalInputs.addElement(di.getCh0());
	      m_DigitalInputs.addElement(di.getCh1());
	      m_DigitalInputs.addElement(di.getCh2());
	      m_DigitalInputs.addElement(di.getCh3());
	      m_DigitalInputs.addElement(di.getCh4());
	      m_DigitalInputs.addElement(di.getCh5());
	      m_DigitalInputs.addElement(di.getCh6());
	      m_DigitalInputs.addElement(di.getCh7());
	    }
	  }//addDigitalIn
  

  public void removeDigitalIn(DigitalIn di) {
    if (!isLocked()) {
      m_DigitalInputs.removeElement(di);
    }
  }//removeDigitalIn

  public void setDigitalIn(int ref, DigitalIn di)
      throws IllegalAddressException {
    if (!isLocked()) {
      try {
        m_DigitalInputs.setElementAt(di, ref);
      } catch (IndexOutOfBoundsException ex) {
        throw new IllegalAddressException();
      }
    }
  }//setDigitalIn

  public DigitalIn getDigitalIn(int ref)
      throws IllegalAddressException {

    try {
      return (DigitalIn) m_DigitalInputs.elementAt(ref);
    } catch (IndexOutOfBoundsException ex) {
      throw new IllegalAddressException();
    }
  }//getDigitalIn

  public int getDigitalInCount() {
    return m_DigitalInputs.size();
  }//getDigitalInCount

  public DigitalIn[] getDigitalInRange(int ref, int count) {
    //ensure valid reference range
    if (ref < 0 || ref + count > m_DigitalInputs.size()) {
      throw new IllegalAddressException();
    } else {
      DigitalIn[] dins = new DigitalIn[count];
      for (int i = 0; i < dins.length; i++) {
        dins[i] = getDigitalIn(ref + i);
      }
      return dins;
    }
  }//getDigitalInRange

  public void addDigitalOut(DigitalOut _do) {
    if (!isLocked()) {
      m_DigitalOutputs.addElement(_do);
    }
  }//addDigitalOut
  
  public void addDigitalOut(DS2408_Output _do) {
	    if (!isLocked()) {
	      m_DigitalOutputs.addElement(_do.getCh0());
	      m_DigitalOutputs.addElement(_do.getCh1());
	      m_DigitalOutputs.addElement(_do.getCh2());
	      m_DigitalOutputs.addElement(_do.getCh3());
	      m_DigitalOutputs.addElement(_do.getCh4());
	      m_DigitalOutputs.addElement(_do.getCh5());
	      m_DigitalOutputs.addElement(_do.getCh6());
	      m_DigitalOutputs.addElement(_do.getCh7());
	      
	      m_Registers.addElement(_do.getCh0());
	      m_Registers.addElement(_do.getCh1());
	      m_Registers.addElement(_do.getCh2());
	      m_Registers.addElement(_do.getCh3());
	      m_Registers.addElement(_do.getCh4());
	      m_Registers.addElement(_do.getCh5());
	      m_Registers.addElement(_do.getCh6());
	      m_Registers.addElement(_do.getCh7());
	    }
	  }//addDigitalOut

  public void removeDigitalOut(DigitalOut _do) {
    if (!isLocked()) {
      m_DigitalOutputs.removeElement(_do);
    }
  }//removeDigitalOut

  public void setDigitalOut(int ref, DigitalOut _do)
      throws IllegalAddressException {
    if (!isLocked()) {
      try {
        m_DigitalOutputs.setElementAt(_do, ref);
      } catch (IndexOutOfBoundsException ex) {
        throw new IllegalAddressException();
      }
    }
  }//setDigitalOut

  public DigitalOut getDigitalOut(int ref)
      throws IllegalAddressException {
    try {
      return (DigitalOut) m_DigitalOutputs.elementAt(ref);
    } catch (IndexOutOfBoundsException ex) {
      throw new IllegalAddressException();
    }
  }//getDigitalOut

  public int getDigitalOutCount() {
    return m_DigitalOutputs.size();
  }//getDigitalOutCount

  public DigitalOut[] getDigitalOutRange(int ref, int count) {
    //ensure valid reference range
    if (ref < 0 || ref + count > m_DigitalOutputs.size()) {
      throw new IllegalAddressException();
    } else {
      DigitalOut[] douts = new DigitalOut[count];
      for (int i = 0; i < douts.length; i++) {
        douts[i] = getDigitalOut(ref + i);
      }
      return douts;
    }
  }//getDigitalOutRange

  public void addInputRegister(InputRegister reg) {
    if (!isLocked()) {
      m_InputRegisters.addElement(reg);
    }
  }//addInputRegister

  public void removeInputRegister(InputRegister reg) {
    if (!isLocked()) {
      m_InputRegisters.removeElement(reg);
    }
  }//removeInputRegister

  public void setInputRegister(int ref, InputRegister reg)
      throws IllegalAddressException {
    if (!isLocked()) {
      try {
        m_InputRegisters.setElementAt(reg, ref);
      } catch (IndexOutOfBoundsException ex) {
        throw new IllegalAddressException();
      }
    }
  }//setInputRegister

  public InputRegister getInputRegister(int ref)
      throws IllegalAddressException {

    try {
      return (InputRegister) m_InputRegisters.elementAt(ref);
    } catch (IndexOutOfBoundsException ex) {
      throw new IllegalAddressException();
    }
  }//getInputRegister

  public int getInputRegisterCount() {
    return m_InputRegisters.size();
  }//getInputRegisterCount

  public InputRegister[] getInputRegisterRange(int ref, int count) {
    //ensure valid reference range
    if (ref < 0 || ref + count > m_InputRegisters.size()) {
      throw new IllegalAddressException();
    } else {
      InputRegister[] iregs = new InputRegister[count];
      for (int i = 0; i < iregs.length; i++) {
        iregs[i] = getInputRegister(ref + i);
      }
      return iregs;
    }
  }//getInputRegisterRange

  public void addRegister(Register reg) {
    if (!isLocked()) {
      m_Registers.addElement(reg);
    }
  }//addRegister

  public void removeRegister(Register reg) {
    if (!isLocked()) {
      m_Registers.removeElement(reg);
    }
  }//removeRegister

  public void setRegister(int ref, Register reg)
      throws IllegalAddressException {
    if (!isLocked()) {
      try {
        m_Registers.setElementAt(reg, ref);
      } catch (IndexOutOfBoundsException ex) {
        throw new IllegalAddressException();
      }
    }
  }//setRegister

  public Register getRegister(int ref)
      throws IllegalAddressException {

    try {
      return (Register) m_Registers.elementAt(ref);
    } catch (IndexOutOfBoundsException ex) {
      throw new IllegalAddressException();
    }
  }//getRegister

  public int getRegisterCount() {
    return m_Registers.size();
  }//getRegisterCount

  public Register[] getRegisterRange(int ref, int count) {
    //ensure valid reference range
    if (ref < 0 || ref + count > m_Registers.size()) {
      throw new IllegalAddressException();
    } else {
      Register[] iregs = new Register[count];
      for (int i = 0; i < iregs.length; i++) {
        iregs[i] = getRegister(ref + i);
      }
      return iregs;
    }
  }//getRegisterRange

}//class SimpleProcessImage
