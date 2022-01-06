package com.project.homeservicesystem.entities.services;

import com.project.homeservicesystem.entities.users.Provider;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ServiceCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double basePrice;
    @Lob
    private String description;
    @ManyToOne
    private MainService mainService;
    @ManyToMany
    private Set<Provider> providers = new HashSet<>();

    @Override
    public String toString() {
        return "SubService{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", basePrice=" + basePrice +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceCategory that = (ServiceCategory) o;
        return Double.compare(that.basePrice, basePrice) == 0 && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(mainService, that.mainService) && Objects.equals(providers, that.providers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, basePrice, description, mainService, providers);
    }
}
