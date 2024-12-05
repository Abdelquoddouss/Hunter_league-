package com.abdo.hunter.web.vm.mapper;

import com.abdo.hunter.domain.entity.Competition;
import com.abdo.hunter.web.vm.request.CompetitionRequest;
import com.abdo.hunter.web.vm.response.CompetitionResponse;
import com.abdo.hunter.web.vm.response.CompetitionResultsResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompetitionVmMapper {

    Competition toCompetition(CompetitionRequest competitionRequest);


    CompetitionResponse toCompetitionResponse(Competition competition);

    List<CompetitionResultsResponse> toCompetitionResultsResponse(List<Competition> competitions);





}
