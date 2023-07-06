package docrob.springblog.models;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name = "dogs")
public class Dog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "dog_name", length = 50, nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private int age;

    @Column(length = 255)
    private String ownerName;

}
