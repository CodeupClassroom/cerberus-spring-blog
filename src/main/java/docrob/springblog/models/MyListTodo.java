package docrob.springblog.models;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name = "todos")
public class MyListTodo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String description;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "list_id")
    private MyList list;
}
