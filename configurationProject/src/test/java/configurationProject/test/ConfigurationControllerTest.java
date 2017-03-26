package configurationProject.test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import configurationProject.controller.ConfigurationController;
import configurationProject.model.component.Component;
import configurationProject.model.configuration.Configuration;
import configurationProject.model.typeOfComponent.TypeOfComponent;
import configurationProject.model.typeOfComponent.ValueOfComponent;
import configurationProject.service.component.ComponentService;
import configurationProject.service.configuration.ConfigurationService;
import configurationProject.service.typeOfComponent.TypeOfComponentService;


public class ConfigurationControllerTest {
	
	private MockMvc mockMvc;
	
	@Mock
	private ConfigurationService configurationServiceMock;
	
	@Mock
	private ComponentService componentServiceMock;
	
	@Mock
	private TypeOfComponentService typeOfComponentServiceMock;
	
	
	@InjectMocks
	private ConfigurationController configurationController;
	

	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");

        mockMvc = MockMvcBuilders.standaloneSetup(configurationController)
                                 .setViewResolvers(viewResolver)
                                 .build();
		
		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("admin", "admin", getAuthorities()));
		
	}

	@Test
	public void testSoldConfigurationListGet() throws Exception {
		List<Configuration> configurations = getConfigurations();
		
		when(configurationServiceMock.findAll()).thenReturn(configurations);
		
		mockMvc.perform(get("/soldConfigurationList")).andExpect(status().isOk()).andExpect(model().attributeExists("pageListHolder"));

	}

	@Test
	public void testMakeConfigurationGet() throws Exception {
		List<Component> components = getComponents();
		List<TypeOfComponent> typeOfComponents = getTypeOfComponents();
		
		when(componentServiceMock.findAll()).thenReturn(components);
		when(typeOfComponentServiceMock.findAll()).thenReturn(typeOfComponents);
		mockMvc.perform(get("/makeConfiguration?page=2")).andExpect(status().isOk()).andExpect(model().attribute("page", 1));
	}

	/*@Test
	public void testSaveConfiguration() throws Exception {

		List<Component> components = getComponents();
		List<TypeOfComponent> typeOfComponents = getTypeOfComponents();
		
		when(componentServiceMock.findAll()).thenReturn(components);
		when(typeOfComponentServiceMock.findAll()).thenReturn(typeOfComponents);
		mockMvc.perform(post("/saveConfiguration").flashAttr("componentList", getComponents())).andExpect(status().isOk());
	}
*/
	@Test
	public void testGetConfigurationDetails() throws Exception {
		List<Configuration> configurations = getConfigurations();
		
		when(configurationServiceMock.getConfigurationById(1L)).thenReturn(configurations.get(0));
		
		mockMvc.perform(get("/configurationDetails/{id}",1L)).andExpect(status().isOk()).andExpect(model().attributeExists("configurationDetail"));
	}
	
	private List<Configuration> getConfigurations(){
		List<Configuration> configurations = new ArrayList<Configuration>();
		configurations.add(new Configuration(1L,112,new Date(),getComponents()));
		configurations.add(new Configuration(2L,112,new Date(),getComponents()));
		configurations.add(new Configuration(3L,112,new Date(),getComponents()));
		configurations.add(new Configuration(4L,112,new Date(),getComponents()));
		configurations.add(new Configuration(5L,112,new Date(),getComponents()));
		configurations.add(new Configuration(6L,112,new Date(),getComponents()));
		configurations.add(new Configuration(7L,112,new Date(),getComponents()));
		configurations.add(new Configuration(8L,112,new Date(),getComponents()));
		
		return configurations;
	}
	
	private List<Component> getComponents(){
		List<Component> components = new ArrayList<Component>();
		components.add(new Component(1L,"m01", new TypeOfComponent(1L, "tastatura", ValueOfComponent.one), "sad", 15, 5));
		components.add(new Component(2L,"m02", new TypeOfComponent(1L, "tastatura", ValueOfComponent.one), "sad", 15, 5));
		components.add(new Component(3L,"m03", new TypeOfComponent(1L, "tastatura", ValueOfComponent.one), "sad", 15, 5));
		components.add(new Component(4L,"m04", new TypeOfComponent(1L, "tastatura", ValueOfComponent.one), "sad", 15, 5));
		components.add(new Component(5L,"m05", new TypeOfComponent(1L, "tastatura", ValueOfComponent.one), "sad", 15, 5));
		components.add(new Component(6L,"m06", new TypeOfComponent(1L, "tastatura", ValueOfComponent.one), "sad", 15, 5));
		components.add(new Component(7L,"m07", new TypeOfComponent(1L, "tastatura", ValueOfComponent.one), "sad", 15, 5));
		
		return components;
	}
	
	private List<TypeOfComponent> getTypeOfComponents(){
		List<TypeOfComponent> typeOfComponents = new ArrayList<TypeOfComponent>();
		typeOfComponents.add(new TypeOfComponent(1L, "tastatura", ValueOfComponent.one));
		typeOfComponents.add(new TypeOfComponent(2L, "mis", ValueOfComponent.one));
		typeOfComponents.add(new TypeOfComponent(3L, "monitor", ValueOfComponent.one));
		typeOfComponents.add(new TypeOfComponent(4L, "kuciste", ValueOfComponent.one));
		
		return typeOfComponents;
	}
	
	private Collection<GrantedAuthority> getAuthorities(){
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new GrantedAuthority() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = -1345017020248096210L;
	
			@Override
			public String getAuthority() {
				return "ROLE_ADMIN";
			}
		});
		return authorities;
	}

}
