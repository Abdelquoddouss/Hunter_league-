package com.abdo.hunter.web.vm.mapper;

import com.abdo.hunter.domain.entity.User;
import com.abdo.hunter.web.vm.request.LoginRequest;
import com.abdo.hunter.web.vm.request.UserRequest;
import com.abdo.hunter.web.vm.request.UserSearchRequest;
import com.abdo.hunter.web.vm.response.UserHistoryResponse;
import com.abdo.hunter.web.vm.response.UserResponse;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserVmMapper {



    User toUser(UserSearchRequest userSearchRequest);
    User toUser(LoginRequest loginRequest);
    User toUser(UserRequest userRequest);

    UserResponse toUserResponse(User user);
    List<UserResponse> toUsersResponceList(Page<User> users);
    List<UserHistoryResponse> toUserHistoryResponse(Page<User> users);

}

