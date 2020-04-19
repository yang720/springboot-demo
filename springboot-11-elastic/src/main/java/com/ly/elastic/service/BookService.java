package com.ly.elastic.service;

import com.ly.elastic.bean.Book;
import com.ly.elastic.repository.BookRepository;
import com.ly.elastic.utils.Result;
import com.ly.elastic.utils.ResultUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liuyang
 * @date 2020/3/11 23:55
 */
@Service
public class BookService {

    @Resource
    BookRepository bookRepository;

    public Result saveToEs(Book book){
        Integer id = book.getId();
        if (id == null){
            return ResultUtil.error(404,"id：null");
        }
        bookRepository.save(book);
        return ResultUtil.success(book);
    }

    public List<Book> findByBookName(String name){
        return bookRepository.findByBookName(name);
    }

    public Result deleteById(Integer id){
        if (id == null){
            return ResultUtil.error(404,"id:null");
        }
        bookRepository.deleteById(id);
        return ResultUtil.success();
    }

}
