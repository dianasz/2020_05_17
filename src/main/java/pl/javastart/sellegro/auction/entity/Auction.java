package pl.javastart.sellegro.auction.entity;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String carMaker;
    private String carModel;
    private String color;
    private BigDecimal price;
    private LocalDate endDate;

    public Auction() {
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCarMaker() {
        return carMaker;
    }

    public String getCarModel() {
        return carModel;
    }

    public String getColor() {
        return color;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}