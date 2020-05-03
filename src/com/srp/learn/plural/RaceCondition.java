package com.srp.learn.plural;

public class RaceCondition {

	
	public static void main(String[] args) throws InterruptedException {
		LongWrapper longwrapper=new LongWrapper(0L);
		Runnable runnable=()->{
			for(int i=0;i<1000;i++) {
				longwrapper.increamentL();
			}
		};
		
		Thread[] threads=new Thread[1000];
		for(int i=0;i<1000;i++) {
			threads[i]=new Thread(runnable);
			threads[i].start();
		}
		
		
		for(int j=0;j<1000;j++) {
			threads[j].join();
		}
		//Thread t=new Thread(runnable);//commented to execute 1000 threads
		//t.start();
		//t.join(); //this will wait for above code to finish before going bwlow this line
		System.out.println(longwrapper.getL());
	
	}
}
