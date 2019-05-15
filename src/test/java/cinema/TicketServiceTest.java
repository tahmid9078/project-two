package cinema;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ttv.daos.TicketDao;
import com.ttv.models.Account;
import com.ttv.models.Role;
import com.ttv.models.Ticket;
import com.ttv.models.TicketType;
import com.ttv.models.Tmdb;
import com.ttv.services.AccountService;
import com.ttv.services.TicketService;
import com.ttv.services.TicketTypeService;
import com.ttv.services.TmdbService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:testApplicationContext.xml")
public class TicketServiceTest {
	
	List<Ticket> mockTickets;
	Ticket mockTicket;
	
	@InjectMocks
	private TicketService ticketService;
	
	@Mock
	private TicketDao ticketDao;
	
	@Mock
	private AccountService accountService;
	
	@Mock
	private TmdbService tmdbService;
	
	@Mock
	private TicketTypeService ticketTypeService;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockTickets = getDummyTickets();
		mockTicket = getDummyTicket();
	}
	
	@Test
	public void testFindAllTickets() {
		when(ticketDao.findAll()).thenReturn(mockTickets);
		List<Ticket> tickets = ticketService.findAll();
		assertEquals(3, tickets.size());
	}
	
	@Test
	public void testAddTicket() {
		when(ticketDao.add(mockTicket)).thenReturn(mockTicket);
		when(accountService.findById((long)1)).thenReturn(new Account((long)1, "username", "password", "firstName", "lastName", "email", new Role((long)1 , "role")));
		when(ticketTypeService.findByName("adult")).thenReturn(new TicketType((long)1, "adult", 14.0));
		when(tmdbService.findIdByApiId("1111")).thenReturn(new Tmdb((long)1, "1111"));
		Ticket ticket = ticketService.add(mockTicket);
		assertEquals((long) 1, (long)ticket.getId());
	}
	
	@Test
	public void testFindTicketById() {
		when(ticketDao.findById((long)1)).thenReturn(mockTicket);
		Ticket ticket = ticketService.findById((long)1);
		assertEquals((long) 1, (long) ticket.getId());
	}
	
	@Test
	public void testUpdateTicket() {
		Ticket t1 = new Ticket((long)1,
				 new Account((long)1, "username", "password", "firstName", "lastName", "email", new Role((long)1 , "role")),
				 new Tmdb((long)1, "1111"),
				 "6:00PM",
				 "*1111",
				 new TicketType((long)1, "adult", 14.0));
		doAnswer((i)->{
			Ticket ticket= i.getArgument(0);
			assertTrue("6:OOPM".equals(ticket.getMovieShowTime()));
			return null;
		}).when(ticketDao).update(t1);
		ticketService.update(mockTicket);
	}
	
	@Test
	public void testDeleteTicket() {
		Ticket deleteTicket = mockTickets.get(1);
		doAnswer((i) ->{
			Ticket ticket = ticketService.findById(i.getArgument(0));
			assertNull(ticket);
			return null;
		}).when(ticketDao).deleteById(deleteTicket.getId());
		accountService.deleteById(deleteTicket.getId());
	}
	

	private List<Ticket> getDummyTickets() {
		Ticket t1 = new Ticket((long)1,
				 new Account((long)1, "username1", "password1", "firstName1", "lastName1", "email1", new Role((long)1 , "role1")),
				 new Tmdb((long)1, "1111"),
				 "3:00PM",
				 "*1111",
				 new TicketType((long)1, "adult", 14.0));
		Ticket t2 = new Ticket((long)2,
				 new Account((long)2, "username2", "password2", "firstName2", "lastName2", "email2", new Role((long)2 , "role2")),
				 new Tmdb((long)2, "1112"),
				 "6:00PM",
				 "*1112",
				 new TicketType((long)2, "adult", 10.0));
		Ticket t3 = new Ticket((long)3,
				 new Account((long)3, "username3", "password3", "firstName3", "lastName3", "email3", new Role((long)3 , "role3")),
				 new Tmdb((long)1, "1131"),
				 "9:00PM",
				 "*1311",
				 new TicketType((long)3, "child", 10.0));
		
		List<Ticket> tickets = new ArrayList<>();
		tickets.add(t1);
		tickets.add(t2);
		tickets.add(t3);
		return tickets;
	}

	private Ticket getDummyTicket() {
		return new Ticket((long)1,
				 new Account((long)1, "username", "password", "firstName", "lastName", "email", new Role((long)1 , "role")),
				 new Tmdb((long)1, "1111"),
				 "3:00PM",
				 "*1111",
				 new TicketType((long)1, "adult", 14.0));
	}
	
}
