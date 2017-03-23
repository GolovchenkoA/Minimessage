package ua.golovchenko.artem.minimessage.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by головченко on 19.03.2017.
 */

@Entity(name = "TEXT")
public class TextImpl implements Text,Serializable {

    private long id;
    private String text;


    public TextImpl() {}

    public TextImpl(String text) {
        this.text = text;
    }

    @Override
    @Id
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public void addText(String text) {
        this.text += text;
    }

    public void setText(String text){
        addText(text);
    }


    @Override
    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "Id=" + id + " text=" + text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextImpl text1 = (TextImpl) o;

        //if (id != text1.id) return false;
        if (!text.equals(text1.text)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;
        //int result = (int) (id ^ (id >>> 32));
        result = 31 * result + text.hashCode();
        return result;
    }


}
