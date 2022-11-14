package stingify.app.macrocategories;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import stingify.app.dto.CategoryDTO;
import stingify.app.dto.MacroCategoryDTO;
import stingify.app.entity.MacroCategory;
import stingify.app.mapper.CategoryMapper;
import stingify.app.mapper.MacroCategoryMapper;
import stingify.app.repository.BudgetBooksRepository;
import stingify.app.repository.CategoryRepository;
import stingify.app.repository.MacroCategoryRepository;

@Service
public class MacroCategoryService {

	@Autowired
	private MacroCategoryRepository macroCategoryRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
 	private BudgetBooksRepository budgetBooksRepository;
 
	@Autowired
	private MacroCategoryMapper macroCategoryMapper;
	
	@Autowired
	private CategoryMapper categoryMapper;
	
	public List<MacroCategoryDTO> getAllMacroCategorys() {
		List<MacroCategory> utenti = new ArrayList<>();
		macroCategoryRepository.findAll().forEach(utenti::add);
		return macroCategoryMapper.toDto(utenti);
	}

	public MacroCategoryDTO getMacroCategory(Integer id) {
		Optional<MacroCategory> macroCategoryOptional = macroCategoryRepository.findById(id);
		if (macroCategoryOptional.isPresent()) {
			return macroCategoryMapper.toDto(macroCategoryOptional.get());
		} else {
			return null;
		}
	}
	
	
	public List<MacroCategoryDTO> getMacroCategoriesByUserId(Integer userId) {
		return macroCategoryMapper.toDto(macroCategoryRepository.getCategoriesByUserId(userId));
	}
	
	public List<MacroCategoryDTO> getMacroCategoriesByBudgetBookId(Integer budgetBookId) {
		List<MacroCategoryDTO> macroCategoryWithCategoryInBudgetBook = macroCategoryMapper.toDto(macroCategoryRepository.getMacroCategoriesByBudgetBookId(budgetBookId));
		List<CategoryDTO> budgetBookCategories = categoryMapper.toDto(budgetBooksRepository.findCategoriesByBudgetBookId(budgetBookId));
		macroCategoryWithCategoryInBudgetBook.forEach(mc -> 
			mc.setCategories(budgetBookCategories.stream()
					.filter(c -> c.getMacroCategory() != null
							&& c.getMacroCategory().getMacroCategoryId().equals(mc.getMacroCategoryId()))
					.collect(Collectors.toList()))
		);
		return macroCategoryWithCategoryInBudgetBook;
	}

	public MacroCategoryDTO addMacroCategory(MacroCategoryDTO macroCategory) {
		macroCategory.setInsertionTimestamp(new Timestamp(new Date().getTime()));
		MacroCategoryDTO addedMacroCategoryDTO = macroCategoryMapper.toDto(macroCategoryRepository.save(macroCategoryMapper.toEntity(macroCategory)));
		if (macroCategory.getCategories() != null && !macroCategory.getCategories().isEmpty()) {			
			for (CategoryDTO categoryDTO : macroCategory.getCategories()) {
				categoryDTO.setMacroCategory(addedMacroCategoryDTO);
				categoryMapper.toDto(categoryRepository.save(categoryMapper.toEntity(categoryDTO)));
			}
		}
		return this.getMacroCategory(addedMacroCategoryDTO.getMacroCategoryId());
	}

	public MacroCategoryDTO updateMacroCategory(MacroCategoryDTO macroCategory) {
		macroCategory.setChangeTimestamp(new Timestamp(new Date().getTime()));
		MacroCategoryDTO updatedMacroCategoryDTO = macroCategoryMapper.toDto(macroCategoryRepository.save(macroCategoryMapper.toEntity(macroCategory)));
		if (macroCategory.getCategories() != null && !macroCategory.getCategories().isEmpty()) {			
			for (CategoryDTO categoryDTO : macroCategory.getCategories()) {
				categoryMapper.toDto(categoryRepository.save(categoryMapper.toEntity(categoryDTO)));
			}
		}
		return this.getMacroCategory(updatedMacroCategoryDTO.getMacroCategoryId());		
	}

	public void deleteMacroCategory(MacroCategoryDTO macroCategory) {
		Timestamp currentTimestamp = new Timestamp(new Date().getTime());
		macroCategory.setCancellationTimestamp(currentTimestamp);
		macroCategory.setChangeTimestamp(currentTimestamp);
		 macroCategoryRepository.save(macroCategoryMapper.toEntity(macroCategory));
	}

}
