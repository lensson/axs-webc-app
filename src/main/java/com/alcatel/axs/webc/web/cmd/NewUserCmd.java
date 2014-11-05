package com.alcatel.axs.webc.web.cmd;

import java.util.ArrayList;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class NewUserCmd {

    @NotBlank
    @Size(max = 255)
    private String name;


    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    @NotBlank
    @Size(max = 6)
    private String description;


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
    
    
}
