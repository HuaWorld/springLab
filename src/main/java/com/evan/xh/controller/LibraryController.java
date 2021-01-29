package com.evan.xh.controller;

import com.evan.xh.pojo.Book;
import com.evan.xh.service.BookService;
import com.evan.xh.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.reflect.annotation.ExceptionProxy;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
public class LibraryController {
    @Autowired //自动注入
    BookService bookService;

    @GetMapping("/api/books")
    public List<Book> list() throws Exception{
        return bookService.list();
    }
    @PostMapping("/api/books")
    public Book addOrUpdate(@RequestBody Book book) throws Exception{
        bookService.addOrUpdate(book);
        return book;
    }
    @PostMapping("/api/delete")
    public void delete(@RequestBody Book book) throws Exception{
        bookService.deleteById(book.getId());
    }
    @GetMapping("/api/categories/{cid}/books")
    public List<Book> listByCategory(@PathVariable int cid) throws Exception {
        if (0 != cid) {
            return bookService.listByCategory(cid);
        } else {
            return list();
        }
    }
    @CrossOrigin
    @GetMapping("/api/search")
    public List<Book> searchResult(@RequestParam("keywords") String keywords){
        // 关键词为空，查询所有书籍
        if(keywords.isEmpty() || keywords.equals("")){
            return bookService.list();
        }else{
            return bookService.search(keywords);
        }
    }
    @CrossOrigin
    @PostMapping("api/covers")
    public String coverUpload(MultipartFile file) throws Exception{
//        可以进一步做一下压缩，或者校验前端传来的数据是否为指定格式
        String folder = "C:/dev/backendImg"; //存放上传文件路径
        File imgFolder = new File(folder); // 创建文件实例
        // 生成随机字符名称
        File imgName = new File(imgFolder
                , StringUtils.getRandomString(6)
                + file.getOriginalFilename()
                .substring(file.getOriginalFilename().length() - 4)
        );
        if (!imgName.getParentFile().exists())
            // 创建文件夹以及父文件夹
            imgName.getParentFile().mkdirs();
        try{
            file.transferTo(imgName);// 转换名称
            String imgUrl = "http://localhost:8443/api/file/" + imgName.getName();
            return imgUrl;
        }catch (IOException e){
            e.printStackTrace();
            return "";
        }
    }
}
