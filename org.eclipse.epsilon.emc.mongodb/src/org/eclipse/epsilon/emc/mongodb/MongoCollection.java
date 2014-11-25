package org.eclipse.epsilon.emc.mongodb;

import java.util.Collection;
import java.util.Iterator;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class MongoCollection implements Collection<Object> {
	
	protected DBCollection col;
	
	class DBCursorIterator implements Iterator<Object> {
		
		protected DBCursor cursor;
		
		public DBCursorIterator(DBCursor cursor) {
			this.cursor = cursor;
		}
		
		@Override
		public boolean hasNext() {
			return cursor.hasNext();
		}

		@Override
		public DBObject next() {
			return cursor.next();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
	}
	
	public MongoCollection(DBCollection col) {
		this.col = col;
	}
	
	@Override
	public int size() {
		return col.find().size();
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public Iterator<Object> iterator() {
		return new DBCursorIterator(col.find());
	}

	@Override
	public boolean contains(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object[] toArray() {
		throw new UnsupportedOperationException();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean add(Object e) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean remove(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(Collection<? extends Object> c) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
		throw new UnsupportedOperationException();
	}
	
}