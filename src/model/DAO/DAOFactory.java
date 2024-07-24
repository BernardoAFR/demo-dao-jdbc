package model.DAO;

import db.DB;
import model.DAO.impl.SellerDAOJDBC;

public class DAOFactory {
	
	public static  SellerDAO createSellerDao() {
		return new SellerDAOJDBC(DB.getConnection());
	}
}
