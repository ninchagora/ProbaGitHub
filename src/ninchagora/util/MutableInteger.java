
package ninchagora.util;

public class MutableInteger {
	public MutableInteger() {
		this(0);
	}
	
	public MutableInteger(int value) {
		super();
		this.value = value;
	}

	private int value;
	
	public void inc() {
		value++;
	}
	
	public void dec() {
		value--;
	}
	
	public int get() {
		return value;
	}
	
	public void set(int value) {
		this.value = value;
	}

	public void add(int value) {
		this.value += value;
	}
	
	public void sub(int value) {
		this.value -= value;
	}

	public int getAndIncrement() {
		return value++;
	}
	
	public int incrementAndGet() {
		return ++value;
	}
	
	public int getAndDecrement() {
		return value--;
	}
	
	public int decrementAndGet() {
		return --value;
	}
	
	public MutableInteger setTo(int value) {
		set(value);
		return this;
	}
	
	@Override
	public String toString() {
		return Integer.toString(value);
	}

	
}
