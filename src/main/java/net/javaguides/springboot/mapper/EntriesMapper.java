package net.javaguides.springboot.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import net.javaguides.springboot.model.EntriesDBO;
import net.javaguides.springboot.model.EntriesDTO;

@Mapper(componentModel = "spring")
public interface EntriesMapper {
	public EntriesMapper MAPPER = Mappers.getMapper(EntriesMapper.class);

	    List<EntriesDTO> mapToEntriesDTOList(List<EntriesDBO> entriesDboList);

	    List<EntriesDBO> mapToEntriesDBOList(List<EntriesDTO> entriesDtoList);
	    
	    EntriesDTO mapToEntriesDTO(EntriesDBO entriesDboList);

	    EntriesDBO mapToEntriesDBO(EntriesDTO entriesDtoList);

}
