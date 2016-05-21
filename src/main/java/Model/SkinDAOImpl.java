package Model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;*/

public class SkinDAOImpl implements SkinDAO {

	public Connection c;
	public List<Skin> searchedSkin = new ArrayList<Skin>();
	private static Logger logger = LoggerFactory.getLogger(SkinDAOImpl.class);

	public SkinDAOImpl(String filename) {
		try {
			c = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/steam", "orotib", "jelszo");
			// c
			// =DriverManager.getConnection("jdbc:oracle:thin:@db.inf.unideb.hu:1521:ora11g","h_am9twe",
			// "kassai");

			initDB(filename);
		} catch (SQLException e) {
			// System.out.println("Server is not available!");
			logger.error("Server is not available!");
			System.exit(-1);
		}
		// EntityManagerFactory factory =
		// Persistence.createEntityManagerFactory("hsqldb-eclipselink");
		// EntityManager manager = factory.createEntityManager();
	}

	private void initDB(String filename) {
		try {
			Scanner in = new Scanner(new FileReader(filename));
			while (in.hasNextLine()) {
				try {
					c.prepareStatement(in.nextLine()).execute();
				} catch (SQLException e) {
					// System.out.println(e.getMessage());
					logger.error("SQLException caught: ", e);
					System.exit(-1);
				}
			}
			in.close();
		} catch (FileNotFoundException e) {
			// System.out.println(e.getMessage());
			logger.error("FileNotFoundException caught: ", e);
			System.exit(-2);
		}

	}

	@Override
	public boolean insertSkin(Skin skin, String table) {
		try {
			PreparedStatement st = c.prepareStatement("INSERT INTO " + table + " VALUES(?, ?, ?, ?, ?, ?);");
			st.setString(1, skin.getCollection());
			st.setString(2, skin.getType().name());
			st.setString(3, skin.getWeapon());
			st.setString(4, skin.getSkin());
			st.setString(5, skin.getCondition().name());
			st.setDouble(6, skin.getAvgPrice());
			st.execute();
		} catch (SQLException e) {
			// System.out.println("The database already contains.");
			return false;
		}
		return true;
	}

	@Override
	public int searchSkin(String sqlcommand) {
		searchedSkin = new ArrayList<Skin>();
		try {
			ResultSet rs = c.prepareStatement(sqlcommand).executeQuery();
			while (rs.next()) {
				String tipus = rs.getString(2);
				String tmp = rs.getString(5);
				Condition con = null;
				if (tmp.contains("-"))
					con = Condition.valueOf(tmp.split("-")[0].toUpperCase());
				else
					con = Condition.valueOf(tmp.split(" ")[0].toUpperCase());

				searchedSkin.add(new Skin(rs.getString(1), Type.valueOf(tipus.toUpperCase()), rs.getString(3),
						rs.getString(4), con, rs.getDouble(6)));
			}
		} catch (SQLException e) {
			// System.out.println("SQLException 100 " + e.getMessage());
			logger.error("SQLException caught: ", e);
		}
		return searchedSkin.size();
	}

	@Override
	public boolean deleteSkin(String sqlCommand) {
		try {
			c.prepareStatement(sqlCommand).executeUpdate();
		} catch (SQLException e) {
			// System.out.println("The database already contains.");
			return false;
		}
		return true;
	}

	@Override
	public boolean updateSkinPrice(Skin skin, String table, double newPrice) {
		StringBuilder b = new StringBuilder();
		b.append("UPDATE ").append(table).append(" SET PRICE=").append(newPrice).append(" WHERE ");
		b.append("COLLECTION='").append(skin.getCollection());
		b.append("' AND TIPUS='").append(skin.getType().name());
		b.append("' AND WEAPON='").append(skin.getWeapon());
		b.append("' AND SKIN='").append(skin.getSkin());
		b.append("' AND CONDITION='").append(skin.getCondition().name());
		b.append("';");

		try {
			c.prepareStatement(b.toString()).executeUpdate();
		} catch (SQLException e) {
			// System.out.println("Update error!");
			logger.error("Update error!");
			return false;
		}
		return true;
	}

	@Override
	public String getSkinLink(Skin skin) {
		StringBuilder b = new StringBuilder();
		b.append("http://steamcommunity.com/market/listings/730/");
		if (skin.getType().equals(Type.STATTRAK))
			b.append("StatTrak%E2%84%A2%20");
		else if (skin.getType().equals(Type.SOUVENIR))
			b.append("Souvenir%20");
		else
			;
		b.append(skin.getWeapon()).append("%20%7C%20");
		b.append(skin.getSkin()).append("%20%28");
		b.append(skin.getCondition().getValue()).append("%29");
		return b.toString().replace(" ", "%20");
	}
}
