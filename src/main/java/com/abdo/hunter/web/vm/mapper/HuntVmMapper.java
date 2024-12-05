package com.abdo.hunter.web.vm.mapper;

import com.abdo.hunter.domain.entity.Hunt;
import com.abdo.hunter.web.vm.request.HuntRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HuntVmMapper {



    Hunt toHunt(HuntRequest huntRequest);


}

