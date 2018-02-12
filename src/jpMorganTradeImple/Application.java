package jpMorganTradeImple;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Application {

	static CalculateTrade calculateTrade;

	public static void main(String[] args) throws Exception {

		calculateTrade = CalculateTrade.getInstance();
		TradeDetails details = null;
		List<Instruction> instr = new ArrayList<Instruction>();
		List<Instruction> instr2 = new ArrayList<Instruction>();
		List<TradeDetails> details2 = new ArrayList<>();
		instr.add(
				new Instruction("FOO", 200, "INR", LocalDate.parse("2018-01-01"), LocalDate.parse("2018-01-02"), "B"));
		instr.add(new Instruction("APLLE", 100, "INR", LocalDate.parse("2018-01-09"), LocalDate.parse("2018-01-10"),
				"B"));
		instr.add(
				new Instruction("MAC", 130, "EUR", LocalDate.parse("2018-01-01"), LocalDate.parse("2018-01-02"), "B"));
		instr.add(new Instruction("HADOOP", 120, "SUD", LocalDate.parse("2018-01-23"), LocalDate.parse("2018-01-24"),
				"B"));
		instr.add(
				new Instruction("ROSE", 80, "CSD", LocalDate.parse("2018-01-25"), LocalDate.parse("2018-01-26"), "B"));
		instr.add(new Instruction("YAHOO", 200, "INR", LocalDate.parse("2018-01-26"), LocalDate.parse("2018-01-27"),
				"S"));
		instr.add(new Instruction("BAR", 70, "OLA", LocalDate.parse("2018-01-30"), LocalDate.parse("2018-01-31"), "S"));
		instr.add(
				new Instruction("FORD", 50, "EUR", LocalDate.parse("2018-01-30"), LocalDate.parse("2018-01-30"), "S"));
		instr.add(
				new Instruction("MERC", 300, "AED", LocalDate.parse("2018-01-15"), LocalDate.parse("2018-01-16"), "S"));
		instr.add(
				new Instruction("IAL", 200, "SAR", LocalDate.parse("2018-01-21"), LocalDate.parse("2018-01-22"), "B"));

		instr2.addAll(instr);

		for (Instruction instrValus : instr2) {
			details = calculateTrade.doTrade(instrValus);
			details2.add(details);
			calculateTrade.getTradeDb().getIncomingReport(details2);

		}
		for (Instruction instrValus : instr) {
			details = calculateTrade.doTrade(instrValus);
			details2.add(details);
			calculateTrade.getTradeDb().getOugoingReport(details2);

		}

	}
}
