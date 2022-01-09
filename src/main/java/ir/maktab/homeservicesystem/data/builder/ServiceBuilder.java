package ir.maktab.homeservicesystem.data.builder;

import ir.maktab.homeservicesystem.data.entities.services.MainService;
import ir.maktab.homeservicesystem.data.entities.services.SubService;




public class ServiceBuilder {

    private Long id;
    private String title;
    private double basePrice;
    private String description;
    private SubService category;

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
