package com.project.homeservicesystem.entities.builder;

import com.project.homeservicesystem.entities.services.MainService;
import com.project.homeservicesystem.entities.services.ServiceCategory;




public class ServiceBuilder {

    private Long id;
    private String title;
    private double basePrice;
    private String description;
    private ServiceCategory category;

    public static ServiceBuilder aService() {
        return new ServiceBuilder();
    }

    public ServiceBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public ServiceBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public ServiceBuilder withBasePrice(Double basePrice) {
        this.basePrice = basePrice;
        return this;
    }

    public ServiceBuilder withDescription(String description) {
        this.description = description;
        return this;
    }


    public ServiceBuilder withCategory(ServiceCategory category) {
        this.category = category;
        return this;
    }



    public MainService build() {
        MainService mainService = new MainService();
        mainService.setId(id);
        mainService.setTitle(title);
        mainService.setBasePrice(basePrice);
        mainService.setDescription(description);
        mainService.setCategory(category);
        return mainService;
    }

}
