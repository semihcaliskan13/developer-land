package com.depo.userservice.service.impl;

import com.depo.userservice.entity.User;
import com.depo.userservice.entry.UserEntry;
import com.depo.userservice.exception.ResourceNotFoundException;
import com.depo.userservice.exception.UserNotFoundException;
import com.depo.userservice.mapper.UserMapper;
import com.depo.userservice.repository.UserLDAPRepository;
import com.depo.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.ldap.query.SearchScope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("userLDAPService")
@ConditionalOnProperty(name = "data.source.location", havingValue = "ldap")
@RequiredArgsConstructor
public class UserLDAPService implements UserService {

    private final UserLDAPRepository userLDAPRepository;

    @Override
    public User getById(String id) {
        UserEntry userEntry = userLDAPRepository.getById(id).orElseThrow(UserNotFoundException::new);
        return UserMapper.INSTANCE.userEntryToUser(userEntry);
    }

    @Override
    public List<User> getAll() {
        List<UserEntry> userEntries = userLDAPRepository.getAllUsers();
        return UserMapper.INSTANCE.userEntryToUser(userEntries);
    }

    @Override
    public List<User> getAllByIds(List<String> ids) {
        String uids = ids.stream().map(id -> "(uid=" + id + ")").reduce(" ", String::concat);
        LdapQuery query = LdapQueryBuilder.query()
                .base("ou=users")
                .filter("(|" + uids+ ")");
        List<UserEntry> userEntries = userLDAPRepository.findAll(query);

        return UserMapper.INSTANCE.userEntryToUser(userEntries);
    }

    @Override
    public User save(User user) {
        throw new ResourceNotFoundException();
    }

    @Override
    public User update(User user) {
        throw new ResourceNotFoundException();
    }

    @Override
    public void delete(String id) {
        throw new ResourceNotFoundException();
    }

    @Override
    public User getByUsername(String username) {
        UserEntry userEntry = userLDAPRepository.getByUsername(username).orElseThrow(UserNotFoundException::new);
        User user = UserMapper.INSTANCE.userEntryToUser(userEntry);
        user.setPassword(new String(userEntry.getPasswordByte()));
        return user;
    }
}
