# crawler-demo
一个用Java实现的豆瓣读书的爬虫

1.使用JDK的java.net包中提供的访问HTTP协议的基本功能的类：HttpURLConnection来抓取网页内容。采用User Agent伪装为浏览器进行爬取。

2.使用jsoup解析html获取书籍信息。

3.使用堆排序算法得到评分前40的书籍。构造n个数据元素的最大堆，时间复杂度为O（nlog2n）。取出堆顶元素，即为当前堆的最大数，时间复杂度为O（log2n）。

4.使用多线程爬取

综合5次程序运行，运行时长约为4.40s
