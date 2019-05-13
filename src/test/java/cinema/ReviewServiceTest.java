package cinema;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
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

import com.ttv.daos.ReviewDao;
import com.ttv.models.Account;
import com.ttv.models.Review;
import com.ttv.models.Role;
import com.ttv.models.Tmdb;
import com.ttv.services.ReviewService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:testApplicationContext.xml")
public class ReviewServiceTest {

	List<Review> mockReviews;
	Review mockReview;
	
	@InjectMocks
	private ReviewService reviewService;
	
	@Mock ReviewDao reviewDao;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockReviews = getMockReviews();
		mockReview = getMockReview();
	}
	
	@Test
	public void testGetAllReviews() {
		when(reviewDao.findAll()).thenReturn(mockReviews);
		List<Review> roles = reviewService.findAll();
		assertEquals(3, roles.size());
	}
	
	@Test
	public void testAddReview() {
		when(reviewDao.add(mockReview)).thenReturn(mockReview);
		Review review = reviewService.add(mockReview);
		assertEquals((long) 1, (long)review.getId());
	}
	
	@Test
	public void testFindReviewById() {
		when(reviewDao.findById(mockReview.getId())).thenReturn(mockReview);
		Review review = reviewService.findById((long)1);
		assertEquals((long) 1, (long)review.getId());
	}
	
	@Test
	public void testUpdateReview() {
		
		Review rUpdate = mockReviews.get(1);
		rUpdate.setReview("reviewUpdate");
		doAnswer((i) ->{
			Review review = i.getArgument(0);
			assertTrue("reviewUpdate".equals(review.getReview()));
			return null;
		}).when(reviewDao).update(rUpdate);
		reviewService.update(rUpdate);
	}
	
	@Test
	public void testDeleteReview() {
		Review deleteReview = mockReviews.get(1);
		doAnswer((i) ->{
			Review a = reviewService.findById(i.getArgument(0));
			assertNull(a);
			return null;
		}).when(reviewDao).deleteById(deleteReview.getId());
		reviewService.deleteById(deleteReview.getId());
	}
	
	private List<Review> getMockReviews() {
		Account a1 = new Account((long)1, "username1", "password1", "firstName1", "lastName1", "email1", new Role((long)1 , "role1"));
		Account a2 = new Account((long)2, "username2", "password2", "firstName2", "lastName2", "email2", new Role((long)2 , "role2"));
		Account a3 = new Account((long)3, "username3", "password3", "firstName3", "lastName3", "email3", new Role((long)3 , "role3"));
		
		Tmdb t1 = new Tmdb((long)1, "movieApiId1");
		Tmdb t2 = new Tmdb((long)2, "movieApiId2");
		Tmdb t3 = new Tmdb((long)3, "movieApiId3");
		
		Review r1 = new Review((long)1, 
				a1,
				t1,
				"review1");
		Review r2 = new Review((long)2,
				a2,
				t2,
				"review2");
		Review r3 = new Review((long)3,
				a3,
				t3,
				"review3");
		
		List<Review> reviews = new ArrayList<>();
		reviews.add(r1);
		reviews.add(r2);
		reviews.add(r3);
		return reviews;
	}
	
	private Review getMockReview() {
		Account a1 = new Account((long)1, "username1", "password1", "firstName1", "lastName1", "email1", new Role((long)1 , "role1"));
		Tmdb t1 = new Tmdb((long)1, "movieApiId1");
		
		return new Review((long)1, a1, t1, "review1");
	}
	
}
