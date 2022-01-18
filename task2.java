package JavaPractice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;


class Task implements Runnable{
	
	Map<String, String> map = new HashMap<>();
	List<String> input;
	int index;
	int loop;
	
	Task(List<String> input){
		this.input = input;
	}
	
	public void str2Hex(List<String> s) {
		
		String currentString = "";
		
		int listSize = s.size();
		int numLoops = listSize/5 + 1;
		
		outerLoop: 
		for(int loop = numLoops; loop>0; loop--) {
			
			StringBuilder hexVal = new StringBuilder("");
			List<String> stringsArray = new ArrayList<>();
			for(int i=0; i<5; i++) {
			
				try{
					currentString = s.get(index);
				}
				catch(IndexOutOfBoundsException e){
					System.out.println("exception: "+e.getMessage());
					break outerLoop;
				}
				
				if(currentString == null) break outerLoop;
				
				stringsArray.add(currentString);
				index++;
				
				Stream.of(currentString)
				.forEach((ele) -> {				
					char[] arr = ele.toCharArray();
					char ch = arr[0];
					int num = (int)ch;
					hexVal.append(Integer.toHexString(num));
				});
			}
		
		map.put(stringsArray.toString(), hexVal.toString());
		}
//		System.out.println("map: " + map);
	}

	@Override
	public void run() {
		str2Hex(input);	
	}
}
	
	
class Initiate{
	
	public List<String> reader(String filepath){
		
		List<String> fileContents = new ArrayList<>();
		
		try{
//			System.out.println(Thread.currentThread().getName());
			BufferedReader br = new BufferedReader(new FileReader(filepath));
			
			String line;
			while((line = br.readLine())!= null) {
				line = line.replaceAll("\s", "");
				if(line.length() < 2) continue;
				fileContents.add(line);
			} 
			
			br.close();		
		} catch(IOException ioex){System.out.println("reader problem");}
		
		System.out.println("file read");
		return fileContents;
	}
	
	//write to file
	public void writer(Map<String, String> map, String outFilePath) {
		
		try(PrintWriter pw = new PrintWriter(outFilePath)) {			
			map.forEach((key, value) -> pw.println(key + ": " + value));
		} catch(IOException ioex) {System.out.println("writer problem");}
		
		System.out.println("file wrote");
	}
	
	public void awaitTerminationAfterShutdown(ExecutorService threadPool) {
	    threadPool.shutdown();
	    try {
	        if (!threadPool.awaitTermination(5, TimeUnit.SECONDS)) {
	        	System.out.println("timeout!");
	            threadPool.shutdownNow();
	        }
	    } catch (InterruptedException ex) {
	        threadPool.shutdownNow();
	        Thread.currentThread().interrupt();
	    }
	}
	
	public static void main(String[] args) {
		
		String read_from_path = "C:\\Users\\nikhil.kaushal\\eclipse-workspace\\learning-java\\src\\copy_question.txt";
		String write_to_path = "C:\\Users\\nikhil.kaushal\\eclipse-workspace\\learning-java\\src\\result.txt";
		
		Initiate initiator = new Initiate();
		List<String> text = initiator.reader(read_from_path);
		
		Task task = new Task(text);
//		System.out.println("main continued");

		//executor threads
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		System.out.println("Thread pool created");
		executorService.submit(task);
		System.out.println("Threads executing");
		initiator.awaitTerminationAfterShutdown(executorService);
		System.out.println("Threads Terminated");
		
		initiator.writer(task.map, write_to_path);
	}
}