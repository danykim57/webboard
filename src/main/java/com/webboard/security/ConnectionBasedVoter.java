package com.webboard.security;

import com.webboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

import static org.apache.commons.lang3.ClassUtils.isAssignable;

public class ConnectionBasedVoter implements AccessDecisionVoter<FilterInvocation> {

    private UserService userService;


    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return isAssignable(FilterInvocation.class, clazz);
    }

    @Override
    public int vote(Authentication authentication, FilterInvocation object, Collection<ConfigAttribute> attributes) {
        HttpServletRequest request = object.getRequest();
        return ACCESS_GRANTED;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
