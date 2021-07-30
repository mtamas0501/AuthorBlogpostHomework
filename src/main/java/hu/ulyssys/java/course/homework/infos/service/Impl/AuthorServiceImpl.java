package hu.ulyssys.java.course.homework.infos.service.Impl;

import hu.ulyssys.java.course.homework.infos.entity.Author;
import hu.ulyssys.java.course.homework.infos.service.AuthorService;

import javax.enterprise.context.ApplicationScoped;
import java.text.SimpleDateFormat;
import java.util.Date;

@ApplicationScoped
public class AuthorServiceImpl extends AbstractServiceImpl<Author> implements AuthorService {

    public AuthorServiceImpl() {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(formatter.format(date));

        for (int i = 0; i < 100; i++) {
            Author author = new Author();
            author.setId(Long.parseLong(i+""));
            author.setFirstName("FirstName example ");
            author.setLastName("LastName example");
            author.setUsername("Username example");
            author.setCreatedDate(date);
            author.setLastModifiedDate(date);
            add(author);
        }
    }


    @Override
    public void update(Author example) {
        for (Author author:getAll()) {
            if (author.getId().equals(example.getId())){
                author.setFirstName(example.getFirstName());
                author.setLastName(example.getLastName());
                author.setUsername(example.getUsername());
                author.setCreatedDate(example.getCreatedDate());
                author.setLastModifiedDate(example.getLastModifiedDate());
                break;
            }
        }
    }
}
