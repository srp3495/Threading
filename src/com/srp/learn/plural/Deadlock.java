package com.srp.learn.plural;

public class Deadlock {

	
	private Object key1=new Object();
	private Object key2=new Object();

	
	public void a() {
		synchronized (key1) {
			System.out.println("i am in function a "+Thread.currentThread().getName());
			b();
		}
	}
	
	public void b() {
		synchronized(key2) {			
			System.out.println("i am in function b "+Thread.currentThread().getName());
			c();
		}
	}
	
	public void c() {
		synchronized (key1) {
			System.out.println("i am in function c "+Thread.currentThread().getName());
			
		}
	}
}
