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

import com.ttv.daos.RoleDao;
import com.ttv.models.Account;
import com.ttv.models.Review;
import com.ttv.models.Role;
import com.ttv.models.Tmdb;
import com.ttv.services.RoleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:testApplicationContext.xml")
public class RoleServiceTest {
	List<Role> mockRoles;
	Role mockRole;
	
	@InjectMocks
	private RoleService roleService;
	
	@Mock 
	RoleDao roleDao;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockRoles = getMockRoles();
		mockRole = getMockRole();
	}
	
	@Test
	public void testGetAllRoles() {
		when(roleDao.findAll()).thenReturn(mockRoles);
		List<Role> roles = roleService.findAll();
		assertEquals(3, roles.size());
	}
	
	@Test
	public void testAddRole() {
		when(roleDao.add(mockRole)).thenReturn(mockRole);
		Role role = roleService.add(mockRole);
		assertEquals((long) 1, (long)role.getId());
	}
	
	@Test
	public void testFindRoleById() {
		when(roleDao.findById(mockRole.getId())).thenReturn(mockRole);
		Role role = roleService.findById((long)1);
		assertEquals((long) 1, (long)role.getId());
	}
	
	@Test
	public void testUpdateRole() {
		
		Role rUpdate = mockRoles.get(1);
		rUpdate.setRoleName("roleUpdate");
		doAnswer((i) ->{
			Role role = i.getArgument(0);
			assertTrue("roleUpdate".equals(role.getRoleName()));
			return null;
		}).when(roleDao).update(rUpdate);
		roleService.update(rUpdate);
	}
	
	@Test
	public void testDeleteRole() {
		Role deleteRole = mockRoles.get(1);
		doAnswer((i) ->{
			Role r = roleService.findById(i.getArgument(0));
			assertNull(r);
			return null;
		}).when(roleDao).deleteById(deleteRole.getId());
		roleService.deleteById(deleteRole.getId());
	}
	
	private List<Role> getMockRoles() {
		Role r1 = new Role((long)1 , "role1");
		Role r2 = new Role((long)2 , "role2");
		Role r3 = new Role((long)3 , "role3");
		
		List<Role> roles = new ArrayList<>();
		roles.add(r1);
		roles.add(r2);
		roles.add(r3);
		return roles;
	}
	
	private Role getMockRole() {
		return new Role((long)1 , "role1");
	}
}
