package main;

import java.util.Arrays;

public class MyArrayList<T> implements MyList<T>{

	/**
	 * значение длины массива для конструктора без параметров
	 */
	
	private int defaultInitialLengthArray = 12;
	
	/**
	 * массив для хранения объектов
	 */
	private T[] values;
	
	/**
	 * количество занятых ячеек в массиве
	 */
	private int size = 0;
	
	/**
	 * показывает на сколько будет увеличен массив, когда в нем закончится место
	 */
	private final double growthFactor = 1.5d;
	
	public MyArrayList() {
		this.values = (T[]) new Object[defaultInitialLengthArray];
	}

	public MyArrayList(T[] values) {
		this.values = values;
	}

	public MyArrayList(int initialLengthArray) {
		this.values = (T[]) new Object[initialLengthArray];
	}

	/**
	 * Получает элемент из списка по указанному индексу.
	 * 
	 * @param index - индекс необходимого элемента в списке
	 * @return T - возвращаемый элемент
	 * @throws IndexOutOfBoundsException
	 */
	@Override
	public T get(int index) throws IndexOutOfBoundsException{
		if(index >= this.size || index < 0) {
			throw new IndexOutOfBoundsException("Данный индекс \"%d\" не находится в диапзоне массива".formatted(index));
		}
		return (T) this.values[index];
	}

	/**
	 * Добавляет новый элемент в конец списка, если места не осталось,
	 * то вызывает метод resize(T[] oldArray, double growthFactor) 
	 * для расширения массива и добавляет данный объект.
	 *
	 * @param value - объект для дабовления
	 */
	@Override
	public void add(T value) {
		if(this.size == this.values.length) {
			resize(this.values, growthFactor);
		}
		add(size, value);
	}
	
	/**
	 * Добавляет новый элемент в список по индексу, если места не осталось,
	 * то вызывает метод resize(T[] oldArray, double growthFactor) 
	 * для расширения массива и добавляет данный объект.
	 *
	 * @param value - объект для дабовления
	 */
	
	@Override
	public void add(int index, T value) {
		if(index > size || index < 0) {
			throw new IndexOutOfBoundsException("Данный индекс \"%d\" не находится в диапзоне массива".formatted(index));
		}
		if(this.size == this.values.length) {
			resize(this.values, growthFactor);
		}
		if(index == size) { // если вставка происходит в конец массива
			values[index] = value;
		}else {
			T newValue = value;
			T oldValue = null;
			int count = index;
			while(count <= size) {
				oldValue = values[count];
				values[count] = newValue;
				newValue = oldValue;
				count++;
			}
		}
		size++;
	}

	/**
	 * Устанавливает новое значение на уже существующем элементе,
	 * иначе выбрасывает IndexOutOfBoundsException.
	 * 
	 * @param index - индекс для вставки нового значения
	 * @param newValue - новое значение
	 * @return T - возвращает старое значение
	 * @throws IndexOutOfBoundsException
	 */
	
	@Override
	public T set(int index, T newValue)  throws IndexOutOfBoundsException{
		
		if(this.size == 0) {
			throw new IndexOutOfBoundsException("Данный массив не содержит элементов");
		}
		if(index < 0 || index >= this.size) {
			throw new IndexOutOfBoundsException("Данный индекс не находится в диапзоне массива");
		}
		
		T oldValue = this.values[index];
		this.values[index] = newValue;
		
		return oldValue;
	}

	/**
	 * Удаляет элемент из списка по указанному индексу.
	 * 
	 * @param index - индекс для удаления из списка
	 * @throws IndexOutOfBoundsException
	 */
	@Override
	public void remove(int index) throws IndexOutOfBoundsException{
		
		if(this.size == 0 || index < 0 || index >= this.size) {
			throw new IndexOutOfBoundsException("Данный индекс не находится в диапзоне массива");
		}
		
		if(this.size - 1 == index) {
			this.values[this.size - 1] = null;
		}else {
			shift(this.values, index);
			this.values[this.size - 1] = null;
		}
		this.size--;
	}

	
	/**
	 * Создает новый лист на сонове диапазона от переменной {@code from} включительно и до
	 * переменной {@code to} исключительно.
	 * 
	 * @param from - начало диапазона, включая сам индекс
	 * @param to - конец диапазона, исключая данный индекс
	 * @throws IllegalArgumentException
	 * @return MyArrayList - список с указанным диапозоном
	 */
	@Override
	public MySubList<T> subList(int from, int to) throws IllegalArgumentException{
		if(from < 0 || to < 0 || from >= this.size || to >= this.size) {
			throw new IllegalArgumentException("Значение startIndex и endIndex не может быть меньше 0 или больша размера(size) массива");
		}
		return new MySubList<T>(this, from, to);
	}

	/**
	 * Возвращает размер данного внутреннего массива.
	 */
	@Override
	public int size() {
		return this.size;
	}

	/**
	 * Проверяет содержит ли список х
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Если у массива нету места для вставки нового элемента,
	 * создается новый массив с {@code oldLength} * growthFactor.
	 * Все элементы из {@code oldArray} копируются в новый массив {@code newArray}.
	 * 
	 * @param oldArray - массив, которому нужно увеличеть length
	 * @param growthFactor - на сколько увеличится массив
	 */
	private void resize(T[] oldArray, double growthFactor) {
		if(oldArray.length == 0) {
			this.values =(T[]) new Object[defaultInitialLengthArray];
		}
		else {
			int oldLength = oldArray.length;
			int newLength = (int) Math.ceil(oldLength * growthFactor);
			T[] newArray = Arrays.copyOf(oldArray, newLength);
			this.values = newArray;
		}
	}
	
	/**
	 * Данный метод служит для сдвига влево всех элементов, находящихся правее удаляемого.
	 * Массив{@code temp} содержит все элементы, каторые находились правее удаляемого.
	 * Переменная {@code startTemp} содержит индекс значения из массива {@code temp},
	 * а переменная {@code startOrigin} содержит индекс для подстановки в массив {@code origin}.
	 * 
	 * @param origin - массив, в котором будет происходить сдвиг
	 * @param removeIndex - индекс в массиве, с которого будет происходить заполнение
	 */
	private void shift(T[] origin, int removeIndex) {
		
		T[] temp = Arrays.copyOfRange(origin, removeIndex + 1, this.size);
		
		int startTemp = 0;
		int startOrigin = removeIndex;
		
		while(startTemp < temp.length) {
			origin[startOrigin++] = temp[startTemp++];
		}
		
	}
	/**
	 * Возваращает длину внутреннего массива, который содержит зачения.
	 * @return value.length - длина массива
	 */
	public int getLenght() {
		return this.values.length;
	}

	@Override
	public String toString() {
		return Arrays.toString(this.values);
	}

	
	private static class MySubList<T> implements MyList<T>{

		private MyList<T> root;
		private MySubList<T> parent;
		private int firstIndex;
		private int size;
		
		public MySubList(MyList<T> root, int from, int to) {
			super();
			this.root = root;
			this.firstIndex = from;
			this.size = to - from;
		}

		public MySubList(MySubList<T> parent, int from, int to) {
			super();
			this.root = parent.root;
			this.parent = parent;
			this.firstIndex = parent.firstIndex + from;
			this.size = to - from;
		}

		@Override
		public T get(int index) {
			T value = root.get(firstIndex + index);
			return value;
		}

		@Override
		public void add(T value) {
			add(size, value);
		}

		@Override
		public void add(int index, T value) {
			
			if(index < 0 || index > size) {
				throw new IndexOutOfBoundsException("Индекс не может быть меньше 0 и больше параметра size");
			}
			
			root.add(firstIndex + index,  value);
			MySubList<T> p = this;
			while(p != null) {
				p.size++;
				p = p.parent;
			}
		}

		@Override
		public T set(int index, T newValue) {
			return root.set(firstIndex + index, newValue);
		}

		@Override
		public void remove(int index) {
			check(index);
			root.remove(firstIndex + index);
			
			MySubList<T> p = this;
			while(p != null) {
				p.size--;
				p = p.parent;
			}
			
		}

		@Override
		public MyList<T> subList(int from, int to) {
			if(from > to) {
				throw new IllegalArgumentException("индекс from не может быть больше индекса to");
			}else if((from < 0 || from > size) || (to < 0 || to> size)){
				throw new IndexOutOfBoundsException("Индекс не может быть меньше 0 и больше параметра size");
			}else {
				return new MySubList<T>(this, from, to);
			}
		}

		@Override
		public int size() {
			return this.size;
		}
		
		/**
		 * Проверяет валидность индекса для методов set(), remove() и get()
		 * @param index
		 */
		private void check(int index) {
			if(index < 0 || index >= size) {
				throw new IndexOutOfBoundsException("Индекс не может быть меньше 0 и больше параметра size");
			}
		}
	}
	
}
