package configurationProject.controller;

import java.util.List;

import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import configurationProject.model.typeOfComponent.TypeOfComponent;
import configurationProject.model.typeOfComponent.ValueOfComponent;
import configurationProject.service.typeOfComponent.TypeOfComponentService;

@Controller
public class TypeOfComponentController {

	private TypeOfComponentService typeOfComponentService;
	private static final Logger logger = LoggerFactory.getLogger(TypeOfComponentController.class);

	@Autowired
	public void setTypeOfComponentService(TypeOfComponentService typeOfComponentService) {
		this.typeOfComponentService = typeOfComponentService;
	}

	@RequestMapping(value = "addTypeOfComponent", method = RequestMethod.GET)
	public String addTypeOfComponent(ModelMap modelMap) {

		boolean checkAuthority = hasRole();
		if (checkAuthority) {
				modelMap.addAttribute("addTypeOfComponent", new TypeOfComponent());
				modelMap.addAttribute("valueOfComponent", ValueOfComponent.values());
				return "addTypeOfComponent";
			
		}
		return "403";
	}

	@RequestMapping(value = "addTypeOfComponent", method = RequestMethod.POST)
	public String addTypeOfComponentPost(@ModelAttribute("addTypeOfComponent") @Valid TypeOfComponent typeOfComponent,
			BindingResult result, ModelMap modelMap) {
		String returnResult = "addTypeOfComponent";
		boolean checkAuthority = hasRole();
		if (checkAuthority) {
			if (!result.hasErrors()) {
				try {
					typeOfComponentService.addTypeOfComponent(typeOfComponent);
				} catch (ConstraintViolationException ex) {
					result.rejectValue("name", "nameExist", "Name already exist");
					return returnResult;
				}
				logger.info("type of component " + typeOfComponent.getName() + " added successfully");
				returnResult = "redirect:typeOfComponentList";
			} else {
				modelMap.addAttribute("addTypeOfComponent", typeOfComponent);
				modelMap.addAttribute("valueOfComponent", ValueOfComponent.values());
			}
			return returnResult;

		}
		return "403";
	}

	@RequestMapping(value = "typeOfComponentList", method = RequestMethod.GET)
	public String getTypeOfComponentList(@RequestParam(required = false) Integer page,
			@RequestParam(required = false) String sorter, ModelMap modelMap) {
		logger.info("type of component list");
		boolean checkAuthority = hasRole();
		if (checkAuthority) {
			PagedListHolder<TypeOfComponent> pageListHolder = new PagedListHolder<TypeOfComponent>();
			modelMap.addAttribute("typeOfComponentList", typeOfComponentService.findAll());

			if (sorter != null) {
				String[] spliter = sorter.split("/");
				String sortColumn = spliter[0];
				String descriptior = spliter[1];
				List<TypeOfComponent> typeOfComponentsSorted = typeOfComponentService.sortBy(sortColumn, descriptior);
				pageListHolder = getPageListHolder(typeOfComponentsSorted, modelMap, page);
			} else {
				pageListHolder = getPageListHolder(typeOfComponentService.findAll(), modelMap, page);
			}
			modelMap.addAttribute("sorter", sorter);
			modelMap.addAttribute("page", pageListHolder.getPage());
			modelMap.addAttribute("pageListHolder", pageListHolder);
			return "typeOfComponentList";

		}
		return "403";
	}

	private PagedListHolder<TypeOfComponent> getPageListHolder(List<TypeOfComponent> typeOfComponent, ModelMap modelMap,
			Integer page) {
		PagedListHolder<TypeOfComponent> pageListHolder = new PagedListHolder<TypeOfComponent>(typeOfComponent);
		pageListHolder.setPageSize(5);

		modelMap.addAttribute("maxPages", pageListHolder.getPageCount());
		if (page == null || page <= 1 || page > pageListHolder.getPageCount()) {
			page = 0;
			pageListHolder.setPage(page);
		} else {
			pageListHolder.setPage(page - 1);
		}
		return pageListHolder;
	}

	@RequestMapping(value = "updateType/{id}", method = RequestMethod.GET)
	public String updateTypeGet(@PathVariable Long id, ModelMap modelMap) {
		boolean checkAuthority = hasRole();
		if (checkAuthority) {
			TypeOfComponent typeOfComponent = typeOfComponentService.findTypeOfComponentById(id);
			modelMap.addAttribute("updateType", typeOfComponent);
			return "updateType";

		}
		return "403";
	}

	@RequestMapping(value = "updateType/{id}", method = RequestMethod.POST)
	public String updateType(@ModelAttribute("updateType") @Valid TypeOfComponent typeOfComponent, BindingResult result,
			@PathVariable Long id, ModelMap modelMap) {
		typeOfComponent.setId(id);
		boolean checkAuthority = hasRole();
		if (checkAuthority) {
			if (!result.hasErrors()) {
				typeOfComponentService.updateTypeOfComponent(typeOfComponent);
				logger.info("type of component " + typeOfComponent.getName() + " updated successfully");
				return "redirect:../typeOfComponentList";
			} else {
				modelMap.addAttribute("updateType", typeOfComponent);
				return "updateType";
			}

		}
		return "403";
	}

	@RequestMapping(value = "removeType/{id}", method = RequestMethod.POST)
	public String removeType(@PathVariable Long id, ModelMap modelMap) {
		TypeOfComponent typeOfComponent = typeOfComponentService.findTypeOfComponentById(id);
		try {
			boolean checkAuthority = hasRole();
			if (checkAuthority) {
				typeOfComponentService.delete(typeOfComponent.getId());
			}
			return "redirect:../typeOfComponentList";

		} catch (DataIntegrityViolationException ex) {
			
		}

		return "403";
	}

	private boolean hasRole() {
		SecurityContext context = SecurityContextHolder.getContext();
		if (context == null)
			return false;

		Authentication authentication = context.getAuthentication();
		if (authentication == null)
			return false;

		for (GrantedAuthority auth : authentication.getAuthorities()) {
			if ((auth.getAuthority()).equals("ROLE_ADMIN")) {
				return true;
			}
		}

		return false;
	}

}
