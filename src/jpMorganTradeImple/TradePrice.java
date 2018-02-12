/**
 * 
 */
package jpMorganTradeImple;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author mohit
 *
 */
public class TradePrice {

	private static Map<String, BigDecimal> fxConversionCharges = new HashMap<String, BigDecimal>();

	static {
		fxConversionCharges.put("SGP", new  BigDecimal(0.50)); 
		fxConversionCharges.put("AED", new  BigDecimal(0.22));
		fxConversionCharges.put("SAR", new  BigDecimal(0.75));
		fxConversionCharges.put("INR", new  BigDecimal(0.50));
		fxConversionCharges.put("EUR", new  BigDecimal(0.23));
		fxConversionCharges.put("SUD", new  BigDecimal(0.77));
		fxConversionCharges.put("OLA", new  BigDecimal(0.58));
		fxConversionCharges.put("CSD", new  BigDecimal(0.66));
		fxConversionCharges.put("HAX", new  BigDecimal(0.97));
	}

	/**
	 * Returns fxCharges for the passed currency
	 * @param currency
	 * @return fxCharges for the passed currency
	 */
	public static BigDecimal getFxConversionCharges(String currency) {

		if (currency != null && !currency.isEmpty() && fxConversionCharges.containsKey(currency)
				&& fxConversionCharges.get(currency) != null)
			return fxConversionCharges.get(currency);
		else
			throw new RuntimeException("Currency not supported");
	}
	
	
	
	
	
	private static Map<String, BigDecimal> tradeRate = new HashMap<String, BigDecimal>();

	static {
		tradeRate.put("SGP", new  BigDecimal(100.25)); 
		tradeRate.put("AED", new  BigDecimal(150.5));
		tradeRate.put("SAR", new  BigDecimal(50));
		tradeRate.put("INR", new  BigDecimal(65.5));
		tradeRate.put("EUR", new  BigDecimal(100.25)); 
		tradeRate.put("SUD", new  BigDecimal(150.5));
		tradeRate.put("OLA", new  BigDecimal(77.90));
		tradeRate.put("CSD", new  BigDecimal(89.5));
		tradeRate.put("HAX", new  BigDecimal(78.5));
	}
	
	/**
	 * Returns current rate of USD for the passed currency
	 * @param currency
	 * @return  current rate of USD for the passed currency
	 */
	public static BigDecimal getTradeRate(String currency) {

		if (currency != null && !currency.isEmpty() && tradeRate.containsKey(currency)
				&& tradeRate.get(currency) != null)
			return tradeRate.get(currency);
		else
			throw new RuntimeException("Currency not supported");
	}


}
