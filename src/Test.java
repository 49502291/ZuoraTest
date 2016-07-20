import java.io.IOException;
import java.util.List;
import java.util.Map;


public class Test {
	
	/*
	 *  Here is a test file, first read logs from file.
	 *  then call function "findTopNpaths" with parameter input logs and N, which is number of selection
	 *  finally, print out result, check if it is correct. 
	 *  Here I tried several test cases:
	 *  test1 is normal case;
	 *  test2 is a empty file;
	 *  test3 has no available paths, because all traversed paths are not long enough.
	 *  test4 is more complex test cases.
	 * 
	*/
	
	
	public static void main(String[] args) throws IOException {
		
		List<String> input = ZuoraTest.readFile("test1");
		List<Map.Entry<String, Integer>> res = ZuoraTest.findTopNpaths(input, 5);
		ZuoraTest.print(res);
		
	}

}
