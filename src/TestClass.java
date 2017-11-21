import java.math.BigDecimal;
import java.util.Calendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

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
		
		System.out.println(BigDecimal.valueOf(2.1).subtract(BigDecimal.valueOf(1.2)));
		
		Integer n1 = 200;
		Integer n2 = 200;

		System.out.println(200 == n1);

		Calendar currentMonth = Calendar.getInstance();
		currentMonth.set(Calendar.DAY_OF_MONTH, 1);
		currentMonth.set(Calendar.HOUR_OF_DAY, 0);
		currentMonth.set(Calendar.MINUTE, 0);
		currentMonth.set(Calendar.SECOND, 0);
		currentMonth.set(Calendar.MILLISECOND, 0);

		XMLGregorianCalendar currentMonth2;
		try {
			currentMonth2 = DatatypeFactory.newInstance().newXMLGregorianCalendar("2017-06-01+08:00");
			System.out.println(currentMonth.getTimeInMillis());
			System.out.println(currentMonth2.toGregorianCalendar().getTimeInMillis());

			System.out.println(currentMonth2.toGregorianCalendar().compareTo(currentMonth) == -1);

		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}


}

