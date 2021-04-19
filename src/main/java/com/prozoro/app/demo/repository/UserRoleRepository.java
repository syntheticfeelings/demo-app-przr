package com.prozoro.app.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Repository
public class UserRoleRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    final String GET_USER = "SELECT name, password FROM app_user WHERE name = ?";
    final String GET_USER_ROLE = "SELECT r.role FROM app_user_role ur " +
                                 "JOIN app_role r ON ur.role_id = r.id " +
                                 "JOIN app_user u ON ur.user_id = u.id " +
                                 "WHERE u.name= ?";


    public UserDetails loadUserByUserName(String username) {
        List<String> roles = getUserRoles(username);

        if (!CollectionUtils.isEmpty(roles)) {
            return getUser(username, roles);
        }

        throw new UsernameNotFoundException("User " + username + " cannot be found");
    }

    private UserDetails getUser(String username, List<String> roles) {
        return jdbcTemplate.queryForObject(GET_USER, (rs, i) -> {
            return User.builder()
                    .username(rs.getString("name"))
                    .password(rs.getString("password"))
                    .authorities(roles.toArray(new String[roles.size()]))
                    .build();
        } , username);
    }


    private List<String> getUserRoles(String name) {
        return jdbcTemplate.queryForList(GET_USER_ROLE, String.class, name);
    }

}
