package sut.sa.g16.cotroller;
import sut.sa.g16.entity.Bill;
import sut.sa.g16.entity.Type;
import sut.sa.g16.entity.BookingEquipment;
import sut.sa.g16.entity.Member;
import sut.sa.g16.entity.Reservation;
import sut.sa.g16.model.DataBill;
import sut.sa.g16.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import  java.lang.Integer.*;

import java.util.Collection;
import java.util.stream.Collectors;
@RestController @CrossOrigin(origins = "http://localhost:4200")
public class BillCon {
    @Autowired
    private BillRepos repository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private final MembersRepository membersRepository;
    @Autowired
    private final ReservationRepository reservationRepository;
    @Autowired
    private TypeRepos typeRepos;



    public BillCon(BillRepos repository,BookingRepository bookingRepository, MembersRepository membersRepository,ReservationRepository reservationRepository,TypeRepos typeRepos) {
        this.repository = repository;
        this.bookingRepository = bookingRepository;
        this.membersRepository = membersRepository;
        this.reservationRepository = reservationRepository;
        this.typeRepos = typeRepos;
    }

    @GetMapping("/Bill")
    public Collection<Bill> bills() {
        return repository.findAll().stream().collect(Collectors.toList());
    }
    /*=======================================================*/
    @GetMapping("/Booking")
    public Collection<BookingEquipment> bookingEquioments() {
        return bookingRepository.findAll().stream().collect(Collectors.toList());
    }
    @GetMapping("/Booking-list/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public BookingEquipment bookingEquipmentFind(@PathVariable("id") Long id) {
        return bookingRepository.findByBookingId(id);
    }

    /*=======================================================*/
    @GetMapping("/Reservation")
    public Collection<Reservation> reservations() {
        return reservationRepository.findAll().stream().collect(Collectors.toList());
    }
    @GetMapping("/Reservation-list/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Reservation reservationFind(@PathVariable("id") Long id) {
        return reservationRepository.findByReservationId(id);
    }

    /*=======================================================*/
    @GetMapping("/Member")
    public Collection<Member> members() {
        return membersRepository.findAll().stream().collect(Collectors.toList());
    }
    @GetMapping("/Member-list/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Member memberFind(@PathVariable("id") Long id) {
        return membersRepository.findByMemberId(id);
    }
    /*=======================================================*/

    @GetMapping("/Type")
    public Collection<Type> types() {
        return typeRepos.findAll().stream().collect(Collectors.toList());
    }
    @GetMapping("/Type-list/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Type typeFind(@PathVariable("id") Long id) { return typeRepos.findByTypeId(id);
    }
    /*=======================================================*/




    @PostMapping(path = "/bills")
    public Bill databill(@RequestBody DataBill dataBill){
      //public Bill(Integer price, String name, String phonenumber,Date date,Reservation reservation,BookingEquipment bookingEquioment,Member member,String list){
        Member m = this.membersRepository.findByMemberId(dataBill.getMemberId());

        Reservation r = (dataBill.getReserId() != null)? this.reservationRepository.findByReservationId(dataBill.getReserId()) : null;
        BookingEquipment Book =  (dataBill.getBookingequId() != null) ? this.bookingRepository.findByBookingId(dataBill.getBookingequId()) : null;

        int totalprice = ((r != null) ? r.getPrice().intValue(): 0 ) + ((Book != null) ? Book.getEquipmentPrice() : 0);
        Type ty = this.typeRepos.findByTypeId(dataBill.getTypeId());

        System.out.println(dataBill);
        Bill bill = this.repository.save(new Bill(totalprice,dataBill.getName(),dataBill.getPhonenumber(),dataBill.getDate(),r,Book,m,ty));
        return bill;
    }

}
