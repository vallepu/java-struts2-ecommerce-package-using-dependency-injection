package org.webhop.ywdc.extras;

public class State 
{
	public String symbol;
	

	public String state;

	public State(String symbol, String state)
	{
		setSymbol(symbol);
		setState(state);
	}
	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
