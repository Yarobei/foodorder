package by.it.academy.foodorder.parent.model;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Entity
@DynamicUpdate
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_id")
    private Long foodId;

    @Lob
    @Basic(fetch=FetchType.LAZY)
    @Column(name = "image", length = Integer.MAX_VALUE, nullable = true)
    private byte[] image;

    @Transient
    private MultipartFile fileData;

    @Column
    private String name;

    @Column
    private Double price;

    @Column
    private Integer cookingTime;

    @Column
    private String ingredients;

    @Column
    private Double weight;

    @Column
    private Double discount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "food_basket",
            joinColumns = {@JoinColumn(name = "food_id")},
            inverseJoinColumns = {@JoinColumn(name = "basket_id")})
    @EqualsAndHashCode.Exclude @ToString.Exclude
    private List<Basket> basket;
}
