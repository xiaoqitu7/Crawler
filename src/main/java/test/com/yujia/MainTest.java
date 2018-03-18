package test.com.yujia; 

import com.yujia.Book;
import com.yujia.Main;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/** 
* Main Tester. 
* 
* @author jia.yu
* @since <pre>���� 18, 2018</pre> 
* @version 1.0 
*/ 
public class MainTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: main(String[] args) 
* 
*/ 
@Test
public void testMain() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: sortBookList(List<Book> bookList) 
* 
*/ 
@Test
public void testSortBookList() throws Exception { 
//TODO: Test goes here...
    List<Book> bookList=new ArrayList<Book>();
    bookList.add(new Book("计算机程序的构造和解释 : 原书第2版",9.5,1877,"Harold Abelson、Gerald Jay Sussman、Julie Sussman","机械工业出版社","2004-2","45.00元"));
    bookList.add(new Book("编码 : 隐匿在计算机软硬件背后的语言",9.3,1988,"[美] Charles Petzold","电子工业出版社","2010","55.00元"));
    bookList.add(new Book("代码大全（第2版）",9.3,3401," [美] 史蒂夫·迈克康奈尔","电子工业出版社","2006-3","128.00元"));
    bookList.add(new Book("C程序设计语言 : 第 2 版·新版",9.4,3917,"（美）Brian W. Kernighan、（美）Dennis M. Ritchie","机械工业出版社","2004-1","30.00元"));
    bookList.add(new Book(" 算法（第4版）",9.4,652,"塞奇威克 (Robert Sedgewick)、韦恩 (Kevin Wayne)","人民邮电出版社"," 2012-10-1","99.00元"));
    List<Book> list=Main.sortBookList(bookList);
    for(Book book:list){
        System.out.print(book.getName());
        System.out.print(book.getScore());
        System.out.print(book.getCommentCount());
        System.out.print(book.getAuthor());
        System.out.print(book.getPublishingHouse());
        System.out.print(book.getDate());
        System.out.println(book.getPrice());
    }
} 

/** 
* 
* Method: writeExcel(List<Book> bookList) 
* 
*/ 
@Test
public void testWriteExcel() throws Exception {
//TODO: Test goes here...
    List<Book> bookList=new ArrayList<Book>();
    bookList.add(new Book("计算机程序的构造和解释 : 原书第2版",9.5,1877,"Harold Abelson、Gerald Jay Sussman、Julie Sussman","机械工业出版社","2004-2","45.00元"));
    bookList.add(new Book("编码 : 隐匿在计算机软硬件背后的语言",9.3,1988,"[美] Charles Petzold","电子工业出版社","2010","55.00元"));
    bookList.add(new Book("代码大全（第2版）",9.3,3401," [美] 史蒂夫·迈克康奈尔","电子工业出版社","2006-3","128.00元"));
    bookList.add(new Book("C程序设计语言 : 第 2 版·新版",9.4,3917,"（美）Brian W. Kernighan、（美）Dennis M. Ritchie","机械工业出版社","2004-1","30.00元"));
    bookList.add(new Book(" 算法（第4版）",9.4,652,"塞奇威克 (Robert Sedgewick)、韦恩 (Kevin Wayne)","人民邮电出版社"," 2012-10-1","99.00元"));
    Main.writeExcel(bookList);
} 

/** 
* 
* Method: startGet(String path) 
* 
*/ 
@Test
public void testStartGet() throws Exception { 
//TODO: Test goes here...
    String url="https://book.douban.com/tag/%E7%BC%96%E7%A8%8B?start=0&type=T";
    String html= Main.startGet(url);
    System.out.println(html);

} 

/** 
* 
* Method: listBook(String html) 
* 
*/ 
@Test
public void testListBook() throws Exception { 
//TODO: Test goes here...
    String url="https://book.douban.com/tag/%E7%BC%96%E7%A8%8B?start=0&type=T";
    String html= Main.startGet(url);
    List<Book> bookList=Main.listBook(html);
    for(Book book:bookList){
        System.out.print(book.getName());
        System.out.print(book.getScore());
        System.out.print(book.getCommentCount());
        System.out.print(book.getAuthor());
        System.out.print(book.getPublishingHouse());
        System.out.print(book.getDate());
        System.out.println(book.getPrice());
    }
} 


} 
