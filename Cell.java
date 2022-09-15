public class Cell {

	boolean open;
	char symbol;
	
	public Cell(char symbol) {
		this.open = false;
		this.symbol = symbol;
	}
	
	public boolean isPlayable() {
		return !open;
	}
	
	public boolean isSameWith(Cell cell) {
		return this.symbol == cell.symbol;
	}
	
	public void close() {
		open = false;
	}
	
	public void open() {
		open = true;
	}
	
	public char getSymbol() {
		return this.symbol;
	}
	
	@Override
	public String toString() {
		return open ? ""+symbol : ".";
	}
}
