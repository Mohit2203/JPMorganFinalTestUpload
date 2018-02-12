/**
 * 
 */
package jpMorganTradeImple;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;


/**
 * @author mohit
 * 
 */
public class CalculateTrade {

	static final CalculateTrade calculateTrade;
	static {
		calculateTrade = new CalculateTrade();
	}

	private CalculateTrade() {

	}

	public static CalculateTrade getInstance() {
		return calculateTrade;
	}

	private static final TradeDb tradeDb = new TradeDb();

	public static final TradeDb getTradeDb() {
		return tradeDb;
	}

	/**
	 * doTrade is exposed to get the instruction and return TradeDetails to caller.
	 * This method make use AgreedFx, Instruction and business logic to check if
	 * trade is possible if Yes it calculates the deal amount, settlement date and
	 * return the final deal. It adds the trade to tradeDB for reporting purpose
	 * 
	 * @param instruction
	 * @return
	 */
	public TradeDetails doTrade(final Instruction instruction) throws Exception {
		BigDecimal units = new BigDecimal(instruction.getUnits());
		BigDecimal tradeRate = TradePrice.getTradeRate(instruction.getCurrency());
		BigDecimal fxCharges = TradePrice.getFxConversionCharges(instruction.getCurrency());
		Double dealAmount = tradeRate.multiply(fxCharges).multiply(units).doubleValue();

		LocalDate finalSettlementDate = getSettleMentDate(instruction);
		TradeDetails tradeDetails = new TradeDetails(dealAmount, finalSettlementDate, instruction.getEntity(),
				instruction.getUnits(), instruction.getCurrency(), instruction.getBuySellFlag());

		tradeDb.push(tradeDetails);
		return tradeDetails;
	}

	private LocalDate getSettleMentDate(Instruction instruction) {

		LocalDate settlementDate = instruction.getSettlementDate();
		DayOfWeek dayOfWeek = settlementDate.getDayOfWeek();

		String currency = instruction.getCurrency();
		if (currency == null || currency.isEmpty())
			throw new RuntimeException("Invalid arguments");

		if (currency.equals("AED") || currency.equals("SAR")) {
			if (dayOfWeek.equals(DayOfWeek.FRIDAY))
				settlementDate.plusDays(2);
			else if (dayOfWeek.equals(DayOfWeek.SATURDAY))
				settlementDate.plusDays(1);
		} else {
			if (dayOfWeek.equals(DayOfWeek.SATURDAY))
				settlementDate.plusDays(2);
			else if (dayOfWeek.equals(DayOfWeek.SUNDAY))
				settlementDate.plusDays(1);
		}
		return settlementDate;
	}
}
