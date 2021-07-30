package hu.ulyssys.java.course.homework.infos.entity;


import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

public class BlogPost extends AbstractProperty {


    private String title;
    private String content;
    @Enumerated(EnumType.STRING)
    private CategoryType category;
    private Date publishedDate;

    public BlogPost() {
    }

    public BlogPost(Long id, Date createdDate, Date lastModifiedDate, String title, String content, Date publishedDate) {
        super(id, createdDate, lastModifiedDate);
        this.title = title;
        this.content = content;
        this.publishedDate = publishedDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public CategoryType getCategory() {
        return category;
    }

    public void setCategory(CategoryType category) {
        this.category = category;
    }
}
