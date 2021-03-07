package com.github.petruki.playground.repository;

import android.content.Context;

import com.github.petruki.dblite.AbstractRepository;
import com.github.petruki.playground.model.Booking;
import com.github.petruki.playground.model.User;

import java.util.List;

public class UserRepository extends AbstractRepository<User> {

    private final BookingRepository bookingRepository;

    public UserRepository(Context context) {
        super(context, new UserWrapper(), MyDatabase.class);
        bookingRepository = new BookingRepository(context, this);
    }

    public List<Booking> findUserBooking(String id) throws Exception {
        return bookingRepository.findByUser(id);
    }

}
