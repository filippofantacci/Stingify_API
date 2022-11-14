package stingify.app.mapper;

import java.util.List;

import stingify.app.dto.CategoryDTO;
import stingify.app.entity.Category;

public interface CategoryMapper {

	public CategoryDTO toDto(Category category);
	
	public CategoryDTO toDtoNoMacroCategory(Category category);
	
	public List<CategoryDTO> toDto(List<Category> categoryList);
	
	public Category toEntity(CategoryDTO categoryDTO);
}
