package by.it.academy.foodorder.parent.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Entity
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "basket_id")
    private Long basketId;

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    @Access(AccessType.PROPERTY)
    private User user;

    @ManyToMany(mappedBy = "basket", fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude @ToString.Exclude
    private List<Food> food;

}
