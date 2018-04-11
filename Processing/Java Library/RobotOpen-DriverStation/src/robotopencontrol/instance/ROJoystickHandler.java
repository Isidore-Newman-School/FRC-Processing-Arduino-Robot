package robotopencontrol.instance;

import java.util.ArrayList;
import java.util.Observable;

import net.java.games.input.*;

/**
 * @author Eric Barch
 */
public class ROJoystickHandler extends Observable {
    // Stores all of the controllers currently connected
    private Controller[] controllers;
    // Stores all active joysticks
    ArrayList<JInputController> activeControllers;
   
    // Our constructor for the ROJoystickHandler object
    public ROJoystickHandler() {
        controllers = null;
        activeControllers = new ArrayList<JInputController>();
    }
    
    // Loop through controller array
    public String[] getControllers() {
    	controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
        
        // An Array to store all of the controllers to be returned
        String[] controllersFound = new String[controllers.length+1];
        
        controllersFound[0] = "Disabled";
        
        // Return an ArrayList with the names of all of the connected devices
        for (int j=0;j<controllers.length;j++) {
            controllersFound[j+1] = controllers[j].getName();
        }
        
        return controllersFound;
    }
    
    // Set a controller as included in control packets
    public void activateController(int controllerIndex) {
        try {
        	if (!activeControllers.contains(controllers[controllerIndex]))
        		activeControllers.add(new JInputController(controllers[controllerIndex]));
        }
        catch (Exception e) {
        	System.exit(0);
        }
    }
    
    public void clearControllers() {
        // Reset the ArrayList
        activeControllers = new ArrayList<JInputController>();
    }
    
    public byte[] exportValues() {
    	try {
	        byte[] exportValues = new byte[getExportSize()];
	        int currentIndex = 0;
	        
	        for (int i = 0; i < activeControllers.size(); i++) {
	        	JInputController indexedController = activeControllers.get(i);
	            
	            boolean success = indexedController.poll();
	            
	            // The joystick is no longer available - reset everything
	            if (!success) {
	            	System.exit(0);
	            }
	           
	        	exportValues[currentIndex++] = (byte)indexedController.getAxisValue(1);
                        exportValues[currentIndex++] = (byte)indexedController.getAxisValue(2);
                        exportValues[currentIndex++] = (byte)indexedController.getAxisValue(4);
                        exportValues[currentIndex++] = (byte)indexedController.getAxisValue(5);
                        exportValues[currentIndex++] = (byte)indexedController.getButton(0);
                        exportValues[currentIndex++] = (byte)indexedController.getButton(1);
                        exportValues[currentIndex++] = (byte)indexedController.getButton(2);
                        exportValues[currentIndex++] = (byte)indexedController.getButton(3);
                        exportValues[currentIndex++] = (byte)indexedController.getButton(4);
                        exportValues[currentIndex++] = (byte)indexedController.getButton(5);
                        exportValues[currentIndex++] = (byte)0;
                        exportValues[currentIndex++] = (byte)0;
                        exportValues[currentIndex++] = (byte)indexedController.getPov();
                        exportValues[currentIndex++] = (byte)indexedController.getButton(9);
                        exportValues[currentIndex++] = (byte)indexedController.getButton(10);
                        exportValues[currentIndex++] = (byte)indexedController.getButton(11);
                        exportValues[currentIndex++] = (byte)indexedController.getButton(12);
                        exportValues[currentIndex++] = (byte)indexedController.getButton(13);
                        exportValues[currentIndex++] = (byte)indexedController.getButton(14);
                        exportValues[currentIndex++] = (byte)indexedController.getButton(15);
                        exportValues[currentIndex++] = (byte)indexedController.getButton(16);
                        exportValues[currentIndex++] = (byte)indexedController.getButton(17);
                        exportValues[currentIndex++] = (byte)indexedController.getButton(18);
                        exportValues[currentIndex++] = (byte)indexedController.getButton(19);
	        }
	        
	        return exportValues;
    	}
    	catch (Exception e) {
    		System.exit(0);
    		return null;
    	}
    }
    
    private int getExportSize() {
        try {
	    	int sizeReturn = 0;
	       
	        for (int i = 0; i < activeControllers.size(); i++) {
	        	// The fixed component siez of 17, +2 is the length byte and bundle ID before each bundle
	            sizeReturn += 24;
	        }
	        
	        return sizeReturn;
        }
        catch (Exception e) {
        	System.exit(0);
        	return 0;
        }
    }
    
    
    
    public boolean controllersActive() {
    	try {
	    	if (activeControllers.size() > 0)
	    		return true;
	    	else
	    		return false;
    	}
    	catch (Exception e) {
    		System.exit(0);
    		return false;
    	}
    }
}