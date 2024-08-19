package main;

public interface MyList<T> {
	
	public T get(int index);
	
	public void add(T value);
	
	public void add(int index, T value);
	
	public T set(int index, T newValue);
	
	public void remove(int index);
	
	public MyList<T> subList(int from, int to);
	
	public int size();
}
