package platform.codingnomads.co.springdata.example.dml.commonproblems.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.codingnomads.co.springdata.example.dml.commonproblems.models.Address;
import platform.codingnomads.co.springdata.example.dml.commonproblems.models.ContactCard;
import platform.codingnomads.co.springdata.example.dml.commonproblems.models.User;
import platform.codingnomads.co.springdata.example.dml.commonproblems.repositories.UserRepo;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public void persistAFewUsers() {

        //set up an Address
        Address address = Address.builder()
                .street("Balboa Tower Way 45")
                .city("Los Angeles")
                .postcode("90361")
                .state("CA")
                .country("United States")
                .build();

        //create new ContactCard
        ContactCard contactCard = ContactCard.builder().emailAddress("lucile@emailserver.com").build();

        //create new User with above ContactCard and Address
        User user = User.builder().username("Lucile Bluth").address(address).contactCard(contactCard).build();
        //save User to the db
        userRepo.save(user);


        //create a new ContactCard and reassign contactCard
        contactCard = ContactCard.builder().emailAddress("george@email.com").phoneNumber("+1(405)345-5837").build();
        //create new User with original address and new contact card
        user = User.builder().username("George Bluth").address(address).contactCard(contactCard).build();
        //save new User assigned to user
        userRepo.save(user);


        //create new ContactCard and assign it to contactCard
        contactCard = ContactCard.builder()
                .emailAddress("oscar@email.com")
                .phoneNumber("+1(657)583-5753")
                .websiteUrl("imoscar.com")
                .build();
        //create new User and assign it to user using original Address and new ContactCard
        user = User.builder().username("Oscar Bluth").address(address).contactCard(contactCard).build();
        //save new User
        userRepo.save(user);
    }

    public void querySomeData() {

        //find user by username
        System.out.println(userRepo.findByUsername("George Bluth").toString());

        //find users by address
        userRepo.findByAddress_id(1L).forEach(System.out::println);

        //find user by ID
        System.out.println(userRepo.getOne(4L).toString());

        //find all users
        userRepo.findAll().forEach(System.out::println);
    }

    public void idError() {
        //build an Address
        Address address1 = Address.builder()
                .street("A street name and a house number")
                .city("The name of a city")
                .postcode("00000")
                .state("The name of a State/Province")
                .country("The name of a country")
                .build();

        //build a ContactCard
        ContactCard contactCard1 = ContactCard.builder().emailAddress("an email address").build();

        //build a User
        User user1 = User.builder()
                .username("a username")
                .contactCard(contactCard1)
                .address(address1)
                .build();

        //save that user to the database
        userRepo.save(user1);

        //no problems so far

        //build a second Address
        Address address2 = Address.builder()
                .street("Street 36")
                .city("City of City")
                .postcode("95732")
                .state("State of Disarray")
                .country("United States of Disarray")
                .build();

        //build a second contact card
        ContactCard contactCard2 = ContactCard.builder()
                .emailAddress("pleasedontsendmeemails@nothanks.com")
                .build();

        //build a second User with an ID already assigned
        User user2 = User.builder()
                //note that unlike user1, user2 is assigned ID 1
                .id(1L)
                .username("a_username")
                .contactCard(contactCard2)
                .address(address2)
                .build();

        //attempt to save new User with already assigned ID
        userRepo.save(user2);
    }
}
