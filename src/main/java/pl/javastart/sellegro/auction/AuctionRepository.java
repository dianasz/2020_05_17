package pl.javastart.sellegro.auction;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AuctionRepository extends JpaRepository<Auction, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Auction a SET a.title = concat(a.carMaker, ' ', a.carModel)")
    void updateTitle();

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
