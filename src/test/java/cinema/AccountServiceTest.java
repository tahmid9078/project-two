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

import com.ttv.daos.AccountDao;
import com.ttv.models.Account;
import com.ttv.models.Role;
import com.ttv.services.AccountService;
import com.ttv.services.RoleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:testApplicationContext.xml")
public class AccountServiceTest {
	
	List<Account> mockAccounts;
	Account mockAccount;
	
	@InjectMocks
	private AccountService accountService;
	
	@Mock
	private AccountDao accountDao;
	
	@Mock
	private RoleService roleService;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockAccounts = getDummyAccounts();
		mockAccount = getDummyAccount();
	}
		
	@Test
	public void testFindAllAccounts() {
		when(accountDao.findAll()).thenReturn(mockAccounts);
		List<Account> accounts = accountService.findAll();
		assertEquals(3, accounts.size());
	}
	
	@Test
	public void testAddAccount() {
		Account mock = new Account((long)2, "username", "password", "firstName", "lastName", "email", null);
		when(accountDao.add(mock)).thenReturn(mock.getId());
		when(roleService.findById((long)1)).thenReturn(new Role((long)1 , "role1"));
		long id = accountService.add(mock);
		assertEquals((long) 2, id);
	}
	
	@Test
	public void testFindAccountById() {
		Account a2 = new Account((long)2, "username2", "password2", "firstName2", "lastName2", "email2", new Role((long)2 , "role2"));
		when(accountDao.findById((long)2)).thenReturn(a2);
		Account account = accountService.findById((long)2);
		assertEquals((long) 2, (long) account.getId());
	}
	
	@Test
	public void testUpdateAccount() {
		Account a2 = new Account((long)2, "username2", "password2", "firstName2", "lastName2", "email2", new Role((long)2 , "role2"));
		when(accountDao.findById((long)2)).thenReturn(a2);
		a2.setUsername("usernameUpdate");
		doAnswer((i)->{
			Account a = i.getArgument(0);
			assertTrue("usernameUpdate".equals(a.getUsername()));
			return null;
		}).when(accountDao).update(a2);
		accountService.update(a2);
	}
	
	@Test
	public void testDeleteAccount() {
		Account deleteAccount = mockAccounts.get(1);
		doAnswer((i) ->{
			Account a = accountService.findById(i.getArgument(0));
			assertNull(a);
			return null;
		}).when(accountDao).deleteById(deleteAccount.getId());
		accountService.deleteById(deleteAccount.getId());
	}
	
	
	private List<Account> getDummyAccounts() {
		Account a1 = new Account((long)1, "username1", "password1", "firstName1", "lastName1", "email1", new Role((long)1 , "role1"));
		Account a2 = new Account((long)2, "username2", "password2", "firstName2", "lastName2", "email2", new Role((long)2 , "role2"));
		Account a3 = new Account((long)3, "username3", "password3", "firstName3", "lastName3", "email3", new Role((long)3 , "role3"));
		List<Account> accounts = new ArrayList<>();
		accounts.add(a1);
		accounts.add(a2);
		accounts.add(a3);
		return accounts;
	}
	
	private Account getDummyAccount() {
		Account a = new Account((long)1, "username", "password", "firstName", "lastName", "email", new Role((long)1 ,"role1"));
		return a;
	}
	
}
