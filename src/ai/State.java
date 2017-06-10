package ai;

public class State {
	
	private long key;
	private int depth;
	private int value;
	private ValueFlag flag;
	State bestChild;
	public State(long key, int depth, int value, ValueFlag flag, State bestChild )
	{
		this.key = key;
		this.depth = depth;
		this.value = value;
		this.flag = flag;
		this.bestChild = bestChild;
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
	public State getBestChild() {
		return bestChild;
	}
	public void setBestChild(State bestChild) {
		this.bestChild = bestChild;
	}

}
