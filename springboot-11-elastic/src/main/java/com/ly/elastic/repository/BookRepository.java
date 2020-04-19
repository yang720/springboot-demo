package com.ly.elastic.repository;

import com.ly.elastic.bean.Book;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author liuyang
 * @date 2020/3/10 23:11
 */
@Component
public interface BookRepository extends ElasticsearchRepository<Book,Integer> {

    /**
     * 按书名查找实体类，测试类使用
     *
     * 更多使用参照 https://docs.spring.io/spring-data/elasticsearch/docs/3.2.5.RELEASE/reference/html/#elasticsearch.repositories
     *
     * @param bookName 书名
     * @return List<Book>
     */
//    List<Book> findByBookName(String bookName);

    /**
     * 使用注解的方式按书名模糊查询
     * @param bookName
     * @return
     */
    @Query("{\"match_phrase\":{\"bookName\":\"?0\"}}")
    List<Book> findByBookName(String bookName);
}
