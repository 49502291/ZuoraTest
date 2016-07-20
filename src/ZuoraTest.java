/**
Given a dataset that represents a user's navigation of a website, 
find the top N most frequently visited paths.


Author: Yichen Cai
Version: 0.0.1
Date: 2016-7-19
Language: Java
Editor: Eclipse
*/


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;



public class ZuoraTest {
	
	// Set page length, default is 3;
	private static int Pages_Length = 3;
	
	/**
	 * Read input string from file，here I suppose the input is a legal log 
	 * file, lines are separated by newline, and each line has two parts : user and path. 
	 * e.g: U1	/
	 * @param filename
	 * @return List of string
	 * @throws IOException, FileNotFoundException
	 */
	
	
	public static List<String> readFile(String filename) throws FileNotFoundException, IOException {
		
		
		List<String> res = new ArrayList<>();
		BufferedReader br = new BufferedReader(
			new InputStreamReader(new FileInputStream(filename), Charset.forName("UTF-8")));
		
		String line = null;
		while ((line = br.readLine()) != null) {			
			res.add(line);		
		}
		
		br.close();
		return res;
	}
	
	
	
	/**
	 * Find the top N most frequently traversed paths.
	 * 
	 * @param List of input string, read from log
	 * @param N, number of selection
	 * @return Top N most frequently visited paths.
	 */

	
	public static List<Map.Entry<String, Integer>> findTopNpaths(List<String> input, int N){
		
		
		if(input == null || input.size() == 0 || N <= 0)
			return null;
		
		List<Map.Entry<String, Integer>> res = new ArrayList<>();
		Map<String, LinkedList<String>> pathByUser = new HashMap<>();
		Map<String, Integer> pathCount  =  new HashMap<>();
		Map<String, String> userCurrPath = new HashMap<>();

		
		for(String line : input){
			if(line == null || line.isEmpty())
				continue;
			
			String [] log = line.split("\\s+");
			
			if(!pathByUser.containsKey(log[0])){
				
				LinkedList<String> q = new LinkedList<String>();
				q.add(log[1]);
				pathByUser.put(log[0], q);
				String currPath = log[1] + "->";
				userCurrPath.put(log[0], currPath);
				
			}else{
				
				LinkedList<String> q = pathByUser.get(log[0]);
				String currPath = userCurrPath.get(log[0]);
								
				q.add(log[1]);
				currPath += log[1] + "->";
				
				
				// Keep queue's size = Pages_Length,  if its size >=Pages_Length, poll first item, then put current Path to map for counting frequency. 
				if(q.size() >= Pages_Length){
					
					String first = q.poll();
					String path = currPath.substring(0, currPath.length() -2);
					userCurrPath.put(log[0], currPath.substring(first.length() + 2));

					if(pathCount.containsKey(path)){
						
						pathCount.put(path, pathCount.get(path) + 1);
					}else{
						
						pathCount.put(path, 1);
						
					}
										
				}else{
					
					userCurrPath.put(log[0], currPath);
				}
				

			}	
		}
		
		// Use a min heap to find top N most frequently traversed paths. 
		PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(N, new Comparator<Map.Entry<String, Integer>>(){
			
			public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2){
				
				return e1.getValue() - e2.getValue();
			}
			
		});
		
		for(Map.Entry<String, Integer> entry : pathCount.entrySet()){
			
			if(pq.size() < N){				
				pq.add(entry);
			}else{
				
				if(entry.getValue() > pq.peek().getValue()){
					
					pq.poll();
					pq.add(entry);
				}
				
			}
		}
		
		while(!pq.isEmpty()){
			
			res.add(0, pq.poll());
		}
		
		return res;
		
	}

	/**
	 * Print out top N most frequently visited paths.
	 * 
	 * @param List of entries which is key-value pair, key is path, value is frequency
	 */
	
	public static void print(List<Map.Entry<String, Integer>> list){
		
		if(list == null || list.size() == 0)
			return;
		
		for(Map.Entry<String, Integer> entry : list){			
			System.out.println(entry.getKey() + "   ;Frequency: " + entry.getValue());
		}
		
	}
	

}
