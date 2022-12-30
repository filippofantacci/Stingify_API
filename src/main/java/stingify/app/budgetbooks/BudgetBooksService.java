package stingify.app.budgetbooks;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import stingify.app.dto.BudgetBookDTO;
import stingify.app.dto.BudgetBooksCategoriesDTO;
import stingify.app.dto.CategoryDTO;
import stingify.app.dto.RecurringAmountDTO;
import stingify.app.dto.UserBudgetBooksDTO;
import stingify.app.entity.Amount;
import stingify.app.entity.BudgetBook;
import stingify.app.entity.BudgetBooksCategories;
import stingify.app.entity.User;
import stingify.app.entity.UserBudgetBooks;
import stingify.app.mapper.AmountTypeMapper;
import stingify.app.mapper.BudgetBookMapper;
import stingify.app.mapper.BudgetBooksCategoriesMapper;
import stingify.app.mapper.CategoryMapper;
import stingify.app.mapper.UserBudgetBooksMapper;
import stingify.app.repository.AmountRepository;
import stingify.app.repository.BudgetBooksCategoriesRepository;
import stingify.app.repository.BudgetBooksRepository;
import stingify.app.repository.UserBudgetBooksRepository;

@Service
public class BudgetBooksService {

	@Autowired
	private BudgetBooksRepository budgetBooksRepository;

	@Autowired
	private UserBudgetBooksRepository userBudgetBooksRepository;

	@Autowired
	private BudgetBooksCategoriesRepository budgetBooksCategoriesRepository;
	
	@Autowired
	private AmountRepository amountRepository;

	@Autowired
	private BudgetBookMapper budgetBookMapper;

	@Autowired
	private UserBudgetBooksMapper userBudgetBooksMapper;

	@Autowired
	private CategoryMapper categoryMapper;
	
	@Autowired
	private AmountTypeMapper amountTypeMapper;

	@Autowired
	private BudgetBooksCategoriesMapper budgetBooksCategoriesMapper;

	public List<BudgetBookDTO> getAllBudgetBooks() {
		List<BudgetBook> utenti = new ArrayList<>();
		budgetBooksRepository.findAll().forEach(utenti::add);
		return budgetBookMapper.toDto(utenti);
	}

	public BudgetBookDTO getBudgetBook(Integer id) {
		Optional<BudgetBook> budgetBookOptional = budgetBooksRepository.findById(id);
		if (budgetBookOptional.isPresent()) {
			return budgetBookMapper.toDto(budgetBookOptional.get());
		} else {
			return null;
		}
	}

	public Page<BudgetBookDTO> getAllUserBudgetBooksByUserId(Integer userId, Pageable pageable) {
		return budgetBooksRepository.findByUserId(userId, pageable).map(budgetBookMapper::toDto);
	}

	public List<CategoryDTO> getAllBudgetBooksCategories(Integer budgetBookId) {
		return categoryMapper.toDto(budgetBooksRepository.findCategoriesByBudgetBookId(budgetBookId));
	}

	public BudgetBookDTO addBudgetBook(BudgetBookDTO budgetBookDTO) {
		// create the budget book
		budgetBookDTO.setInsertionTimestamp(new Timestamp(new Date().getTime()));
		BudgetBook addedBudgetBook = budgetBooksRepository.save(budgetBookMapper.toEntity(budgetBookDTO));
		// create the associatione within the user and the budget book
		User creatorUser = new User();
		creatorUser.setUserId(addedBudgetBook.getCreatorUserId());
		UserBudgetBooks userBudgetBooks = new UserBudgetBooks();
		userBudgetBooks.setUserId(addedBudgetBook.getCreatorUserId());
		userBudgetBooks.setBudgetBookId(addedBudgetBook.getBudgetBookId());
		userBudgetBooks.setInsertionTimestamp(addedBudgetBook.getInsertionTimestamp());
		userBudgetBooksRepository.save(userBudgetBooks);
		// create the asociations within the budget book and the categories
		if (budgetBookDTO.getCategories() != null) {
			for (CategoryDTO categoryDTO : budgetBookDTO.getCategories()) {
				BudgetBooksCategoriesDTO budgetBooksCategoriesDTO = new BudgetBooksCategoriesDTO();
				budgetBooksCategoriesDTO.setBudgetBookId(addedBudgetBook.getBudgetBookId());
				budgetBooksCategoriesDTO.setCategoryId(categoryDTO.getCategoryId());
				budgetBooksCategoriesDTO.setInsertionTimestamp(addedBudgetBook.getInsertionTimestamp());
				budgetBooksCategoriesRepository.save(budgetBooksCategoriesMapper.toEntity(budgetBooksCategoriesDTO));
			}
		}
		// get the created categories
		BudgetBookDTO addedbuBookDTO = budgetBookMapper.toDto(addedBudgetBook);
		addedbuBookDTO.setCategories(this.getAllBudgetBooksCategories(addedbuBookDTO.getBudgetBookId()));
		// insert the recurring amounts
		if (budgetBookDTO.getRecurringAmounts() != null && !budgetBookDTO.getRecurringAmounts().isEmpty()) {
			for (RecurringAmountDTO recurringAmount: budgetBookDTO.getRecurringAmounts()) {
				
			Amount amount = new Amount();
			amount.setRecurringAmountId(recurringAmount.getRecurringAmountId());
			amount.setAmountType(amountTypeMapper.toEntity(recurringAmount.getAmountType()));
			amount.setCategory(categoryMapper.toEntity(recurringAmount.getCategory()));
			amount.setDescription(recurringAmount.getDescription());
			amount.setPlanned(recurringAmount.getPlanned());
			amount.setActual(recurringAmount.getActual());
			amount.setCreatorUserId(recurringAmount.getCreatorUserId());
			amount.setBudgetBookId(addedbuBookDTO.getBudgetBookId());
			amount.setDate(new Date());
			amount.setInsertionTimestamp(addedbuBookDTO.getInsertionTimestamp());
			amount.setChangeTimestamp(addedbuBookDTO.getChangeTimestamp());
			amount.setCancellationTimestamp(addedbuBookDTO.getCancellationTimestamp());
			
			this.amountRepository.save(amount);
			}
		}
		return addedbuBookDTO;
	}

	public BudgetBookDTO addUserBudgetBooks(UserBudgetBooksDTO userBudgetBookDTO) {
		userBudgetBookDTO.setInsertionTimestamp(new Timestamp(new Date().getTime()));
		UserBudgetBooks addedUserBudgetBooks = userBudgetBooksRepository
				.save(userBudgetBooksMapper.toEntity(userBudgetBookDTO));
		return this.getBudgetBook(addedUserBudgetBooks.getBudgetBookId());
	}

	public List<CategoryDTO> addBudgetBooksCategory(BudgetBooksCategoriesDTO budgetBooksCategoriesDTO) {
		budgetBooksCategoriesDTO.setInsertionTimestamp(new Timestamp(new Date().getTime()));
		budgetBooksCategoriesRepository.save(budgetBooksCategoriesMapper.toEntity(budgetBooksCategoriesDTO));
		return this.getAllBudgetBooksCategories(budgetBooksCategoriesDTO.getBudgetBookId());
	}

	public BudgetBookDTO updateBudgetBook(BudgetBookDTO budgetBookDTO) {
		budgetBookDTO.setChangeTimestamp(new Timestamp(new Date().getTime()));
		BudgetBookDTO updatedBudgetBookDTO = budgetBookMapper
				.toDto(budgetBooksRepository.save(budgetBookMapper.toEntity(budgetBookDTO)));

		List<CategoryDTO> categoriesToAdd = new ArrayList<>();
		List<CategoryDTO> categoriesToRemove;
		// get budget book categories
		List<CategoryDTO> alreadyExistingCategories = this
				.getAllBudgetBooksCategories(updatedBudgetBookDTO.getBudgetBookId());

		if (budgetBookDTO.getCategories() != null && !budgetBookDTO.getCategories().isEmpty()) {
			// if input category not present in budget book categories => insert
			categoriesToAdd = budgetBookDTO.getCategories().stream()
					.filter(categoryInput -> !alreadyExistingCategories.contains(categoryInput))
					.collect(Collectors.toList());
			// if budget book categories not present in input categories => remove
			categoriesToRemove = alreadyExistingCategories.stream()
					.filter(alreadyExistingCategory -> !budgetBookDTO.getCategories().contains(alreadyExistingCategory))
					.collect(Collectors.toList());
		} else {
			// remove every category
			categoriesToRemove = alreadyExistingCategories;
		}

		// add
		for (CategoryDTO categoryToAdd : categoriesToAdd) {
			BudgetBooksCategoriesDTO budgetBooksCategoriesDTO = new BudgetBooksCategoriesDTO();
			budgetBooksCategoriesDTO.setBudgetBookId(updatedBudgetBookDTO.getBudgetBookId());
			budgetBooksCategoriesDTO.setCategoryId(categoryToAdd.getCategoryId());
			budgetBooksCategoriesDTO.setInsertionTimestamp(updatedBudgetBookDTO.getChangeTimestamp());
			budgetBooksCategoriesRepository.save(budgetBooksCategoriesMapper.toEntity(budgetBooksCategoriesDTO));
		}
		// remove
		for (CategoryDTO categoryToRemove : categoriesToRemove) {
			BudgetBooksCategories budgetBooksCategories = budgetBooksCategoriesRepository
					.findByBudgetBookIdAndCategoryId(updatedBudgetBookDTO.getBudgetBookId(),
							categoryToRemove.getCategoryId());
			budgetBooksCategoriesRepository.delete(budgetBooksCategories);
		}
		updatedBudgetBookDTO.setCategories(this.getAllBudgetBooksCategories(updatedBudgetBookDTO.getBudgetBookId()));
		return updatedBudgetBookDTO;
	}

	public void deleteBudgetBook(BudgetBookDTO budgetBookDTO) {
		Timestamp currentTimestamp = new Timestamp(new Date().getTime());
		budgetBookDTO.setCancellationTimestamp(currentTimestamp);
		budgetBookDTO.setChangeTimestamp(currentTimestamp);
		budgetBooksRepository.save(budgetBookMapper.toEntity(budgetBookDTO));
	}

	public void deleteUserBudgetBooks(UserBudgetBooksDTO userBudgetBookDTO) throws Exception {
		UserBudgetBooks userBudgetBooks = userBudgetBooksRepository
				.findByUserIdAndBudgetBookId(userBudgetBookDTO.getUserId(), userBudgetBookDTO.getBudgetBookId());
		if (userBudgetBooks != null) {
			userBudgetBooksRepository.delete(userBudgetBooks);
		} else {
			throw new Exception("UserBudgetBooks {} not found" + userBudgetBookDTO);
		}
	}

	public void deleteBudgetBooksCategory(BudgetBooksCategoriesDTO budgetBooksCategoriesDTO) throws Exception {
		BudgetBooksCategories budgetBooksCategories = budgetBooksCategoriesRepository.findByBudgetBookIdAndCategoryId(
				budgetBooksCategoriesDTO.getBudgetBookId(), budgetBooksCategoriesDTO.getCategoryId());
		if (budgetBooksCategories != null) {

			budgetBooksCategoriesRepository.delete(budgetBooksCategories);
		} else {
			throw new Exception("BudgetBooksCategories {} not found" + budgetBooksCategoriesDTO);
		}
	}

}
