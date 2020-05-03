package com.srp.learn.plural;

public class ProducerConsumer {

	private static int count;
	private static int[] buffer;
	
	
	private static Object key=new Object();
	
	static class Producer {
		
		void produce() {
		synchronized(key) {
		
			if(isFull(buffer)) {
			try {
				key.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		else {
			buffer[count++]=1;
			key.notify();
		}
			
		}
	
	}
	}
	
	static class Consumer{
		
		void consume() {
			synchronized (key) {
				if(isEmpty(buffer)) {
					try {
						key.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				else {
					buffer[--count]=0;
					key.notify();
				}
				
			}
		}
		
	}
	
	
	
	static boolean isEmpty(int[] buffer) {
		return count==0;
	}
	static boolean isFull(int[] buffer) {
		return count==buffer.length;
	}
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		
		buffer=new int[10];
		count=0;
		Producer p=new Producer();
		Consumer c=new Consumer();
		
		Runnable r1=()->{for(int i=0;i<40;i++) {
			c.consume();}
		};
		Runnable r2=()->{for(int j=0;j<45;j++) {p.produce();}};
		
		
		Thread t1=new Thread(r1);
		Thread t2=new Thread(r2);
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		
		System.out.println("product remaining:"+count);
		
		
		
	}

}
