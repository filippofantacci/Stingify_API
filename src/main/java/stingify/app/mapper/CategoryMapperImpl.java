package stingify.app.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import stingify.app.dto.CategoryDTO;
import stingify.app.entity.Category;

@Component
public class CategoryMapperImpl implements CategoryMapper {

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDTO toDto(Category category) {

		if (category == null) {
			return null;
		}

		if (category.getMacroCategory() != null) {
			category.getMacroCategory().setCategories(new ArrayList<>(0));
		}

		CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);
		categoryDTO.setInsertionTimestamp(category.getInsertionTimestamp());
		categoryDTO.setChangeTimestamp(category.getChangeTimestamp());
		categoryDTO.setCancellationTimestamp(category.getCancellationTimestamp());

		return categoryDTO;
	}

	@Override
	public CategoryDTO toDtoNoMacroCategory(Category category) {

		if (category == null) {
			return null;
		}
		if (category.getMacroCategory() != null) {
			category.getMacroCategory().setCategories(new ArrayList<>(0));
		}
		return toDto(category);

	}

	@Override
	public List<CategoryDTO> toDto(List<Category> categoryList) {
		if (categoryList == null) {
			return new ArrayList<>();
		}

		List<CategoryDTO> categoryDTOList = new ArrayList<>(categoryList.size());

		for (Category category : categoryList) {
			categoryDTOList.add(toDtoNoMacroCategory(category));
		}

		return categoryDTOList;
	}

	@Override
	public Category toEntity(CategoryDTO categoryDTO) {

		if (categoryDTO == null) {
			return null;
		}

		Category category = modelMapper.map(categoryDTO, Category.class);
		category.setInsertionTimestamp(categoryDTO.getInsertionTimestamp());
		category.setChangeTimestamp(categoryDTO.getChangeTimestamp());
		category.setCancellationTimestamp(categoryDTO.getCancellationTimestamp());

		return category;
	}

}
