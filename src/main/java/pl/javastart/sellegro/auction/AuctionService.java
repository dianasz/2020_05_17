package pl.javastart.sellegro.auction;

import org.springframework.stereotype.Service;
import pl.javastart.sellegro.auction.entity.Auction;
import pl.javastart.sellegro.auction.repository.AuctionRepository;

import java.util.List;

@Service
public class AuctionService {

    private AuctionRepository auctionRepository;
    List<Auction> auctions;

    public AuctionService(AuctionRepository auctionRepository) {
        this.auctionRepository = auctionRepository;
        auctionRepository.createTitle();
        updateTitles();
    }

    public void updateTitles(){
        auctionRepository.updateTitleForPink();
        auctionRepository.updateTitleForAquamarine();
        auctionRepository.updateTitleForOrange();
    }

    public List<Auction> findAllSorted(String sort) {
        switch (sort) {
            case "title":
                auctions = auctionRepository.findAllByOrderByTitle();
                break;
            case "price":
                auctions = auctionRepository.findAllByOrderByPrice();
                break;
            case "color":
                auctions = auctionRepository.findAllByOrderByColor();
                break;
            case "endDate":
                auctions = auctionRepository.findAllByOrderByEndDate();
                break;
            default:
                auctions = auctionRepository.findAll();
                break;
        }
        return auctions;
    }

    public List<Auction> findAll() {
        return auctionRepository.findAll();
    }

    public List<Auction> findAllForFilters(AuctionFilters auctionFilters) {
        return auctionRepository.findByAllAttributes(auctionFilters.getTitle(), auctionFilters.getCarMaker(),
                auctionFilters.getCarModel(), auctionFilters.getColor());
    }
}