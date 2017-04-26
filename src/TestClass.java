
public class TestClass {

	public static void main(String[] args) {
		String testString1 = "1.232-";
		String[] results = testString1.split("-");
		
		if(results.length == 1 && testString1.contains("-")){
			try{
				System.out.println(-Double.parseDouble(results[0]));
			}catch(NumberFormatException e){
				System.out.println("Not a number");
			}
			
		}else{
			System.out.println("not this value");
		}
		
		
	}

}

