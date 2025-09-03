package odata.demo.entity;

import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

@Entity
@Table(name="car_model")
public class CarModel {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    @Column(name = "Model_year")
    private Integer year;

    @NotNull
    private String sku;

    @ManyToOne(optional=false,fetch=FetchType.LAZY)
    @JoinColumn(name="maker_fk")
    private CarMaker maker;

    public CarModel(Long id, @NotNull String name, @NotNull Integer year, @NotNull String sku, CarMaker maker) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.sku = sku;
        this.maker = maker;
    }

    public CarModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public @NotNull Integer getYear() {
        return year;
    }

    public void setYear(@NotNull Integer year) {
        this.year = year;
    }

    public @NotNull String getSku() {
        return sku;
    }

    public void setSku(@NotNull String sku) {
        this.sku = sku;
    }

    public CarMaker getMaker() {
        return maker;
    }

    public void setMaker(CarMaker maker) {
        this.maker = maker;
    }
}
