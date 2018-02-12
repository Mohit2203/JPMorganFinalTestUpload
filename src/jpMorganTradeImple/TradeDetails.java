package jpMorganTradeImple;

import java.time.LocalDate;

public class TradeDetails {
	private Double tradeAmount;
	private LocalDate finalSettlementDate;
	private String entity;
	private int units;
	private String currency;
	private String buySellFlag;

	public TradeDetails(Double tradeAmount, LocalDate finalSettlementDate, String entity, int units,
			String currency, String buySellFlag) {
		super();
		this.tradeAmount = tradeAmount;
		this.finalSettlementDate = finalSettlementDate;
		this.entity = entity;
		this.units = units;
		this.currency = currency;
		this.buySellFlag = buySellFlag;
	}

	public String getBuySellFlag() {
		return buySellFlag;
	}

	public Double getTradeAmount() {
		return tradeAmount;
	}

	public LocalDate getFinalSettlementDate() {
		return finalSettlementDate;
	}

	public String getEntity() {
		return entity;
	}

	public int getUnits() {
		return units;
	}

	public String getCurrency() {
		return currency;
	}

	public String toString() {
	      return ("Entity Name = " + this.getEntity() 
	                + " USD Amount = $" + this.getTradeAmount() + "\n");
	    }
	  
}
