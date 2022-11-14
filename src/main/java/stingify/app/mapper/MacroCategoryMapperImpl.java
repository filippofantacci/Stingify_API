package stingify.app.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import stingify.app.dto.MacroCategoryDTO;
import stingify.app.entity.Category;
import stingify.app.entity.MacroCategory;

@Component
public class MacroCategoryMapperImpl implements MacroCategoryMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CategoryMapper categoryMapper;


	@Override
	public MacroCategoryDTO toDto(MacroCategory macroCategory) {

		if (macroCategory == null) {
			return null;
		}
		List<Category> categories = new ArrayList<>();
		if (macroCategory.getCategories() != null) {
			categories = macroCategory.getCategories();
			macroCategory.setCategories(new ArrayList<>(categories.size()));			
		} else {
			macroCategory.setCategories(new ArrayList<>(0));
		}
		
		

		MacroCategoryDTO macroCategoryDTO = modelMapper.map(macroCategory, MacroCategoryDTO.class);
		macroCategoryDTO.setInsertionTimestamp(macroCategory.getInsertionTimestamp());
		macroCategoryDTO.setChangeTimestamp(macroCategory.getChangeTimestamp());
		macroCategoryDTO.setCancellationTimestamp(macroCategory.getCancellationTimestamp());
		
		for (Category category : categories) {
			macroCategoryDTO.getCategories().add(categoryMapper.toDtoNoMacroCategory(category));
		}
		
		return macroCategoryDTO;
	}

	@Override
	public List<MacroCategoryDTO> toDto(List<MacroCategory> macroCategoryList) {
		if (macroCategoryList == null) {
			return new ArrayList<>();
		}

		List<MacroCategoryDTO> macroCategoryDTOList = new ArrayList<>(macroCategoryList.size());

		for (MacroCategory macroCategory : macroCategoryList) {
			macroCategoryDTOList.add(toDto(macroCategory));
		}

		return macroCategoryDTOList;
	}

	@Override
	public MacroCategory toEntity(MacroCategoryDTO macroCategoryDTO) {

		if (macroCategoryDTO == null) {
			return null;
		}

		MacroCategory macroCategory = modelMapper.map(macroCategoryDTO, MacroCategory.class);
		macroCategory.setInsertionTimestamp(macroCategoryDTO.getInsertionTimestamp());
		macroCategory.setChangeTimestamp(macroCategoryDTO.getChangeTimestamp());
		macroCategory.setCancellationTimestamp(macroCategoryDTO.getCancellationTimestamp());
		
		return macroCategory;
	}

}
