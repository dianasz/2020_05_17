package pl.javastart.sellegro.auction;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuctionService {

    private AuctionRepository auctionRepository;

    public AuctionService(AuctionRepository auctionRepository) {
        this.auctionRepository = auctionRepository;
    }

    public List<Auction> findAllSorted(String sort) {
        List<Auction> auctions;
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
        }
        return auctions;
    }
}