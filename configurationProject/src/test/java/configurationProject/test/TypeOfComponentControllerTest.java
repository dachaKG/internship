package configurationProject.test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Collection;
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

import configurationProject.controller.TypeOfComponentController;
import configurationProject.model.typeOfComponent.TypeOfComponent;
import configurationProject.model.typeOfComponent.ValueOfComponent;
import configurationProject.service.typeOfComponent.TypeOfComponentService;

public class TypeOfComponentControllerTest {
	
	private MockMvc mockMvc;
	
	@Mock
	private TypeOfComponentService typeOfComponentServiceMock;
	
	@InjectMocks
	private TypeOfComponentController typeOfController;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");

        mockMvc = MockMvcBuilders.standaloneSetup(typeOfController)
                                 .setViewResolvers(viewResolver)
                                 .build();
		
		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("admin", "admin", getAuthorities()));
	}

	@Test
	public void testAddTypeOfComponent() throws Exception {
		List<TypeOfComponent> typeOfComponents = getTypeOfComponents();
		
		when(typeOfComponentServiceMock.findAll()).thenReturn(typeOfComponents);
		
		mockMvc.perform(get("/addTypeOfComponent")).andExpect(status().isOk()).andExpect(model().attributeExists("valueOfComponent"));
	}

	@Test
	public void testAddTypeOfComponentPost() throws Exception {
		List<TypeOfComponent> typeOfComponents = getTypeOfComponents();

		TypeOfComponent typeOfComponent = new TypeOfComponent(5L, "zvucnik", ValueOfComponent.one);
		when(typeOfComponentServiceMock.findAll()).thenReturn(typeOfComponents);
		
		
		mockMvc.perform(post("/addTypeOfComponent").flashAttr("addTypeOfComponent", typeOfComponent)).andExpect(redirectedUrl("typeOfComponentList"));
		
	}

	@Test
	public void testGetTypeOfComponentList() throws Exception {
		List<TypeOfComponent> typeOfComponents = getTypeOfComponents();
		
		when(typeOfComponentServiceMock.findAll()).thenReturn(typeOfComponents);
		
		mockMvc.perform(get("/typeOfComponentList")).andExpect(status().isOk()).andExpect(model().attributeExists("pageListHolder"));
	}

	@Test
	public void testUpdateTypeGet() throws Exception {
		
		mockMvc.perform(get("/updateType/{id}", 2L)).andExpect(status().isOk()).andExpect(view().name("updateType"));
	}

	@Test
	public void testUpdateType() throws Exception {
		List<TypeOfComponent> typeOfComponents = getTypeOfComponents();
		
		
		TypeOfComponent typeOfComponent = new TypeOfComponent(2L, "podloga za mis", ValueOfComponent.one);

		when(typeOfComponentServiceMock.findAll()).thenReturn(typeOfComponents);
		
		
		mockMvc.perform(post("/updateType/{id}", 2L).flashAttr("updateType", typeOfComponent)).andExpect(redirectedUrl("../typeOfComponentList")).andExpect(model().attributeExists("updateType"));
		
	}

	@Test
	public void testRemoveType() throws Exception {
		
		when(typeOfComponentServiceMock.findTypeOfComponentById(2L)).thenReturn(getTypeOfComponents().get(1));
		
		mockMvc.perform(post("/removeType/{id}", 2L)).andExpect(redirectedUrl("../typeOfComponentList"));
		
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
