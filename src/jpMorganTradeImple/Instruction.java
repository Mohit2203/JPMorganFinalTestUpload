/**
 * 
 */
package jpMorganTradeImple;

import java.time.LocalDate;

/**
 * @author mohit
 * 
 */
public class Instruction {

	private String entity;
	private int units;
	private String currency;
	private LocalDate instructionDate;
	private LocalDate settlementDate;
	private String buySellFlag;

	public Instruction(String entity, int units, String currency, LocalDate instructionDate, LocalDate settlementDate,
			String buySellFlag) {
		this.entity = entity;
		this.units = units;
		this.currency = currency;
		this.instructionDate = instructionDate;
		this.settlementDate = settlementDate;
		this.buySellFlag = buySellFlag;
	}

	public String getBuySellFlag() {
		return buySellFlag;
	}

	public void setBuySellFlag(String buySellFlag) {
		this.buySellFlag = buySellFlag;
	}

	/**
	 * @return the entity
	 */
	public String getEntity() {
		return entity;
	}

	/**
	 * @param entity
	 *            the entity to set
	 */
	public void setEntity(String entity) {
		this.entity = entity;
	}

	/**
	 * @return the units
	 */
	public int getUnits() {
		return units;
	}

	/**
	 * @param units
	 *            the units to set
	 */
	public void setUnits(int units) {
		this.units = units;
	}

	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * @param currency
	 *            the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * @return the instructionDate
	 */
	public LocalDate getInstructionDate() {
		return instructionDate;
	}

	/**
	 * @param instructionDate
	 *            the instructionDate to set
	 */
	public void setInstructionDate(LocalDate instructionDate) {
		this.instructionDate = instructionDate;
	}

	/**
	 * @return the settlementDate
	 */
	public LocalDate getSettlementDate() {
		return settlementDate;
	}

	/**
	 * @param settlementDate
	 *            the settlementDate to set
	 */
	public void setSettlementDate(LocalDate settlementDate) {
		this.settlementDate = settlementDate;
	}

}