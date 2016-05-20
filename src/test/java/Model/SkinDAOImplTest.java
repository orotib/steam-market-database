package Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class SkinDAOImplTest {

	private SkinDAOImpl DAO = new SkinDAOImpl(
			System.getProperty("user.dir") + "/src/test/resources/initializetest.sql");

	@Test
	public void getSkinLinkTest() {
		Skin testSkin = new Skin("Operation Phoenix", Type.NORMAL, "AWP", "Asiimov", Condition.FIELD, 31.77);
		assertEquals("http://steamcommunity.com/market/listings/730/AWP%20%7C%20Asiimov%20%28Field-Tested%29",
				DAO.getSkinLink(testSkin));
		testSkin = new Skin("Operation Phoenix", Type.STATTRAK, "AWP", "Asiimov", Condition.FIELD, 31.77);
		assertEquals(
				"http://steamcommunity.com/market/listings/730/StatTrak%E2%84%A2%20AWP%20%7C%20Asiimov%20%28Field-Tested%29",
				DAO.getSkinLink(testSkin));
		testSkin = new Skin("Operation Phoenix", Type.SOUVENIR, "AWP", "Asiimov", Condition.FIELD, 31.77);
		assertEquals(
				"http://steamcommunity.com/market/listings/730/Souvenir%20AWP%20%7C%20Asiimov%20%28Field-Tested%29",
				DAO.getSkinLink(testSkin));
	}

	@Test
	public void enumTest() {
		assertEquals("Battle-Scarred", Condition.BATTLE.getValue());
		assertEquals("StatTrak", Type.STATTRAK.getValue());
	}
	
	@Test
	public void searchSkinTest() {
		assertEquals(0, DAO.searchSkin("SELECT * FROM steamtest;"));
	}

	@Test
	public void deleteSkinTest() {
		assertTrue(DAO.deleteSkin("DELETE FROM steamtest WHERE weapon='AUG';"));
		assertFalse(DAO.deleteSkin("ASD"));
	}
	
	@Test
	public void updateSkinTest() {
		Skin skin = new Skin("Other", Type.NORMAL, "P250", "Asiimov", Condition.MINIMAL, 5.00);
		assertTrue(DAO.updateSkinPrice(skin, "steamtest", 5.5));
	}

	@Test
	public void skinTest() {
		Skin testSkin = new Skin("Operation Phoenix", Type.NORMAL, "AWP", "Asiimov", Condition.FIELD, 31.77);
		testSkin.setCollection("Other");
		assertEquals("Other", testSkin.getCollection());
		testSkin.setType(Type.SOUVENIR);
		assertEquals("Souvenir", testSkin.getType().getValue());
		testSkin.setWeapon("P250");
		assertEquals("P250", testSkin.getWeapon());
		testSkin.setSkin("Cerberus");
		assertEquals("Cerberus", testSkin.getSkin());
		testSkin.setCondition(Condition.FACTORY);
		assertEquals("Factory New", testSkin.getCondition().getValue());
		assertEquals("Other Souvenir P250 Cerberus Factory New 31.77",
				testSkin.toString());
		testSkin.setAvgPrice(30.0);
	}

	@Test
	public void insertSkinTest() {
		Skin testSkin = new Skin("Operation Phoenix", Type.NORMAL, "AWP", "Asiimov", Condition.FIELD, 31.77);
		assertTrue(DAO.insertSkin(testSkin, "steamtest"));
		assertFalse(DAO.insertSkin(testSkin, "ASD"));
	}

}
