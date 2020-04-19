package com.ly.elastic.controller;

import com.ly.elastic.bean.Book;
import com.ly.elastic.repository.BookRepository;
import com.ly.elastic.service.BookService;
import com.ly.elastic.utils.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liuyang
 * @date 2020/3/11 23:53
 */
@RestController
public class BookController {

    @Resource
    BookService bookService;

    @GetMapping("/addBook")
    public Result addBook(Book book){
//        Book book = new Book();
//        book.setId(1);
//        book.setBookName("西游记");
//        book.setAuthor("吴承恩");
        Result result = bookService.saveToEs(book);

        return result;
    }

    @GetMapping("/book/{name}")
    public List<Book> findByBookName(@PathVariable("name") String name){
        List<Book> books = bookService.findByBookName(name);
        return books;
    }

    @PutMapping("/update")
    public Result updateById(Book book){
        Result result = bookService.saveToEs(book);
        return result;
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteBook(@PathVariable("id") Integer id){
        Result result = bookService.deleteById(id);
        return result;
    }
}
