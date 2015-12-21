//package com.restaurant.servicesTests;
//
//import java.time.Clock;
//import java.time.LocalDateTime;
//import java.time.ZoneId;
//
//import javax.inject.Inject;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.test.context.transaction.TransactionConfiguration;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.restaurant.DAO.LoginDAOImpl;
//import com.restaurant.entities.User;
//
////@EnableJpaRepositories(value="com.restaurant.DAO.LoginDAOImpl")
////@ContextConfiguration(classes = {LoginDAOImpl.class})
//@TransactionConfiguration(transactionManager="Restaurant", defaultRollback=true)
//@Transactional
//public class LoginDAOTest extends LoginDAOImpl{
//	private final String correct_login="test_user";
//	private final String correct_pass="test_pass";
//	private final String incorrect_pass="incorr_pass";
//	private int test_id;
//    private static ZoneId zoneId = ZoneId.systemDefault();
//	private static Clock clock;
//    private User user;
////    private EntityManagerFactory emf; 
////    private EntityManager em; 
//	@Inject
//	private LoginDAOImpl loginDAO;
//	@PersistenceContext
//	protected EntityManager em; 
//
//	@Before 
//	public void setUp() {
////		emf = Persistence.createEntityManagerFactory("Restaurant"); 
////		em = emf.createEntityManager(); 
//		createTestData(); 
//	} 
//	@After 
//	public void tearDown() { 
//		/*if (em != null) { */
//			removeTestData(); 
////		} 
//	} 
//	
//	private void createTestData() { 
//		User user = new User(); 
//		user.setName(correct_login); 
//		user.setPassword(correct_pass); 
////		user.setDateOfLastLogin(LocalDateTime.now(clock));
//		em.getTransaction().begin(); 
//		em.persist(user); 
//		em.getTransaction().commit(); 
//		test_id=user.getId();
//		clock = Clock.fixed(LocalDateTime.now().atZone(zoneId).toInstant(), zoneId);
//		}
//	
//	private void removeTestData() { 
//		User user = loginDAO.getEm().find(User.class, test_id); 
//		if (user != null) { 
//			em.remove(user); 
//		} 
//	}	
//	
//	@Test 
//	public void testLoginSuccess() throws Exception { 
//		User user = loginDAO.login(correct_login, correct_pass);
//		Assert.assertNotNull(user);
//		Assert.assertEquals(correct_login, user.getLogin());	
//		Assert.assertEquals(correct_pass, user.getPassword());
//	} 
//	@Test 
//	public void testLoginUnsuccess() throws Exception { 
//		User user = loginDAO.login(correct_login, incorrect_pass);
//		Assert.assertNull(user);
//	} 
//	
//	/*@Test
//	public void testSetDateOfLastLogin(LocalDateTime time){
//		loginDAO.setDateOfLastLogin(LocalDateTime.now(clock));
//		Assert.assertEquals(user.getDateOfLastLogin(), LocalDateTime.now(clock));
//	}*/
//	
//	public int getTest_id() {
//		return test_id;
//	}
//	public void setTest_id(int test_id) {
//		this.test_id = test_id;
//	}
//	public String getCorrect_login() {
//		return correct_login;
//	}
//	public String getCorrect_pass() {
//		return correct_pass;
//	}
//	public String getIncorrect_pass() {
//		return incorrect_pass;
//	}
//	public static Clock getClock() {
//		return clock;
//	}
//	public static void setClock(Clock clock) {
//		LoginDAOTest.clock = clock;
//	}
//	public static ZoneId getZoneId() {
//		return zoneId;
//	}
//	public static void setZoneId(ZoneId zoneId) {
//		LoginDAOTest.zoneId = zoneId;
//	}
//	public User getUser() {
//		return user;
//	}
//	public void setUser(User user) {
//		this.user = user;
//	}
//}
