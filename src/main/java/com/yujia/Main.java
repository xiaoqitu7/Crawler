package com.yujia;

import org.apache.poi.hssf.usermodel.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class Main {
    final static Integer BOOK_LIST_SIZE=40;
    final static Integer PAGE=96;
    final static Integer COMMENT_COUNT=1000;
    final static Integer EXCEL_COR=8;
    public static void main(String[] args) {
        long startTime=System.currentTimeMillis();
        final List<Book> list = Collections.synchronizedList(new ArrayList());
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (int i = 0; i < PAGE; i++) {
            final int finalI = i;
            fixedThreadPool.execute(new Runnable() {
                public void run() {
                    String url = "https://book.douban.com/tag/%E7%BC%96%E7%A8%8B?start=" + (finalI * 20) + "&type=T";
                    String html =  startGet(url);
                    List<Book> pageBooks = listBook(html);
                    list.addAll(pageBooks);
                }
            });
        }
        fixedThreadPool.shutdown();
        try {
            fixedThreadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //System.out.println(list.size());
        List<Book> bookList=sortBookList(list);
        writeExcel(bookList);
        long endTime=System.currentTimeMillis();
        float excTime=(float)(endTime-startTime)/1000;
        System.out.println("执行时间"+excTime+"s");
    }
    public static List<Book> sortBookList(List<Book> bookList){
        Book[] array=bookList.toArray(new Book[bookList.size()]);
        MaxHeap maxHeap=new MaxHeap(array);
        int count=0;
        List<Book> list=new ArrayList<Book>();
        while(!maxHeap.isEmpty()&&count<BOOK_LIST_SIZE){
            Book book= (Book) maxHeap.deleteTop();
            if(book.getCommentCount()>=COMMENT_COUNT) {
                list.add(book);
                count++;
                System.out.println(book.getName() + "    " + book.getScore());
            }
        }
        return list;
    }
    public static void writeExcel(List<Book> bookList){
         String[] titleArray = {"序号", "书名", "评分", "评价人数", "作者", "出版社","出版日期","价格"};
        String fileName = "Test.xls";
        String sheetName = "Sheet1";
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(sheetName);
        HSSFRow row = sheet.createRow(0);
        for(int i=0;i<EXCEL_COR;i++){
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(titleArray[i]);
        }
        int index=0;
        for(Book book:bookList){
            HSSFRow row1 = sheet.createRow(++index);
            row1.createCell(0).setCellValue(index);
             row1.createCell(1).setCellValue(book.getName());
            row1.createCell(2).setCellValue(book.getScore());
            row1.createCell(3).setCellValue(book.getCommentCount());
             row1.createCell(4).setCellValue(book.getAuthor());
             row1.createCell(5).setCellValue(book.getPublishingHouse());
            row1.createCell(6).setCellValue(book.getDate());
            row1.createCell(7).setCellValue(book.getPrice());
        }
        //写到磁盘上
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(fileName));
            workbook.write(fileOutputStream);
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String startGet(String path){
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try {
            //InetSocketAddress addr = new InetSocketAddress("36.6.147.121",27320);
            //Proxy proxy = new Proxy(Proxy.Type.HTTP, addr); // http 代理
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            //Get请求不需要DoOutPut
            conn.setDoOutput(false);
            conn.setDoInput(true);
            //设置连接超时时间和读取超时时间
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(10000);
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; .NET CLR 3.0.04506)");
            //conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            //连接服务器
            conn.connect();
            // 取得输入流，并使用Reader读取
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //关闭输入流
        finally{
            try{
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result.toString();
    }
    public static List<Book> listBook(String html){
        Document doc = Jsoup.parse(html);
        Elements elements = doc.select(".subject-item");
        List<Book> bookList=new ArrayList<Book>();
        for(Element element:elements){
            String name=element.select("a").eq(1).text();
            String scoreTmp=element.select(".rating_nums").text();
            Double score=0.0;
            if(scoreTmp.length()!=0) {
                score = Double.parseDouble(scoreTmp);
            }
            String commentCountTemp=element.select(".pl").text();
            Integer commentCount=0;
            if(!commentCountTemp.equals("(少于10人评价)")) {
                commentCount = Integer.parseInt(commentCountTemp.substring(1, commentCountTemp.length() - 4));
            }
            String info=element.select(".pub").text();
            //System.out.println(content);
            String[] split=info.split("/");
            int splitLength=split.length;
            String author=split[0].trim();
            String publishingHouse="";
            String date="";
            String price="";
            if(splitLength>=4) {
                publishingHouse = split[splitLength - 3].trim();
                date = split[splitLength - 2].trim();
                price = split[splitLength - 1].trim();
            }
            bookList.add(new Book(name,score,commentCount,author,publishingHouse,date,price));
        }
        return bookList;
    }
}