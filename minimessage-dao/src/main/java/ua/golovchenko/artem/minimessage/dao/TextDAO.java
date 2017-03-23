package ua.golovchenko.artem.minimessage.dao;

import ua.golovchenko.artem.minimessage.model.Text;

import java.util.List;

/**
 * Created by головченко on 19.03.2017.
 *
 */

public interface TextDAO {

    /**
    * Add new and update old objects
     * @param text element to by added or updated
    * */
    public void add(Text text);
    public Text get(Long textId);
    public List<Text> findAll();
}
