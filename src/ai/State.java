package ai;

import model.Move;

public class State {
	
	private long key;
	private int depth;
	private int value;
	private ValueFlag flag;
	Move bestMove;
	public State(long key, int depth, int value, ValueFlag flag, Move bestMove )
	{
		this.key = key;
		this.depth = depth;
		this.value = value;
		this.flag = flag;
		this.bestMove = bestMove;
	}
	public long getKey() {
		return key;
	}
	public void setKey(long key) {
		this.key = key;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public ValueFlag getFlag() {
		return flag;
	}
	public void setFlag(ValueFlag flag) {
		this.flag = flag;
	}
	public Move getBestMove() {
		return bestMove;
	}
	public void setBestMove(Move bestMove) {
		this.bestMove = bestMove;
	}
}
