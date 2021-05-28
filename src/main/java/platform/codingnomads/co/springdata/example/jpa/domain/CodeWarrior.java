package platform.codingnomads.co.springdata.example.jpa.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "code_warriors")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CodeWarrior {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @OneToMany(
            mappedBy = "codeWarrior",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Builder.Default
    private List<Weapon> weapons = new ArrayList<>();

    public void addWeapon(Weapon weapon) {
        weapons.add(weapon);
        weapon.setCodeWarrior(this);
    }

    public void removeWeapon(Weapon weapon) {
        weapons.remove(weapon);
        weapon.setCodeWarrior(null);
    }
}
