package main;

import java.util.NoSuchElementException;
/**
 * Данный класс представляет двусвязный список. Элементами списка является
 * класс MyNode<T>.
 * @param <T> - тип хранимого значения
 */
public class MyLinkedList<T> implements MyList<T>{
	
	/**
	 *текущий размер MyLinkedList
	 */
	private int size = 0;
	
	/**
	 * первый элемент в MyLinkedList
	 */
	private MyNode<T> head;
	
	/**
	 * последний элемент в MyLinkedList
	 */
	private MyNode<T> tail;

	
	public MyLinkedList() {
	}
	/**
	 * Получает уже существующий элемент из MyLinkedList по индексу,
	 * иначе выбрасывает IndexOutOfBoundsException.
	 * 
	 * @param index - индекс получаемого элемента
	 * @return T - возвращаемое значение
	 */
	@Override
	public T get(int index){
		
		T value = find(index).value;
		return value;
	}

	/**
	 * Добавляет новый элемент в конец MyLinkedList
	 * @param value - новый элемент
	 */
	@Override
	public void add(T value) {
		add(size, value);
	}
	
	
	/**
	 * Добавляет новый элемент по указанному индексу.
	 * 
	 * @param index - индекс нового значения
	 * @param value - новое значение
	 */
	@Override
	public void add(int index, T value) {
		
		MyNode<T> newNode = new MyNode<>(value);
		
		if(size == 0 && index == 0) { // элемент добавляется в пустой список
			head = newNode;
			tail = newNode;	
		}else if(index == 0 && size >= 1) { // добавление происходит в начало списка
			MyNode<T> temp = head;
			head = newNode;
			head.next = temp;
			temp.prev = head;
		}else if(index == size && index > 0) {// добавление происходит в конец списка
			newNode.prev = tail;
			tail.next = newNode;
			tail = newNode;
		}
		else {								// добавление в середину
			MyNode<T> oldNode = find(index); 
				
			MyNode<T>oldNodePrev = oldNode.prev;
			oldNodePrev.next = newNode;
			oldNode.prev = newNode;
			
			newNode.next = oldNode;
			newNode.prev = oldNodePrev;
		}
		size++;
	}

	/**
	 * Устанавливает новое значение существующему элементу в MyLinkedList,
	 * иначе выбрасывает IndexOutOfBoundsException.
	 * 
	 * @param index - индекс элемента для установки нового значения
	 * @param newValue - новое значение
	 * @return T - возвращает старое зачение элемента
	 */
	@Override
	public T set(int index, T newValue) {
	
		if(size == 0) {
			throw new NoSuchElementException("Данного элемента нету в списке");
		}
		MyNode<T> oldNode = find(index);
		T oldValue = oldNode.value;
		oldNode.value = newValue;
		
		return oldValue;
	}

	/**
	 * Удаляет существующий элемент из MyLinkedList, иначе выбрасывает 
	 * IndexOutOfBoundsException.
	 *  
	 * @param index - индекс элемента для удаления
	 */
	@Override
	public void remove(int index) {
		if(size == 0) {
			throw new NoSuchElementException("Данного элемента нету в списке");
		}
		
		MyNode<T> removed = find(index);
		
		if(index == 0 && size == 1) { // если в MyLinkedList содержался один элемент
			head = null;
			tail = null;
		}
		else if(index == 1 && size == 2) { // если в MyLinkedList содержалось только 2 элемента
			tail = head;
		}
		else{
			if(index == 0) {
				head = head.next;
			}else if(index == size -1) {
				tail = tail.prev;
			}else {
			
				MyNode<T> prevNode = removed.prev;
				MyNode<T> nextNode = removed.next;
				
				prevNode.next = nextNode;
				nextNode.prev = prevNode;
			}
		}
		size--;
		
	}
	
	/**
	 *Создает новый лист на сонове диапазона от переменной {@code from} включительно и до
	 *переменной {@code to} исключительно.
	 *
	 *@param from - индекс начала диапозона. Индекс включен в диапозон
	 *@param to - индекс конца диапозона. Индекс не входит в диапозон
	 *
	 *@return MyList - новый писок
	 */
	@Override
	public MyList<T> subList(int from, int to) {
		if(from > to) {
			throw new IllegalArgumentException("индекс from не может быть больше индекса to");
		}
		
		MyNode<T> first = find(from);
		MyNode<T> last = find(to);
		
		return new MySubList<T>(this, from, to) ;
		
	}

	@Override
	public int size() {
		return this.size;
	}
	
	/**
	 * Ищет необходимый элемент по индeксу, начиная с tail(первого элемента в MyLinkedList).
	 * 
	 * @param index - индекс необходимого элемента
	 * @return MyNode<T> - возвращает элемент, находящийся по индексу
	 * @throws IndexOutOfBoundsException
	 */
	private MyNode<T> find(int index) throws IndexOutOfBoundsException{
		
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Индекс не может быть меньше 0 и больше size()");
		}
		
		MyNode<T> value = null;
		//если индекс == 0 то возвращаем значение первого элемента
		if(index == 0) {
			value = this.head;
		}
		//если индекс == size - 1 то возвращаем значение последнего элемента
		else if(index == size - 1){

			value = this.tail;
		}
		else {
			
			int count = 0;
			MyNode<T> temp = head; // - 0 элемент
			while(count != index) {
				temp = temp.next;
				count++;
			}
			value = temp;
		};
		
		return value;
	}
	
	/**
	 * Возвращает значение первого элемента в MyArrayList
	 * 
	 * @return T - значение элемента
	 * @throws NoSuchElementException
	 */
	public T getFirst() throws NoSuchElementException{
		if(head != null) {
			return head.value;
		}
		throw new NoSuchElementException("Данный элемент отсутствует");
	}
	
	/**
	 * Возвращает значение последнего элемента в MyArrayList
	 * 
	 * @return T - значение элемента
	 */
	public T getLast() throws NoSuchElementException{
		if(tail != null) {
			return tail.value;
		}
		throw new NoSuchElementException("Данный элемент отсутствует");
	}
	
	/**
	 * Является внутренним элементом MyLinkedList. Хранит значение, а также 
	 * ссылки на следующий элемент  и предыдущий. Образует двусвязный список.
	 * @param <T>
	 */
	private static class MyNode<T>{
		/**
		 * хранимое значение
		 */
		T value;
		/**
		 * ссылка на следующий элемент
		 */
		MyNode<T> next;
		/**
		 * ссылка на предыдущий элемент
		 */
		MyNode<T> prev;
		

		public MyNode(T value) {
			this.value = value;
		}


		public MyNode(T value, MyNode<T> next, MyNode<T> prev) {
			this.value = value;
			this.next = next;
			this.prev = prev;
		}
		
		
	}
	
	private static class MySubList<T> implements MyList<T>{
		
		private MyList<T> root;
		private MySubList<T> parent;
		private int startIndex;
		private int size;
		
		public MySubList(MyList<T> root, int fromIndex, int toIndex) {
			this.root = root;
			this.startIndex = fromIndex;
			this.size = toIndex - fromIndex;
		}
		
		public MySubList(MySubList<T> parent, int fromIndex, int toIndex) {
			this.root = parent.root;
			this.parent = parent;
			this.startIndex = parent.startIndex + fromIndex;
			this.size = toIndex - fromIndex;
		}

		@Override
		public T get(int index) {
			
			indexWithinBounds(index);
			return root.get(startIndex + index);
		}

		@Override
		public void add(T value) {
			
			root.add(startIndex + size(), value);
			
			MySubList<T> temp = this;
			while(temp != null) {// проход по каждому родительском MySubList и увеличение size + 1
				temp.size++;
				System.err.println("temp.size = " + temp.size);
				temp = temp.parent;
			}
		}
		
		@Override
		public void add(int index, T value) {
			indexWithinBounds(index);
			root.add(startIndex + index, value);
			
			MySubList<T> temp = this;
			while(temp != null) {// проход по каждому родительском MySubList и увеличение size + 1
				temp.size++;
				temp = temp.parent;
			}
			
		}

		@Override
		public T set(int index, T newValue) {
			indexWithinBounds(index);
			T oldValue = root.set(startIndex + index, newValue);
			return oldValue;
		}

		@Override
		public void remove(int index) {
			indexWithinBounds(index);
			root.remove(startIndex + index);
			
			MySubList<T> temp = this;// проход по каждому родительском MySubList и уменьшение size - 1
			while(temp != null) {
				temp.size--;
				temp = temp.parent;
			}
		}

		@Override
		public MyList<T> subList(int from, int to) {
			indexWithinBounds(from);
			indexWithinBounds(to);
			if(from > to) {
				throw new IllegalArgumentException("индекс from не может быть больше to");
			}
			return new MySubList<T>(this, from, to);
		}
		
		private boolean indexWithinBounds(int index) throws IndexOutOfBoundsException{
			if(index < 0 || index >= size) {
				throw new IndexOutOfBoundsException("Индекс не может быть меньше 0 и больше size()");
			}
			return true;
		}

		@Override
		public int size() {
			return this.size;
		}
		
	}

}
