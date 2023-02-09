package com.progmatic.SpringIoc.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ContactRepository extends CrudRepository<Contact, Long> {
    @Query("""
            select c from Contact c
            where
                upper(c.name) like upper(concat('%', ?1, '%'))
                or upper(c.tel) like upper(concat('%', ?1, '%'))
                or upper(c.email) like upper(concat('%', ?1, '%'))
                or upper(c.misc) like upper(concat('%', ?1, '%'))
    """)
    List<Contact> findByAll(String text);
}
