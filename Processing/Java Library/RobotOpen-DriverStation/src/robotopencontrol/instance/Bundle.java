package robotopencontrol.instance;

public class Bundle {
	private char type;
	private String bundleName;
	private float value;
	
	
	public Bundle(char t, String bN, float val) {
		type = t;
		bundleName = bN;
		value = val;
	}
	
	@Override
	public String toString() {
		return type + " " + bundleName + " " + String.valueOf(value);
	}
	
	public float getValue() {
		return value;
	}
	
//	public Bundle(char t, String bN, char vChr) {
//		this(t, bN);
//		valChr = vChr;
//	}
//	
//	public Bundle(char t, String bN, int vInt) {
//		this(t, bN);
//		valInt = vInt;
//	}
//	
//	public Bundle(char t, String bN, float vFl) {
//		this(t, bN);
//		valFl = vFl;
//	}
//	
//	public Bundle(char t, String bN, boolean vBool) {
//		this(t, bN);
//		valBool = vBool;
//	}
}