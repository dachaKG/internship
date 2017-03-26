package configurationProject.model.configuration;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

import configurationProject.model.component.Component;

@Entity
@XmlRootElement(name = "configurations")
@XmlAccessorType(XmlAccessType.NONE)
public class Configuration {
	
	
	public Configuration() {
		super();
		component = new ArrayList<Component>();
	}

	public Configuration(Long id, int total, Date date, List<Component> component) {
		super();
		this.id = id;
		this.total = total;
		this.date = date;
		this.component = component;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "configuration_id")
	private Long id;
	
	@Column
	@NotNull
	private int total;
	
	@Column
	@NotNull
	private Date date;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JsonIgnore
	@JoinTable(name = "configuration_component", joinColumns = @JoinColumn(name = "configuration_id"), inverseJoinColumns = @JoinColumn(name = "component_id"))
	private List<Component> component;
	
	//getters and setters ---------------------------------------

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getTotal() {
		int total = 0;
		for(int i = 0 ; i < component.size(); i++){
			total += component.get(i).getPrice(); 
		}
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<Component> getComponent() {
		return component;
	}

	public void setComponent(List<Component> component) {
		this.component = component;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	

	
}
