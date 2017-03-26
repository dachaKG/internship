package configurationProject.model.typeOfComponent;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class TypeOfComponent {

	
	public TypeOfComponent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TypeOfComponent(Long id, String name, ValueOfComponent valueOfComponent) {
		super();
		this.id = id;
		this.name = name;
		this.valueOfComponent = valueOfComponent;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "toc_id")
	private Long id;
	
	@Column(name = "toc_name", length = 20, unique = true)
	@NotBlank(message = "Name is mandatory")
	private String name;
	
	@Column
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Value of component is mandatory")
	private ValueOfComponent valueOfComponent;

	
	//getters and setters------------------------------------------------

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ValueOfComponent getValueOfComponent() {
		return valueOfComponent;
	}

	public void setValueOfComponent(ValueOfComponent valueOfComponent) {
		this.valueOfComponent = valueOfComponent;
	}

	
	
	
}
