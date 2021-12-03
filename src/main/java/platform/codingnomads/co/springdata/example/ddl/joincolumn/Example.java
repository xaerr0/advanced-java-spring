package platform.codingnomads.co.springdata.example.ddl.joincolumn;

import javax.persistence.*;

@Entity
@Table(name = "examples")
public class Example {

    @Id
    @GeneratedValue
    private Long id;

    //define a one-to-many relationship with a few customizations
    @ManyToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
    //use @JoinColumns to indicate multiple join columns in the are needed in the examples table
    @JoinColumns({
            //define the first join column. It will be called referenced_id and references the id column in the user table
            @JoinColumn(name = "references_id", referencedColumnName = "id"),
            //define the second join column. It will be called references_name and references the name column in the user table
            @JoinColumn(name = "references_name", referencedColumnName = "name")
    })
    private User user;
}