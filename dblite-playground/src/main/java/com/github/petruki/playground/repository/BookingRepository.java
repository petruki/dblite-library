package com.github.petruki.playground.repository;

import android.content.Context;

import com.github.petruki.dblite.AbstractRepository;
import com.github.petruki.dblite.wrapper.EntityResolver;
import com.github.petruki.playground.model.Booking;
import com.github.petruki.playground.model.User;

import java.util.List;

public class BookingRepository extends AbstractRepository<Booking>
        implements EntityResolver<Booking> {

    private final UserRepository userRepository;

    public BookingRepository(Context context, UserRepository userRepository) {
        super(context, new BookingWrapper(), MyDatabase.class);
        this.userRepository = userRepository;
    }

    @Override
    public Booking findById(String id) throws Exception {
        return findById(id, this);
    }

    public List<Booking> findByUser(String id) throws Exception {
        return find("contactId = ?", new String[] { id }, this);
    }

    @Override
    public Booking resolve(Booking entity) throws Exception {
        User contact = userRepository.findById(entity.getContactId());
        entity.setContact(contact);
        return entity;
    }
}
