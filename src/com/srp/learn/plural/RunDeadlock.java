package com.srp.learn.plural;

public class RunDeadlock {

	public static void main(String[] args) throws InterruptedException{
		// TODO Auto-generated method stub

		Deadlock dl=new Deadlock();
		Runnable r1=()->{dl.a();};
		Runnable r2=()->{dl.b();};
		
		
		
		Thread t1=new Thread(r1);
		t1.start();
		
		Thread t2=new Thread(r2);
		t2.start();
		
		t1.join();
		t2.join();
	}

}
