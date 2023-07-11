package docrob.springblog.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "dog_owners")
public class DogOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 255, nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner", fetch = FetchType.EAGER)
    private List<Dog> dogs;

    private String email;

    @Override
    public String toString() {
        String str = name + " has dog(s): ";
        for(Dog dog : dogs) {
            str += dog.getName() + " ";
        }
        return str;
    }
}
