package com.store.litflix.repository.book;

import com.store.litflix.model.Book;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BookRepository extends JpaRepository<Book, Long>,
        JpaSpecificationExecutor<Book> {
    List<Book> findAllByCategoriesId(Long categoryId);
}

