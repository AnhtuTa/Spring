package beans;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class JavaCollection {
	List<Book> bookList;
	Set<Book> bookSet;
	Map<Integer, Book> bookMap;
	Properties bookProp;
	
	public List<Book> getBookList() {
		System.out.println("This is getBookList(), bookList = " + bookList);
		return bookList;
	}
	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}
	public Set<Book> getBookSet() {
		System.out.println("This is getBookSet(), bookSet = " + bookSet);
		return bookSet;
	}
	public void setBookSet(Set<Book> bookSet) {
		this.bookSet = bookSet;
	}
	public Map<Integer, Book> getBookMap() {
		System.out.println("This is getBookMap(), bookMap = " + bookMap);
		return bookMap;
	}
	public void setBookMap(Map<Integer, Book> bookMap) {
		this.bookMap = bookMap;
	}
	public Properties getBookProp() {
		System.out.println("This is getBookProp(), bookProp = " + bookProp);
		return bookProp;
	}
	public void setBookProp(Properties bookProp) {
		this.bookProp = bookProp;
	}
	
}
