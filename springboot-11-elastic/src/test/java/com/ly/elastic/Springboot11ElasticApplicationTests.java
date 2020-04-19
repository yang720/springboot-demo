package com.ly.elastic;

import com.ly.elastic.bean.Article;
import com.ly.elastic.bean.Book;
import com.ly.elastic.repository.BookRepository;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;

@SpringBootTest
class Springboot11ElasticApplicationTests {

    @Resource
    JestClient jestClient;

    @Resource
    BookRepository bookRepository;

    @Test
    public void test02(){
//        Book book = new Book();
//        book.setId(1);
//        book.setBookName("西游记");
//        book.setAuthor("吴承恩");
//        bookRepository.index(book);

        for (Book book : bookRepository.findByBookName("游")) {
            System.out.println(book);
        }
    }

    /**
     * Jest 构造索引文档
     */
    @Test
    void contextLoads() {
        //1、给es中的索引(保存)一个文档
        Article article = new Article();
        article.setId(1);
        article.setAuthor("zhangsan");
        article.setTitle("好消息");
        article.setContent("hello");

        //构建一个索引功能
        Index index = new Index.Builder(article).index("ly").type("news").build();

        try {
            //执行
            jestClient.execute(index);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Jest 测试搜索
     */
    @Test
    public void search(){
        //查询表达式
        String json = "{\n" +
                "     \"query\" : {\n" +
                "         \"match\" : {\n" +
                "             \"last_name\" : \"hello\"\n" +
                "         }\n" +
                "}";
        //构建搜索功能
        Search search = new Search.Builder(json).addIndex("ly").addType("news").build();

        try {
            SearchResult result = jestClient.execute(search);
            System.out.println(result.getJsonString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
