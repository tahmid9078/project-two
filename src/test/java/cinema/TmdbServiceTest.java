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

import com.ttv.daos.TmdbDao;
import com.ttv.models.Account;
import com.ttv.models.Role;
import com.ttv.models.Tmdb;
import com.ttv.services.TmdbService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:testApplicationContext.xml")
public class TmdbServiceTest {

	List<Tmdb> mockTmdbs;
	Tmdb mockTmdb;
	
	@InjectMocks
	private TmdbService tmdbService;
	
	@Mock
	private TmdbDao tmdbDao;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockTmdbs = getDummyTmdbs();
		mockTmdb = getDummyTumdb();
	}

	@Test
	public void testFindAllTmdbs() {
		when(tmdbDao.findAll()).thenReturn(mockTmdbs);
		List<Tmdb> tmdbs = tmdbService.findAll();
		assertEquals(3, tmdbs.size());
	}
	
	@Test
	public void testAddTmdb() {
		when(tmdbDao.add(mockTmdb)).thenReturn(mockTmdb);
		Tmdb tmdb = tmdbService.add(mockTmdb);
		assertEquals((long) 1, (long)tmdb.getId());
	}

	@Test
	public void testFindTmdbById() {
		when(tmdbDao.findById((long)1)).thenReturn(mockTmdb);
		Tmdb tmdb = tmdbService.findById((long)1);
		assertEquals((long) 1, (long) tmdb.getId());
	}
	
	@Test
	public void testUpdateTmdb() {
		Tmdb mock = new Tmdb((long)1 , "1144");
		doAnswer((i)->{
			Tmdb tmdb = i.getArgument(0);
			assertTrue("1144".equals(tmdb.getMovieApiId()));
			return null;
		}).when(tmdbDao).update(mock);
		tmdbService.update(mockTmdb);
	}
	
	@Test
	public void testDeleteAccount() {
		Tmdb tmdb = mockTmdbs.get(1);
		doAnswer((i) -> {
			Tmdb t = tmdbService.findById(i.getArgument(0));
			assertNull(t);
			return null;
		}).when(tmdbDao).deleteById(tmdb.getId());
		tmdbService.deleteById(tmdb.getId());
	}
	
	
	private Tmdb getDummyTumdb() {
		return new Tmdb((long)1, "1131");
	}

	private List<Tmdb> getDummyTmdbs() {
		Tmdb t1 = new Tmdb((long)1, "1131");
		Tmdb t2 = new Tmdb((long)1, "1131");
		Tmdb t3 = new Tmdb((long)1, "1131");
		List<Tmdb> tmdbs = new ArrayList<>();
		tmdbs.add(t1);
		tmdbs.add(t2);
		tmdbs.add(t3);
		return tmdbs;
	}
}
