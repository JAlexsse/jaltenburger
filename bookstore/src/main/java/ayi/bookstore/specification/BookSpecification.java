package ayi.bookstore.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


import org.springframework.data.jpa.domain.Specification;

import ayi.bookstore.entity.Book;

public class BookSpecification {

    public Specification<Book> getUserSpecificationByAuthorOrPublishing(String attribute, int valueAttribute){
        return new Specification<Book>() {

            private static final long serialVersionUID = 1L;

            @Override 
            public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            
            Path<Book> path = root.get(attribute);
            return cb.equal(path, valueAttribute);
          };
        };
    }
    
    
}
