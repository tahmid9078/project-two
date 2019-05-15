package cinema;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
import com.ttv.daos.TicketTypeDao;
import com.ttv.models.TicketType;
import com.ttv.models.Tmdb;
import com.ttv.services.TicketService;
import com.ttv.services.TicketTypeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:testApplicationContext.xml")
public class TicketTypeServiceTest {
	
	List<TicketType> mockTicketTypes;
	TicketType mockTicketType;
	
	@InjectMocks
	private TicketTypeService ticketTypeService;
	
	@Mock
	private TicketTypeDao ticketTypeDao;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockTicketTypes = getDummyTicketTypes();
		mockTicketType = getDummyTicketType();
	}
	
	@Test
	public void testFindAllTicketypes() {
		when(ticketTypeDao.findAll()).thenReturn(mockTicketTypes);
		List<TicketType> ticketTypes = ticketTypeService.findAll();
		assertEquals(3, ticketTypes.size());
	}
	
	@Test
	public void testAddTicketType() {
		when(ticketTypeDao.add(mockTicketType)).thenReturn(mockTicketType);
		TicketType ticketType = ticketTypeService.add(mockTicketType);
		assertNotNull(ticketType);
	}
	
	@Test
	public void testFindTickettypeById() {
		when(ticketTypeDao.findById((long)1)).thenReturn(mockTicketType);
		TicketType ticketType = ticketTypeService.findById((long)1);
		assertEquals((long) 1, (long) ticketType.getId());
	}
	
	@Test
	public void testUpdateTicketType() {
		TicketType updateTt = new TicketType((long)1, "child", 10.0);
		doAnswer((i)->{
			TicketType tt = i.getArgument(0);
			assertTrue("child".equals(tt.getType()));
			return null;
		}).when(ticketTypeDao).update(updateTt);
		ticketTypeService.update(mockTicketType);
	}
	
	@Test
	public void testUpdateTicketTypePrice() {
		TicketType updateTt = new TicketType((long)1, "adult", 14.0);
		doAnswer((i)->{
			TicketType tt = i.getArgument(0);
			assertEquals(14.0, (double)tt.getPrice());
			return null;
		}).when(ticketTypeDao).update(updateTt);
		ticketTypeService.update(mockTicketType);
	}
	
	@Test
	public void testDeleteTicketType() {
		TicketType deleteTt = mockTicketTypes.get(0);
		doAnswer((i) -> {
			TicketType tt = ticketTypeService.findById(i.getArgument(0));
			assertNull(tt);
			return null;
		}).when(ticketTypeDao).deleteById(deleteTt.getId());
		ticketTypeService.deleteById(deleteTt.getId());
	}

	private TicketType getDummyTicketType() {
		return new TicketType((long)1, "adult", 10.0);
	}

	private List<TicketType> getDummyTicketTypes() {
		TicketType t1 = new TicketType((long)1, "adult", 10.0);
		TicketType t2 = new TicketType((long)2, "adult", 14.0);
		TicketType t3 = new TicketType((long)3, "adult", 15.0);
		List<TicketType> ticketTypes = new ArrayList<>();
		ticketTypes.add(t1);
		ticketTypes.add(t2);
		ticketTypes.add(t3);
		return ticketTypes;
	}
	
	
	

}
