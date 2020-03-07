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
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foodId;
    @Column
    private String name;
    @Column
    private Double price;
    @Column
    private Integer cookingTime;
    @Column
    private boolean delivery;
    @Column
    private String ingredients;
    @Column
    private Double weight;
    @Column
    private Double discount;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "food", cascade = CascadeType.ALL)
    private Category category;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "FOOD_BASKET",
            joinColumns = {@JoinColumn(name = "foodId")},
            inverseJoinColumns = {@JoinColumn(name = "basketId")})
    private List<Basket> basket = new ArrayList<>();
}
