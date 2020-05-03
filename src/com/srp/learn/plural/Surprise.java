package com.srp.learn.plural;

import java.util.Timer;
import java.util.TimerTask;

public class Surprise extends Thread{
	
	private static Object lock=new Object();
	
	private boolean quit_time=false;
	
	public void run() {
		while(!quit_time) {
			working();
			System.out.println("still working.....");
		}
		System.out.println("have a cup of tea");
	}
	
	public void working() {
		try {
			Thread.sleep(300);
		}
		catch(InterruptedException e) {e.printStackTrace();}
	}

	
	synchronized void quit() throws InterruptedException {
		synchronized (lock) {
			quit_time=true;
			join();
		}
		
	}
	
	synchronized void keepWorking() {
		synchronized (lock) {
			quit_time=false;
		}
		
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		final Surprise surprise=new Surprise();
		surprise.start();
		
		Timer t=new Timer(true);//daemon thread
		
        t.schedule(new TimerTask(){
			
			@Override
			public void run() {
			surprise.keepWorking();
			}
		}, 500);
        
        Thread.sleep(400);
        surprise.quit();

	}

}
