package configurationProject.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolationException;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import configurationProject.model.component.Component;
import configurationProject.model.configuration.Configuration;
import configurationProject.model.typeOfComponent.TypeOfComponent;
import configurationProject.model.typeOfComponent.ValueOfComponent;
import configurationProject.service.component.ComponentService;
import configurationProject.service.configuration.ConfigurationService;
import configurationProject.service.typeOfComponent.TypeOfComponentService;

@Controller
@SessionAttributes("componentList")
public class ConfigurationController {

	private ConfigurationService configurationService;
	private ComponentService componentService;
	private TypeOfComponentService typeOfComponentService;
	private static final Logger logger = LoggerFactory.getLogger(ConfigurationController.class);

	@Autowired
	public void setComponentService(ComponentService componentService) {
		this.componentService = componentService;
	}

	@Autowired
	public void setConfigurationService(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

	@Autowired
	public void setTypeOfComponentService(TypeOfComponentService typeOfComponentService) {
		this.typeOfComponentService = typeOfComponentService;
	}

	// -------------------------------------

	@RequestMapping(value = "soldConfigurationList", method = RequestMethod.GET)
	public String soldConfigurationListGet(@RequestParam(required = false) Integer page, ModelMap modelMap) {
		System.out.println(configurationService.findAll().size());
		boolean checkAuthority = hasRole();
		if (checkAuthority) {
			PagedListHolder<Configuration> pageListHolder = getPageListHolderConfiguration(
					configurationService.findAll(), modelMap, page);
			modelMap.addAttribute("pageListHolder", pageListHolder);
			logger.info("configuration list, page " + pageListHolder.getPage());
			modelMap.addAttribute("page", pageListHolder.getPage());
			return "soldConfigurationList";

		}
		return "403";
	}

	@RequestMapping(value = "makeConfiguration", method = RequestMethod.GET)
	public String makeConfigurationGet(@RequestParam(required = false) Integer page,
			@RequestParam(required = false) String filter, @RequestParam(required = false) Long add,
			@RequestParam(required = false) Integer remove, @RequestParam(required = false) String sorter,
			ModelMap modelMap, @ModelAttribute("componentList") List<Component> componentList) {

		boolean checkAuthority = hasRole();
		if (checkAuthority) {
			PagedListHolder<Component> pageListHolder = new PagedListHolder<Component>();
			pageListHolder = getPageListHolder(componentService.findAll(), modelMap, page);
			if (add != null) {
				addComponent(componentList, modelMap, add);
			}

			if (remove != null) {
				componentList.remove((int) remove);
			}

			if (sorter != null) {
				String[] spliter = sorter.split("/");
				String columnSort = spliter[0];
				String descriptor = spliter[1];
				List<Component> componentsSorted = componentService.sortBy(columnSort, descriptor);
				pageListHolder = getPageListHolder(componentsSorted, modelMap, page);
				modelMap.addAttribute("sorter", sorter);
			}

			if (filter != null && !filter.equals("all")) {
				addFilter(sorter, modelMap, pageListHolder, page, filter);
			}

			modelMap.addAttribute("page", pageListHolder.getPage());
			modelMap.addAttribute("pageListHolder", pageListHolder);
			modelMap.addAttribute("componentList", componentList);
			modelMap.addAttribute("total", getTotal(componentList));
			modelMap.addAttribute("typeOfComponents", typeOfComponentService.findAll());

			return "makeConfiguration";

		}
		return "403";
	}



	@RequestMapping(value = "saveConfiguration", method = RequestMethod.POST)
	public String saveConfiguration(ModelMap modelMap, @ModelAttribute("componentList") List<Component> componentList) {
		boolean checkAuthority = hasRole();
		if (checkAuthority) {
			Configuration configuration = new Configuration();
			boolean checkMandatory = allMandatoryComponents(componentList);

			if (!checkMandatory) {

				return "redirect:makeConfiguration";
			}
			for (int i = 0; i < componentList.size(); i++) {
				Component c = componentService.findComponentById(componentList.get(i).getId());
				configuration.getComponent().add(c);
			}

			configuration.setTotal(configuration.getTotal());
			configuration.setDate(new Date());
			try {
				configurationService.save(configuration);
			} catch (ConstraintViolationException ex) {
				System.out.println("ConstraintViolationException happened");
				return "redirect:makeConfiguration";
			}
			for (int i = 0; i < componentService.findAll().size(); i++) {
				for (int j = 0; j < configuration.getComponent().size(); j++) {
					if (componentService.findAll().get(i).getId().longValue() == configuration.getComponent().get(j)
							.getId().longValue()) {
						Component component = componentService
								.findComponentById(componentService.findAll().get(i).getId());
						component.setStock(component.getStock() - 1);
						componentService.updateComponent(component);
					}
				}
			}
			modelMap.addAttribute("configuration", configuration);
			componentList.clear();
			return "saveConfiguration";

		}
		return "403";

	}

	@ModelAttribute("componentList")
	public List<Component> getComponents() {
		return new ArrayList<Component>(); // populates form for the first time
											// if its null
	}

	@RequestMapping(value = "configurationDetails/{id}", method = RequestMethod.GET)
	public String getConfigurationDetails(@PathVariable Long id, ModelMap modelMap) {
		boolean checkAuthority = hasRole();
		if (checkAuthority) {
			Configuration configuration = configurationService.getConfigurationById(id);
			modelMap.addAttribute("configurationDetail", configuration);

			return "configurationDetails";

		}
		return "403";
	}

	private int getTotal(List<Component> components) {
		int total = 0;
		for (int i = 0; i < components.size(); i++) {
			total += components.get(i).getPrice();
		}
		return total;
	}

	private boolean allMandatoryComponents(List<Component> components) {
		List<TypeOfComponent> mandatoryTypes = new ArrayList<TypeOfComponent>();
		// value one----------------------------------------
		for (int i = 0; i < typeOfComponentService.findAll().size(); i++) {
			if (typeOfComponentService.findAll().get(i).getValueOfComponent().compareTo(ValueOfComponent.one) == 0) {
				mandatoryTypes.add(typeOfComponentService.findAll().get(i));
			}
		}
		List<Component> mandatoryComponents = new ArrayList<Component>();
		for (int i = 0; i < components.size(); i++) {
			if (components.get(i).getTypeOfComponent().getValueOfComponent().compareTo(ValueOfComponent.one) == 0) {
				mandatoryComponents.add(components.get(i));
			}
		}
		
		if (mandatoryComponents.size() < mandatoryTypes.size()) {
			return false;
		}
		// value oneToMany---------------------------------
		List<TypeOfComponent> mandatoryOneToMany = new ArrayList<TypeOfComponent>();
		for (int i = 0; i < typeOfComponentService.findAll().size(); i++) {
			if (typeOfComponentService.findAll().get(i).getValueOfComponent()
					.compareTo(ValueOfComponent.oneToMany) == 0) {
				mandatoryOneToMany.add(typeOfComponentService.findAll().get(i));
			}
		}
		Set<String> mandatoryStringOneToMany = new HashSet<String>();
		for (int i = 0; i < components.size(); i++) {
			if (components.get(i).getTypeOfComponent().getValueOfComponent()
					.compareTo(ValueOfComponent.oneToMany) == 0) {
				mandatoryStringOneToMany.add(components.get(i).getTypeOfComponent().getName());
			}
		}

		if (mandatoryOneToMany.size() != mandatoryStringOneToMany.size()) {
			return false;
		}

		return true;
	}

	public boolean hasRole() {
		SecurityContext context = SecurityContextHolder.getContext();
		if (context == null)
			return false;

		Authentication authentication = context.getAuthentication();
		if (authentication == null)
			return false;

		for (GrantedAuthority auth : authentication.getAuthorities()) {
			if ((auth.getAuthority()).equals("ROLE_ADMIN") || auth.getAuthority().equals("ROLE_SELLER")) {
				return true;
			}
		}
		return false;
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

	private void addComponent(List<Component> componentList, ModelMap modelMap, Long add) {

		boolean isAllowedToAdd = true;
		Component component = componentService.findComponentById(add);
		if (isMandatory(component)) {
			for (int i = 0; i < componentList.size(); i++) {
				if (componentList.get(i).getTypeOfComponent().getId() == component.getTypeOfComponent().getId()) {
					modelMap.addAttribute("checkMandatoryOne", "you can have only one component of that type");
					isAllowedToAdd = false;
					break;
				}
			}
		}

		if (component.getTypeOfComponent().getValueOfComponent().compareTo(ValueOfComponent.zero) == 0) {
			for (int i = 0; i < componentList.size(); i++) {
				if (componentList.get(i).getTypeOfComponent().getId() == component.getTypeOfComponent().getId()) {
					modelMap.addAttribute("checkMandatoryOne", "you can have only one component of that type");
					isAllowedToAdd = false;
					break;
				}
			}
		}

		if (isAllowedToAdd)
			componentList.add(component);
	}

	private boolean isMandatory(Component component) {
		return component.getTypeOfComponent().getValueOfComponent().compareTo(ValueOfComponent.one) == 0;
	}
	
	private void addFilter(String sorter, ModelMap modelMap, PagedListHolder<Component> pageListHolder, Integer page, String filter){

		List<Component> componentsSorted = componentService.findAll();
		if (sorter != null) {
			String[] spliter = sorter.split("/");
			String columnSort = spliter[0];
			String descriptor = spliter[1];
			componentsSorted = componentService.sortBy(columnSort, descriptor);
			pageListHolder = getPageListHolder(componentsSorted, modelMap, page);
		}
		TypeOfComponent typeOfComponent = typeOfComponentService.findTypeOfComponentByName(filter);
		List<Component> filterComponents = new ArrayList<Component>();
		for (int i = 0; i < componentsSorted.size(); i++) {
			if (componentsSorted.get(i).getTypeOfComponent().getId() == typeOfComponent.getId()) {
				filterComponents.add(componentsSorted.get(i));
			}
		}

		modelMap.addAttribute("filter", filter);
		pageListHolder = getPageListHolder(filterComponents, modelMap, page);
	
	}

	private PagedListHolder<Configuration> getPageListHolderConfiguration(List<Configuration> configurations, ModelMap modelMap, Integer page) {
		PagedListHolder<Configuration> pageListHolder = new PagedListHolder<Configuration>(configurations);
		pageListHolder.setPageSize(3);

		modelMap.addAttribute("maxPages", pageListHolder.getPageCount());
		if (page == null || page <= 1 || page > pageListHolder.getPageCount()) {
			page = 0;
			pageListHolder.setPage(page);
		} else {
			pageListHolder.setPage(page - 1);
		}
		return pageListHolder;
	}

}
