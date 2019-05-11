package cinema;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static util.PasswordHashing.*;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ttv.models.Account;
import com.ttv.models.Role;
import com.ttv.services.AccountService;
import com.ttv.services.RoleService;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("file:src/main/webapp/WEB-INF/applicationContext.xml")
public class AccountTest {

//	@Autowired
//	AccountService as;
//	
//	@Autowired
//	RoleService rs;
//	
//	@Test	
//	public void addAccount() throws NoSuchAlgorithmException, NoSuchProviderException{
//		long id = 1;
//		Long i = null;
//		Role r = new Role(id, rs.findById(id).getRoleName());
//		i = as.add(new Account((long)2100, "test2", "password", "test2F", "test2L","test2@gmail.com", r));
//	
//		assertNotNull(i);
//	}
	
//	@Test
//	public void getAllAccounts() {
//		List<Account> aList = as.findAll();
//		assertNotNull(aList);
//	}
//	
//	@Test 
//	public void findByIdTrue() { 
//		assertNotNull(as.findById((long)150));
//	}
//	@Test
//	public void findByIdFalse() {
//		assertNull(as.findById((long)1234567));
//	}
//	
//	@Test
//	public void update() throws NoSuchAlgorithmException, NoSuchProviderException { 
//		//updating firstname from test2F to test2Fname
//		long id = 1;
//		Role r = new Role(id, rs.findById(id).getRoleName());
//		Account a = new Account((long)2100, "test2", "password", "test2Fname", "test2Last","test2@gmail.com", r);
//		as.update(a);
//		assertEquals(a.getEmail(), as.findById((long) 2100).getEmail());
//	}
//	
//	@Test
//	public void delete() {
//		as.deleteById((long)2100);
//	}
//	
//	

}
