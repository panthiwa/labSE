package sut.sa.g16.entity;
        import  javax.persistence.*;
        import lombok.*;
        import java.util.Date;

@Entity
@Data
@Table (name = "Bill")
public class Bill {
    @Id
    @SequenceGenerator(name="billId_seq",sequenceName="billId_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="billId_seq")

    private @NonNull Long billId;
    private @NonNull int price;
    private @NonNull String name;
    private @NonNull Date date;
    private @NonNull String phonenumber;


    @OneToOne(fetch = FetchType.EAGER, optional = true,cascade =  CascadeType.ALL)
    @JoinColumn(name = "reser_id")
    private Reservation reservation;

    @OneToOne(fetch = FetchType.EAGER, optional = true,cascade =  CascadeType.ALL)
    @JoinColumn(name = "bookingequioment_id")
    private BookingEquipment bookingEquioment;

    @ManyToOne(fetch = FetchType.EAGER   , cascade = CascadeType.ALL)
    @JoinColumn(name="mamber_id")
    private Member member;

    @ManyToOne(fetch = FetchType.EAGER   , cascade = CascadeType.ALL)
    @JoinColumn(name="type_id")
    private Type type;

    protected Bill(){
    }
    public Bill(Integer price, String name, String phonenumber,Date date,Reservation reservation,BookingEquipment bookingEquioment,Member member,Type type){
        this.type = type;
        this.price = price;
        this.name = name;
        this.phonenumber = phonenumber;
        this.date = date;
        this.reservation = reservation;
        this.bookingEquioment = bookingEquioment;
        this.member = member;
    }




    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public BookingEquipment getBookingEquioment() {
        return bookingEquioment;
    }

    public void setBookingEquioment(BookingEquipment bookingEquioment) {
        this.bookingEquioment = bookingEquioment;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}