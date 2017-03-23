package ua.golovchenko.artem.dao;

import ua.golovchenko.artem.minimessage.model.Text;

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
}
