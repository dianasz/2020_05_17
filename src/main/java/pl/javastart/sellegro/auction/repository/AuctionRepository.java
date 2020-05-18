package pl.javastart.sellegro.auction.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import pl.javastart.sellegro.auction.entity.Auction;

import java.util.List;

public interface AuctionRepository extends JpaRepository<Auction, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Auction a SET a.title = concat(a.carMaker, ' ', a.carModel)")
    void createTitle();

    @Transactional
    @Modifying
    @Query("UPDATE Auction a SET a.title = concat('Perełka ', a.carMaker, ' ', a.carModel) where a.color='Pink'")
    void updateTitleForPink();

    @Transactional
    @Modifying
    @Query("UPDATE Auction a SET a.title = concat('Wyjątkowy ', a.carMaker, ' ', a.carModel) where a.color='Aquamarine'")
    void updateTitleForAquamarine();

    List<Auction> findAllByOrderByPriceDesc(Pageable pageable);

    List<Auction> findAllByOrderByTitle();

    List<Auction> findAllByOrderByColor();

    List<Auction> findAllByOrderByEndDate();

    List<Auction> findAllByOrderByPrice();

    @Query("select a from Auction a " +
            "where lower(a.title) LIKE lower(concat('%', :title, '%'))" +
            " and lower(a.carMaker) LIKE lower(concat('%', :carMake, '%'))" +
            " and lower(a.carModel) LIKE lower(concat('%', :carModel, '%'))" +
            " and lower(a.color) LIKE lower(concat('%', :color, '%'))")
    List<Auction> filtered(@Param("title") String title,
                           @Param("carMake") String carMake,
                           @Param("carModel") String carModel,
                           @Param("color") String color);

}
