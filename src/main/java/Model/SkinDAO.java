package Model;

public interface SkinDAO {
	public boolean insertSkin(Skin skin, String table);

	public int searchSkin(String sqlcommand);

	public boolean deleteSkin(String sqlCommand);

	public boolean updateSkinPrice(Skin skin, String table, double newPrice);

	public String getSkinLink(Skin skin);
}
