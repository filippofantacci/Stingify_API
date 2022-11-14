package stingify.app.categories;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import stingify.app.dto.CategoryDTO;
import stingify.app.entity.Category;
import stingify.app.mapper.CategoryMapper;
import stingify.app.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private CategoryMapper categoryMapper;

	public List<CategoryDTO> getAllCategorys() {
		List<Category> utenti = new ArrayList<>();
		categoryRepository.findAll().forEach(utenti::add);
		return categoryMapper.toDto(utenti);
	}

	public CategoryDTO getCategory(Integer id) {
		Optional<Category> categoryOptional = categoryRepository.findById(id);
		if (categoryOptional.isPresent()) {
			return categoryMapper.toDtoNoMacroCategory(categoryOptional.get());
		} else {
			return null;
		}
	}

	public List<CategoryDTO> getCategoriesByUserId(Integer userId, boolean unused) {
		if (unused) {
			return categoryMapper.toDto(categoryRepository.getUnusedCategoriesByUserId(userId));
		} else {			
			return categoryMapper.toDto(categoryRepository.getCategoriesByUserId(userId));
		}
	}
	
	public CategoryDTO addCategory(CategoryDTO category) {
		category.setInsertionTimestamp(new Timestamp(new Date().getTime()));
		return categoryMapper.toDto(categoryRepository.save(categoryMapper.toEntity(category)));
	}

	public CategoryDTO updateCategory(CategoryDTO category) {
		category.setChangeTimestamp(new Timestamp(new Date().getTime()));
		return categoryMapper.toDto(categoryRepository.save(categoryMapper.toEntity(category)));
	}

	public void deleteCategory(CategoryDTO category) {
		Timestamp currentTimestamp = new Timestamp(new Date().getTime());
		category.setCancellationTimestamp(currentTimestamp);
		category.setChangeTimestamp(currentTimestamp);
		categoryRepository.save(categoryMapper.toEntity(category));
	}

}
