package ir.maktab.homeservicesystem.data.entities.services;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

    @Getter
    @Setter
    @ToString
    @MappedSuperclass
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public class Service {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Service service = (Service) o;

            return id.equals(service.id);
        }

        @Override
        public int hashCode() {
            return id.hashCode();
        }
    }

