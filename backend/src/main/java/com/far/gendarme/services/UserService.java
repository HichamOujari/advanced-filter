package com.far.gendarme.services;

import com.far.gendarme.models.*;
import com.far.gendarme.requests.FilterRequest;
import com.far.gendarme.responses.UserResponse;

import java.util.List;

public interface UserService {
    public List<User> getUserAndFilter(FilterRequest filterRequest);
}
