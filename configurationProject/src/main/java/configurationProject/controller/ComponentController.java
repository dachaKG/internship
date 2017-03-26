package configurationProject.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
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

import configurationProject.model.component.Component;
import configurationProject.model.typeOfComponent.TypeOfComponent;
import configurationProject.service.component.ComponentService;
import configurationProject.service.typeOfComponent.TypeOfComponentService;

@Controller
public class ComponentController {

	private ComponentService componentService;
	private TypeOfComponentService typeOfComponentService;

	private static final Logger logger = LoggerFactory.getLogger(ComponentController.class);

	@Autowired
	public void setTypeOfComponentService(TypeOfComponentService typeOfComponentService) {
		this.typeOfComponentService = typeOfComponentService;
	}

	@Autowired
	public void setComponentService(ComponentService componentService) {
		this.componentService = componentService;
	}

	//Id predstavlja filter
	@RequestMapping(value = "componentList", method = RequestMethod.GET)
	public String componentList(@RequestParam(required = false) Integer page,
			@RequestParam(required = false) String sorter, @RequestParam(required = false) Long id,
			@RequestParam(required = false) Long delete, ModelMap modelMap) {

		boolean checkAuthority = hasRole();
		if (checkAuthority) {
				PagedListHolder<Component> pageListHolder = new PagedListHolder<Component>();
				modelMap.addAttribute("filterComponent", new TypeOfComponent());

				if (delete != null) {
					deleteComponent(delete, modelMap, pageListHolder, page);
				} else if (sorter != null) {
					addSorter(sorter, modelMap, page, pageListHolder, id);
				} else if (id != null && id == 0) {
					pageListHolder = getPageListHolder(componentService.findAll(), modelMap, page);
				} else if (id != null) {
					addFilter(id, modelMap, pageListHolder, page);
				} else {
					pageListHolder = getPageListHolder(componentService.findAll(), modelMap, page);
				}
				modelMap.addAttribute("sorter", sorter);
				modelMap.addAttribute("typeFilter", id);
				modelMap.addAttribute("typeOfComponent", typeOfComponentService.findAll());
				logger.info("component list, page " + pageListHolder.getPage());
				modelMap.addAttribute("page", pageListHolder.getPage());
				modelMap.addAttribute("pageListHolder", pageListHolder);
				return "componentList";

			
		}
		return "403";
	}	
	
	private void addFilter(Long id, ModelMap modelMap, PagedListHolder<Component> pageListHolder, Integer page){

		TypeOfComponent typeOfComponent = typeOfComponentService.findTypeOfComponentById(id);
		List<Component> componentsList = new ArrayList<Component>();

		for (int i = 0; i < componentService.findAll().size(); i++) {
			if (componentService.findAll().get(i).getTypeOfComponent().getId() == id) {
				componentsList.add(componentService.findAll().get(i));
			}
		}
		pageListHolder = getPageListHolder(componentsList, modelMap, page);
		modelMap.addAttribute("filterComponent", typeOfComponent);
	
	}
	
	private void addSorter(String sorter, ModelMap modelMap, Integer page, PagedListHolder<Component> pageListHolder, Long id){

		String[] spliter = sorter.split("/");
		String columnSort = spliter[0];
		String descriptor = spliter[1];
		List<Component> componentsSorted = componentService.sortBy(columnSort, descriptor);
		pageListHolder = getPageListHolder(componentsSorted, modelMap, page);
		modelMap.addAttribute("components", componentsSorted);
		if (id != null && id == 0) {
			pageListHolder = getPageListHolder(componentsSorted, modelMap, page);
		} else if (id != null) {
			TypeOfComponent typeOfComponent = typeOfComponentService.findTypeOfComponentById(id);
			List<Component> componentsList = new ArrayList<Component>();

			for (int i = 0; i < componentService.findAll().size(); i++) {
				if (componentService.findAll().get(i).getTypeOfComponent().getId() == id) {
					componentsList.add(componentService.findAll().get(i));
				}
			}
			pageListHolder = getPageListHolder(componentsList, modelMap, page);
			if (sorter != null) {
				List<Component> componentsFilterSorted = new ArrayList<Component>();
				for (int i = 0; i < componentsSorted.size(); i++) {
					for (int j = 0; j < componentsList.size(); j++) {
						if (componentsList.get(j).getId() == componentsSorted.get(i).getId()) {
							componentsFilterSorted.add(componentsList.get(j));
						}
					}
				}
				pageListHolder = getPageListHolder(componentsFilterSorted, modelMap, page);
			}
			modelMap.addAttribute("filterComponent", typeOfComponent);

		}
	
	}
	
	private void deleteComponent(Long delete, ModelMap modelMap, PagedListHolder<Component> pageListHolder, Integer page){

		Component component = componentService.findComponentById(delete);
		if (component.getStock() == 0) {
			componentService.deleteComponent(delete);
			modelMap.addAttribute("checkStock", "");
			pageListHolder = getPageListHolder(componentService.findAll(), modelMap, page);
		} else {
			modelMap.addAttribute("checkStock", "you can't remove this component");
			pageListHolder = getPageListHolder(componentService.findAll(), modelMap, page);
		}
	
	}

	private PagedListHolder<Component> getPageListHolder(List<Component> components, ModelMap modelMap, Integer page) {
		PagedListHolder<Component> pageListHolder = new PagedListHolder<Component>(components);
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

	@RequestMapping(value = "addComponent", method = RequestMethod.GET)
	public String addComponentGet(ModelMap modelMap) {
		boolean checkAuthority = hasRole();
		if (checkAuthority) {
				modelMap.addAttribute("addComponent", new Component());
				modelMap.addAttribute("typeOfComponent", typeOfComponentService.findAll());
				return "addComponent";
			
		}

		return "403";
	}

	@RequestMapping(value = "addComponent", method = RequestMethod.POST)
	public String addComponent(@ModelAttribute("addComponent") @Valid Component component, BindingResult result,
			ModelMap modelMap) {
		String returnResult = "addComponent";
		boolean checkAuthority = hasRole();
		if (checkAuthority) {
			if (!result.hasErrors()) {
				try {
					this.componentService.save(component);
				} catch (ConstraintViolationException ex) {
					result.rejectValue("code", "codeExist", "Code already exist");
					modelMap.addAttribute("addComponent", component);
					modelMap.addAttribute("typeOfComponent", typeOfComponentService.findAll());
					return returnResult;
				}
				logger.info("you added component " + component.getCode() + " successfully ");
				return "redirect:componentList";
			} else {

				modelMap.addAttribute("addComponent", component);
				modelMap.addAttribute("typeOfComponent", typeOfComponentService.findAll());
				return returnResult;
			}

		}
		return "403";
	}

	@RequestMapping(value = "updateComponent/{id}", method = RequestMethod.GET)
	public String updateComponentGet(@PathVariable Long id, ModelMap modelMap) {
		boolean checkAuthority = hasRole();
		if (checkAuthority) {
			Component component = componentService.findComponentById(id);
			modelMap.addAttribute("updateComponent", component);

			return "updateComponent";

		}
		return "403";
	}

	@RequestMapping(value = "updateComponent/{id}", method = RequestMethod.POST)
	public String updateComponent(@ModelAttribute("updateComponent") @Valid Component component, BindingResult result,
			@PathVariable Long id, ModelMap modelMap) {
		component.setId(id);
		boolean checkAuthority = hasRole();
		if (checkAuthority) {
			if (!result.hasErrors()) {
				componentService.updateComponent(component);
				logger.info("update component  " + component.getCode() + " successfully");
				return "redirect:../componentList";
			} else {
				modelMap.addAttribute("updateComponent", component);
				return "updateComponent";
			}

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
			if ((auth.getAuthority()).equals("ROLE_ADMIN") || auth.getAuthority().equals("ROLE_WAREHOUSECLERK")) {
				return true;
			}
		}
		return false;
	}

}
