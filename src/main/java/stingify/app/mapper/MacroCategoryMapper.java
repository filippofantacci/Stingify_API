package stingify.app.mapper;

import java.util.List;

import stingify.app.dto.MacroCategoryDTO;
import stingify.app.entity.MacroCategory;

public interface MacroCategoryMapper {

	public MacroCategoryDTO toDto(MacroCategory macroCategory);
	
	public List<MacroCategoryDTO> toDto(List<MacroCategory> macroCategoryList);
	
	public MacroCategory toEntity(MacroCategoryDTO macroCategoryDTO);
}
