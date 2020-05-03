package com.srp.learn.plural;

public class LongWrapper {

	private Object key=new Object(); //explicit lock object
	private Long l=0L;
	
	

	public Long getL() {
		return l;
	}

	public LongWrapper(Long l) {
		this.l = l;
	}
	
	public void increamentL() {
		synchronized (key) {
			
		l=l+1;
	}
	}
	
}
