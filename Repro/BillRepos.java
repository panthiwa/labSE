package sut.sa.g16.repository;
        import sut.sa.g16.entity.Bill;
        import sut.sa.g16.entity.Reservation;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BillRepos extends JpaRepository<Bill, Long>{
    Bill findByBillId(Long billId);
}

