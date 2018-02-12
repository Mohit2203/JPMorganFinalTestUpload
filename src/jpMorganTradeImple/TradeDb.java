package jpMorganTradeImple;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class TradeDb {

	/**
	 * This Method Prints The Incoming Settlement report
	 */

	public void getIncomingReport(List<TradeDetails> tradeDetail) {
		StringBuilder stringBuilder = null;
		Iterator<Entry<LocalDate, Double>> iter = null;
		Map<LocalDate, Double> sumIncomingByDate = settlementIncomingReport(tradeDetail);
		stringBuilder = new StringBuilder();
		for (TradeDetails details : tradeDetail) {
			if (details.getBuySellFlag().equalsIgnoreCase("B")) {
				System.out.println("\n******* REPORT :: INCOMING USD SETTLEMENT PER DAY ********");
				iter = sumIncomingByDate.entrySet().iterator();
				while (iter.hasNext()) {
					Entry<LocalDate, Double> entry = iter.next();
					stringBuilder.append(entry.getKey());
					stringBuilder.append(" = ").append('$');
					stringBuilder.append(entry.getValue());
					if (iter.hasNext()) {
						stringBuilder.append('\n');
					}
				}
				System.out.println(stringBuilder.toString());
				printIncomingRanking(tradeDetail);
			}
		}
	}

	/**
	 * This Method Prints The Outgoing Settlement report
	 */
	public void getOugoingReport(List<TradeDetails> tradeDetail) {
		Map<LocalDate, Double> sumOutgoingByDate = settlementOutgoingReport(tradeDetail);
		StringBuilder stringBuilder = new StringBuilder();
		Iterator<Entry<LocalDate, Double>> iter = null;
		for (TradeDetails details : tradeDetail) {
			if (details.getBuySellFlag().equalsIgnoreCase("S")) {
				System.out.println("\n******* REPORT :: OUTGOING USD SETTLEMENT PER DAY *********");
				iter = sumOutgoingByDate.entrySet().iterator();
				while (iter.hasNext()) {
					Entry<LocalDate, Double> entry = iter.next();
					stringBuilder.append(entry.getKey());
					stringBuilder.append(" = ").append('$');
					stringBuilder.append(entry.getValue());
					if (iter.hasNext()) {
						stringBuilder.append('\n');
					}
				}
				System.out.println(stringBuilder.toString());
				printOutgoingRanking(tradeDetail);
			}
		}

	}

	/**
	 * This Method Generates The Incoming Settlement report
	 */
	public Map<LocalDate, Double> settlementIncomingReport(List<TradeDetails> tradeDetail) {
		Map<LocalDate, Double> settleInReport = tradeDetail.stream().collect(Collectors.groupingBy(
				TradeDetails::getFinalSettlementDate, Collectors.summingDouble(TradeDetails::getTradeAmount)));
		return settleInReport;
	}

	/**
	 * This Method Generates The Outgoing Settlement report
	 */
	public Map<LocalDate, Double> settlementOutgoingReport(List<TradeDetails> tradeDetail) {
		Map<LocalDate, Double> settleOutReport = tradeDetail.stream().collect(Collectors.groupingBy(
				TradeDetails::getFinalSettlementDate, Collectors.summingDouble(TradeDetails::getTradeAmount)));
		return settleOutReport;
	}

	/**
	 * This Method Print The Ranking Based on the Incoming Settlement
	 */
	private void printIncomingRanking(List<TradeDetails> tradeDetail) {
		System.out.println("\n*** REPORT :: RANKING OF ENTITIES BASED ON INCOMING SETTLEMENT ***");
		Map<String, Double> getEntityAndAmount = tradeDetail.stream().collect(
				Collectors.groupingBy(TradeDetails::getEntity, Collectors.summingDouble(TradeDetails::getTradeAmount)));
		List<TradeDetails> sortedIncomingEntity = tradeDetail.stream()
				.sorted(Comparator.comparing((TradeDetails x) -> getEntityAndAmount.get(x.getEntity())).reversed())
				.collect(Collectors.toList());
		int rank = 0;
		for (TradeDetails i : sortedIncomingEntity) {
			System.out.print(++rank + ") ");
			System.out.println(i);
		}

	}

	/**
	 * This Method Print The Ranking Based on the Incoming Settlement
	 */
	private void printOutgoingRanking(List<TradeDetails> tradeDetail) {
		System.out.println("\n*** REPORT :: RANKING OF ENTITIES BASED ON OUTGOING SETTLEMENT ***");
		Map<String, Double> getEntityAndAmount = tradeDetail.stream().collect(
				Collectors.groupingBy(TradeDetails::getEntity, Collectors.summingDouble(TradeDetails::getTradeAmount)));
		List<TradeDetails> sortedOutgoingEntity = tradeDetail.stream()
				.sorted(Comparator.comparing((TradeDetails x) -> getEntityAndAmount.get(x.getEntity())).reversed())
				.collect(Collectors.toList());
		int rank = 0;
		for (TradeDetails i : sortedOutgoingEntity) {
			System.out.print(++rank + ") ");
			System.out.println(i);
		}
	}

	private final static List<TradeDetails> tradeDetailsList = new LinkedList<>();
	private final static Map<LocalDate, Map<String, List<TradeDetails>>> buyDeals = new HashMap<>();
	private final static Map<LocalDate, Map<String, List<TradeDetails>>> sellDeals = new HashMap<>();

	/**
	 * This Method Push The Final Buy and Settle Trade Deals Based On The Buy And
	 * Sell Indicator
	 */
	public void push(final TradeDetails tradeDetails) {
		tradeDetailsList.add(tradeDetails);

		if (tradeDetails.getBuySellFlag().equals("B")) {
			if (buyDeals.containsKey(tradeDetails.getFinalSettlementDate())) {
				Map<String, List<TradeDetails>> tmpMap = buyDeals.get(tradeDetails.getFinalSettlementDate());
				if (tmpMap.containsKey(tradeDetails.getEntity())) {
					List<TradeDetails> list = tmpMap.get(tradeDetails.getEntity());
					list.add(tradeDetails);
				} else {
					List<TradeDetails> tmpList = new ArrayList<>();
					tmpList.add(tradeDetails);
					tmpMap.put(tradeDetails.getEntity(), tmpList);
				}
			} else {
				Map<String, List<TradeDetails>> tmpMap = new HashMap<>();
				List<TradeDetails> tmpList = new ArrayList<>();
				tmpList.add(tradeDetails);
				tmpMap.put(tradeDetails.getEntity(), tmpList);
				buyDeals.put(tradeDetails.getFinalSettlementDate(), tmpMap);
			}

		} else if (tradeDetails.getBuySellFlag().equals("S")) {
			if (sellDeals.containsKey(tradeDetails.getFinalSettlementDate())) {
				Map<String, List<TradeDetails>> tmpMap = sellDeals.get(tradeDetails.getFinalSettlementDate());
				if (tmpMap.containsKey(tradeDetails.getEntity())) {
					List<TradeDetails> list = tmpMap.get(tradeDetails.getEntity());
					list.add(tradeDetails);
				} else {
					List<TradeDetails> tmpList = new ArrayList<>();
					tmpList.add(tradeDetails);
					tmpMap.put(tradeDetails.getEntity(), tmpList);
				}
			} else {
				Map<String, List<TradeDetails>> tmpMap = new HashMap<>();
				List<TradeDetails> tmpList = new ArrayList<>();
				tmpList.add(tradeDetails);
				tmpMap.put(tradeDetails.getEntity(), tmpList);
				sellDeals.put(tradeDetails.getFinalSettlementDate(), tmpMap);
			}

		}
	}

}
