package JavaPractice;

class Nikhil implements Runnable{
	StringBuffer sb;
	
	Nikhil(String s){
		sb = new StringBuffer(s);
	}
	
	public void run() {
		for(int i=0; i<10; i++) {
			sb.append(Thread.currentThread().getName() + " ");
		}
	}
	
	public static void main(String[] args) {
		String s = "";
		Nikhil obj = new Nikhil(s);
		Thread t1 = new Thread(obj);
		Thread t2 = new Thread(obj);
		t1.setName("T1");
		t2.setName("T2");
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch(InterruptedException e) {}
		
		System.out.println(obj.sb);
	}
}