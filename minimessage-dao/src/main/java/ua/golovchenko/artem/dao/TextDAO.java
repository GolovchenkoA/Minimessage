package ua.golovchenko.artem.dao;

import ua.golovchenko.artem.minimessage.model.Text;

/**
 * Created by головченко on 19.03.2017.
 *
 * @
 */



public interface TextDAO {

    public void add(Text text);
    public Text get(Long textId);
}
