package javahrms.hrms.core.utilites.results;

public class SuccessDataResult<T> extends DataResult<T> {

	public SuccessDataResult(String message, T data) {
		super(data, true, message);	
	}

	public SuccessDataResult(T data) {
		super(data,true);
		
	}
	
	public SuccessDataResult(String message) {
		super(null,true,message);
	}
	
	public SuccessDataResult() {
		super(null,true);
	}
}
