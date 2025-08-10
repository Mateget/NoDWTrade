package nodwtrade;

public class Config {

	private String noTradeDWMessage;
	private boolean tradingMachines = true;
	private boolean pokesell = true;

	public Config() {
		noTradeDWMessage = "&cTrade canceled because it contain a breedable DW";

	}

	public String getNoTradeDWMessage() {
		return noTradeDWMessage;
	}

	public boolean isTradingMachines() {
		return tradingMachines;
	}

	public void setTradingMachines(boolean tradingMachines) {
		this.tradingMachines = tradingMachines;
	}

	public boolean isPokesell() {
		return pokesell;
	}

	public void setPokesell(boolean pokesell) {
		this.pokesell = pokesell;
	}

}
