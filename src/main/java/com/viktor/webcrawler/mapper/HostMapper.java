package com.viktor.webcrawler.mapper;

import com.viktor.webcrawler.domain.Host;
import com.viktor.webcrawler.dto.HostDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HostMapper {

    HostDto toDto(Host host);
}
