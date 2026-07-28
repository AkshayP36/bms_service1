Class Diagram:

City:
    id
    name
    List<Theater>

Theater
    id
    name
    List<Screen>
    city {ManyToOne} //add the relation in the class for Many entity

Screen
    id
    name
    List<seat>
    List<Feature>
    status //like Empty, Inprogress

Seat
    id
    name/number
    Row
    Col
    SeatType [ex. Golden, premium, Silver]
    ~~Status~~ //if seat is occupied. This status is applicable for show level. Hence it should not be here. it shoudl be at show level

Show
    id
    movie
    date
    start-time
    end-time
    screen
    List<feature>
    Seat                 //ManyToMany
                            - SHOWS_SEATS
                            - seat_id
                            - Status

Price: (theater + time + movie + screen) + seatType
(theater + time + movie + screen) = show

    Show + seatType    
                        // SHOWS_SEATTYPE
                        - show_id
                        - seatType
                        - price


User
    id
    name


Movie
    id
    name
    release date
    description

Booking
    id
    number
    status  //PENDING, CONFIRMED, ~~CANCELLED~~
    user
    show
    amount
    List<shows_seats>
    List<Payment>


Payments
    id
    type
    amount 
    mode  //CC, DC, WALLET
    status //CONFIRMED, PENDING, REFUNDED
    unique reference number


Feature: [enum]
    2D
    3D
    4D

SeatType: [enum]
    SILVER
    GOLD
    PLATINUM
    PREMIUM
    

    

