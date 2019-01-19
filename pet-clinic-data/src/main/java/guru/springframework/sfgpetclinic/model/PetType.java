package guru.springframework.sfgpetclinic.model;

import guru.springframework.sfgpetclinic.services.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * @author
 */
@Entity
@Table(name = "types")
public class PetType extends BaseEntity {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
