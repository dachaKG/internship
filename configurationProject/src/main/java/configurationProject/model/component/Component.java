package configurationProject.model.component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import configurationProject.model.typeOfComponent.TypeOfComponent;

@Entity
public class Component {
	public Component() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Component(Long id, String code, TypeOfComponent typeOfComponent, String name, int price, int stock) {
		super();
		this.id = id;
		this.code = code;
		this.typeOfComponent = typeOfComponent;
		this.name = name;
		this.price = price;
		this.stock = stock;
	}



	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "component_id")
	private Long id;
	
	@Column(length = 10, unique = true)
	@NotBlank(message = "Code is mandatory")
	private String code;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinTable(name = "component_type", joinColumns = @JoinColumn(name = "component_id"), inverseJoinColumns = @JoinColumn(name = "toc_id", nullable = false) )
	private TypeOfComponent typeOfComponent;
	
	@Column(length = 20)
	@NotBlank(message = "Name is mandatory")
	private String name;

	@Min(10)
	@Column
	@NotNull(message = "Price is mandatory")
	private int price;
	
	@Min(0)
	@Column
	@NotNull(message = "Stock is mandatory")
	private int stock;
	
	
	//getters and setters-------------------------------

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public TypeOfComponent getTypeOfComponent() {
		return typeOfComponent;
	}

	public void setTypeOfComponent(TypeOfComponent typeOfComponent) {
		this.typeOfComponent = typeOfComponent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	
}
