package docrob.springblog.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "toys")
public class Toy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 255, nullable = false)
    private String name;

    // insert M:M relationship here if needed
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "dog_toys",
            joinColumns = {@JoinColumn(name = "toy_id")},
            inverseJoinColumns = {@JoinColumn(name = "dog_id")}
    )
    private List<Dog> dogs;

    // avoid the infinite stack trace from hell by only printing the toy's name
    @Override
    public String toString() {
        return name;
    }
}
