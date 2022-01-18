package JavaPractice;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


class Trader{
	
	private final String name;
	private final String city;
	
	public Trader(String n, String c){
		this.name = n;
		this.city = c;
	}
	public String getName(){
		return this.name;
	}
	public String getCity(){
		return this.city;
	}
	public String toString(){
		return "Trader: " + this.name + " in " + this.city;
	}
}

class Transaction{
	
	private final Trader trader;
	private final int year;
	private final int value;
	
	public Transaction(Trader trader, int year, int value){
		this.trader = trader;
		this.year = year;
		this.value = value;
	}
	public Trader getTrader(){
		return this.trader;
	}
	public int getYear(){
		return this.year;
	}
	public int getValue(){
		return this.value;
	}
	public String toString(){
		return "{" + this.trader + ", " +
			"year: "+this.year+", " +
			"value:" + this.value +"}";
	}
}


// questions...........................................

//1 Find all transactions in the year 2011 and sort them by value (small to high).
//2 What are all the unique cities where the traders work?
//3 Find all traders from Cambridge and sort them by name.
//4 Return a string of all traders’ names sorted alphabetically.
//5 Are any traders based in Milan?
//6 Print all transactions’ values from the traders living in Cambridge.
//7 What’s the highest value of all the transactions?
//8 Find the transaction with the smallest value.


public class StreamExercise5 {
	public static void main(String[] args) {
		Trader raoul = new Trader("Raoul", "Cambridge");
		Trader mario = new Trader("Mario","Milan");
		Trader alan = new Trader("Alan","Cambridge");
		Trader brian = new Trader("Brian","Cambridge");
		
		List<Transaction> transactions = Arrays.asList(
			new Transaction(brian, 2011, 300),
			new Transaction(raoul, 2012, 1000),
			new Transaction(raoul, 2011, 400),
			new Transaction(mario, 2012, 710),
			new Transaction(mario, 2012, 700),
			new Transaction(alan, 2012, 950)
		);
		
		List<Trader> traders = Arrays.asList(raoul, mario, alan, brian);
		////////////////////////////////////////////
		List<Transaction> ans1 = transactions.stream()
											 .filter(t -> t.getYear()==2011)
											 .sorted(Comparator.comparing(Transaction::getValue).reversed())
//											 .sorted((t1, t2) -> Integer.valueOf(t1.getValue()).compareTo(t2.getValue()))
											 .collect(Collectors.toList());
		
		Set<String> ans2 = traders.stream()
								   .map(t -> t.getCity())
//								   .distinct()
								   .collect(Collectors.toSet());
		
		List<Trader> ans3 = traders.stream()
								   .filter(t -> t.getCity().equals("Cambridge"))
								   .sorted((t1, t2) -> t1.getName().compareToIgnoreCase(t2.getName()))
								   .collect(Collectors.toList());
		
		String ans4 = traders.stream()
								   .map(t -> t.getName())
								   .sorted((t1, t2) -> t1.compareToIgnoreCase(t2))
								   .collect(Collectors.joining(", "));
		
		boolean ans5 = transactions.stream()
								   .anyMatch(transaction -> transaction.getTrader().getCity().equalsIgnoreCase("Milan"));
		
		//ans6
//		transactions.stream()
//					.filter(transaction -> transaction.getTrader().getCity()
//													  .equalsIgnoreCase("cambridge"))
//					.map(Transaction::getValue)
//					.forEach(System.out::println);
		
		Integer ans7 = transactions.stream()
								   .map(Transaction::getValue)
								   .reduce(Integer::max).get();
		
		Transaction ans8 = transactions.stream()
									   .reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2)
									   .get();
		
		Transaction ans8_2 = transactions.stream()
		   		 						 .min(Comparator.comparing(Transaction::getValue))
		   		 						 .get();
		
		int smallestTransactionValue = transactions.stream()
										 .mapToInt(Transaction::getValue)
										 .min()
										 .getAsInt();
									   
		
		System.out.println(ans1);
		
//		Stream.iterate(new int[] {0, 1}, t -> new int[] {t[1], t[0]+t[1]})
//		.limit(10)
//		.map(t -> t[0])
//		.forEach(System.out::println);
//		
//		Map<Integer, List<Transaction>> map = transactions.stream()
//														  .collect(Collectors.groupingBy(Transaction::getYear));
//		System.out.println(map);
		
	}
}	
