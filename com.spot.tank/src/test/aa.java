package test;

public class aa {

	private cc mCallback;

	public void setCallback(cc callback) {
		mCallback = callback;
	}

	public cc getCallback() {
		return mCallback;
	}
	
	public void call(int number){
		mCallback.onCall(number);
	}

}
