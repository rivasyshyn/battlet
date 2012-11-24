package test;

public class bb {

	private cc mCallback;
	
	public void setCallback(cc callback){
		mCallback = callback;
	}
	
	public void call(int number){
		mCallback.onCall(number);
	}
	
}
