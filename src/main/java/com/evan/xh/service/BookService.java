package com.evan.xh.service;

import com.evan.xh.dao.BookDao;
import com.evan.xh.pojo.Book;
import com.evan.xh.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    BookDao bookDao;
    @Autowired
    CategoryService categoryService;

    //查出所有书籍
    public List<Book> list(){
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        return bookDao.findAll(sort);
    }
    //增加或更新书籍
    public void addOrUpdate(Book book){
        //save() 方法的作用是，当主键存在时更新数据，当主键不存在时插入数据
        bookDao.save(book);
    }
    //通过 id 删除书籍
    public void deleteById(int id){
        bookDao.deleteById(id);
    }
    //通过分类查出书籍
    public List<Book> listByCategory(int cid){
        Category category = categoryService.get(cid);
        return bookDao.findAllByCategory(category);
    }
    public List<Book> search(String keywords){
        return bookDao.findAllByTitleLikeOrAuthorLike('%' + keywords + '%','%' + keywords + '%');
    }
}
