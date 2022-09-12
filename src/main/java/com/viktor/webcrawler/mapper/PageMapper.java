package com.viktor.webcrawler.mapper;

import com.viktor.webcrawler.domain.Page;
import com.viktor.webcrawler.dto.PageDto;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring", uses = {HostMapper.class})
public interface PageMapper {

    PageDto toDto(Page entity);

    List<PageDto> toListDto(List<Page> entityList);
}
