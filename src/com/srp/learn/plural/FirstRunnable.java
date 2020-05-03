package com.srp.learn.plural;

public class FirstRunnable {

	
	public static void main(String[] args) {
		Runnable runnable=()->{System.out.println("Running in "+Thread.currentThread().getName());};
		//here lambda expression is used as we have only one method
		//currentThread() is a static method of thread class
	   Thread t=new Thread(runnable);
	   t.setName("TestThread");
	   t.start();
	   t.run(); //will use main thread
	}
	
}
