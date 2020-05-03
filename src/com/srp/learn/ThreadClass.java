package com.srp.learn;



class Writer{
	//synchronized 
	 public void runLoop(int k) {
		for(int i=0;i<k;i++) {
			try{Thread.sleep(1000);}
			catch(Exception e) {}
			System.out.println(i);
		}
	}
}

class thread1 implements Runnable{
	
	Writer w1;
	
	public thread1(Writer a) {
		this.w1=a;
		
	}
	
	@Override
	public void run() {
		synchronized(w1) {
		w1.runLoop(10);
	}
}
}


class thread2 implements Runnable{
	Writer w2;
	public thread2(Writer w) {
		this.w2=w;
	}
	
	public void run() {
		synchronized(w2){
			w2.runLoop(10);
	}
}
}
public class ThreadClass{
	public static void main(String[] args) {
		System.out.println("Started");
		Writer w=new Writer();
		Thread tr1=new Thread(new thread1(w));
		tr1.start();
		//try{tr1.join();}catch(Exception e) {}
		Thread tr2=new Thread(new thread2(w));
		tr2.start();
		
		
		
		//w.runLoop(10);
		System.out.println("Ended");
	}
	
}