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





    public MainService build() {
        MainService mainService = new MainService();
        mainService.setId(id);
        mainService.setTitle(title);
        return mainService;
    }

}
