package hu.ulyssys.java.course.homework.infos.mbean;

import hu.ulyssys.java.course.homework.infos.entity.BlogPost;
import hu.ulyssys.java.course.homework.infos.service.BlogPostService;
import org.primefaces.PrimeFaces;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Named
@ViewScoped
public class BlogPostCRUDMbean implements Serializable {

    private List<BlogPost> list;
    private BlogPost selectedBlogPost;

    private boolean inFunction;

    public boolean isInFunction() {
        return inFunction;
    }

    private BlogPostService blogPostService;

    @Inject
    public BlogPostCRUDMbean(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
        list = blogPostService.getAll();
        selectedBlogPost = new BlogPost();
    }

    public void initSave() {
        selectedBlogPost = new BlogPost();
        inFunction = true;
    }

    public void initEdit(BlogPost blogPost) {
        selectedBlogPost = blogPost;
        inFunction = true;
    }

    public void save() {
        if (selectedBlogPost.getId() == null) {
            selectedBlogPost.setId(System.currentTimeMillis());
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            Date date = new Date(System.currentTimeMillis());
            selectedBlogPost.setCreatedDate(date);
            blogPostService.add(selectedBlogPost);
            list = blogPostService.getAll();
            selectedBlogPost = new BlogPost();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sikeres hozzáadás"));
        } else {
            blogPostService.update(selectedBlogPost);
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            Date date = new Date(System.currentTimeMillis());
            selectedBlogPost.setLastModifiedDate(date);
            list = blogPostService.getAll();
            selectedBlogPost = new BlogPost();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sikeres módsítás"));
        }
        PrimeFaces.current().executeScript("PF('blogPostDialog').hide()");
    }

    public void remove(BlogPost blogPost) {
        blogPostService.remove(blogPost);
        list = blogPostService.getAll();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sikeres törlés"));
    }

    public List<BlogPost> getList() {
        return list;
    }

    public void setList(List<BlogPost> list) {
        this.list = list;
    }

    public BlogPost getSelectedBlogPost() {
        return selectedBlogPost;
    }

    public void setSelectedBlogPost(BlogPost selectedBlogPost) {
        this.selectedBlogPost = selectedBlogPost;
    }

}