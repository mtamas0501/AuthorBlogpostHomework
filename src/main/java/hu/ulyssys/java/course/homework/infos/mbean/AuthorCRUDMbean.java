package hu.ulyssys.java.course.homework.infos.mbean;

import hu.ulyssys.java.course.homework.infos.entity.Author;
import hu.ulyssys.java.course.homework.infos.service.AuthorService;
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
public class AuthorCRUDMbean implements Serializable {

    private List<Author> list;
    private Author selectedAuthor;

    private boolean inFunction;

    public boolean isInFunction() {
        return inFunction;
    }

    private AuthorService authorService;



    @Inject
    public AuthorCRUDMbean(AuthorService authorService) {
        this.authorService = authorService;
        list = authorService.getAll();
        selectedAuthor = new Author();
    }

    public void initSave() {
        selectedAuthor = new Author();
        inFunction = true;
    }

    public void initEdit(Author author) {
        selectedAuthor = author;
        inFunction = true;
    }

    public void save() {
        if (selectedAuthor.getId() == null) {
            selectedAuthor.setId(System.currentTimeMillis());
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            Date date = new Date(System.currentTimeMillis());
            selectedAuthor.setCreatedDate(date);
            authorService.add(selectedAuthor);
            list = authorService.getAll();
            selectedAuthor = new Author();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sikeres hozzáadás"));
        } else {
            authorService.update(selectedAuthor);
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            Date date = new Date(System.currentTimeMillis());
            selectedAuthor.setLastModifiedDate(date);
            list = authorService.getAll();
            selectedAuthor = new Author();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sikeres módsítás"));
        }
        PrimeFaces.current().executeScript("PF('authorDialog').hide()");
    }

    public void remove(Author author) {
        authorService.remove(author);
        list = authorService.getAll();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sikeres törlés"));
    }

    public List<Author> getList() {
        return list;
    }

    public void setList(List<Author> list) {
        this.list = list;
    }

    public Author getSelectedAuthor() {
        return selectedAuthor;
    }

    public void setSelectedAuthor(Author selectedAuthor) {
        this.selectedAuthor = selectedAuthor;
    }

}