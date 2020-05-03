package com.srp.learn.plural;

public class FalseSharing {

	public static int MAX_THREADS=4;
	public final static long ITERATIONS=5000000L;
	
	private static VolatileLongPadded[] paddedLongs;
	private static VolatileLongUnpadded[] unpaddedLongs;
	
	public final static class VolatileLongPadded{
		public long q1,q2,q3,q4,q5,q6;
		public volatile long value=0L;
		public long q11,q12,q13,q14,q16,q17;
	}
	
	public final static class VolatileLongUnpadded{
		public volatile long value=0L;

	}
	
	static {
		paddedLongs=new VolatileLongPadded[MAX_THREADS];
		for(int i=0;i<paddedLongs.length;i++) {
			paddedLongs[i]=new VolatileLongPadded();
		}
		
		unpaddedLongs=new VolatileLongUnpadded[MAX_THREADS];
		for(int j=0;j<unpaddedLongs.length;j++) {
			unpaddedLongs[j]=new VolatileLongUnpadded();
		}
	}
	
	public static void main(String []args) throws InterruptedException {
		runBenchMark();
	}
	
	public static void runBenchMark() throws InterruptedException {
		
		long begin,end;
		for(int n=0;n<MAX_THREADS;n++) {
			Thread[] threads=new Thread[n];
			
			for (int k=0;k<threads.length;k++) {
				threads[k]=new Thread(createPaddedRunnable(k));
			}
			begin=System.currentTimeMillis();
			for(Thread t:threads) {t.start();}
			for(Thread t:threads) {t.join();}
			end=System.currentTimeMillis();
			System.out.println("padded"+n+(end-begin));
		
		
			
			for (int q=0;q<threads.length;q++) {
				threads[q]=new Thread(createUnPaddedRunnable(q));
			}
			begin=System.currentTimeMillis();
			for(Thread t:threads) {t.start();}
			for(Thread t:threads) {t.join();}
			end=System.currentTimeMillis();
			System.out.println("unpadded"+n+(end-begin));
			
		}
		
	}
	
	private static Runnable createUnPaddedRunnable(final int k) {
		return ()->{long i=ITERATIONS+1;
		while(0!=--i) {
			unpaddedLongs[k].value=i;
		}
		};
	}
	
	private static Runnable createPaddedRunnable(final int k) {
		return ()->{long i=ITERATIONS+1;
		while(0!=--i) {
			paddedLongs[k].value=i;
		}
		};
	}
	
	
	
}
