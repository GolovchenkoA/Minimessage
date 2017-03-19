package ua.golovchenko.artem.minimessage.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by головченко on 19.03.2017.
 */

@Entity(name = "TEXT")
public class TextImpl implements Text {

    private long id;
    private String text;


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
        return text;
    }
}
