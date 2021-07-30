package hu.ulyssys.java.course.homework.infos.service.Impl;

import hu.ulyssys.java.course.homework.infos.entity.BlogPost;
import hu.ulyssys.java.course.homework.infos.entity.CategoryType;
import hu.ulyssys.java.course.homework.infos.service.BlogPostService;

import javax.enterprise.context.ApplicationScoped;
import java.text.SimpleDateFormat;
import java.util.Date;

@ApplicationScoped
public class BlogPostServiceImpl extends AbstractServiceImpl<BlogPost> implements BlogPostService {

    public BlogPostServiceImpl() {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(formatter.format(date));

        for (int i = 0; i < 100; i++) {
            BlogPost blogPost = new BlogPost();
            blogPost.setId(Long.parseLong(i+""));
            blogPost.setTitle("Title example ");
            blogPost.setContent("Content example");
            blogPost.setCategory(CategoryType.JAVA);
            blogPost.setCreatedDate(date);
            blogPost.setLastModifiedDate(date);
            blogPost.setPublishedDate(date);
            add(blogPost);
        }
    }

    @Override
    public void update(BlogPost example) {
        for (BlogPost blogPost: getAll()) {
            if (blogPost.getId().equals(example.getId())){
                blogPost.setTitle(example.getTitle());
                blogPost.setContent(example.getContent());
                blogPost.setCreatedDate(example.getCreatedDate());
                blogPost.setLastModifiedDate(example.getLastModifiedDate());
                blogPost.setPublishedDate(example.getPublishedDate());
                break;
            }
        }
    }
}
