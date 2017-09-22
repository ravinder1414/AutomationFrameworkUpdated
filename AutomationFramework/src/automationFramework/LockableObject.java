package automationFramework;

public class LockableObject {

	private static final Object obj = new Object();
	
	public static final Object getLockableObject()
	{
		return obj;
	}
	
}
