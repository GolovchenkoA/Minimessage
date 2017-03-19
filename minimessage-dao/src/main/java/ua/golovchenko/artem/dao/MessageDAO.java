package ua.golovchenko.artem.dao;

import ua.golovchenko.artem.minimessage.model.Message;

import java.util.List;

/**
 * Created by головченко on 17.03.2017.
 */
public interface MessageDAO{

    void add (Message message);
    Message get(Long id);
    void delete(Long id);
    void update(Message message);
    List<Message> findAll();
    List<Message> findAllbyUserId(Long userId);

}
