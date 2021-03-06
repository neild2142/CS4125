/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseManagement;

import Business.Product.Product;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Benjamin Grimes
 */
public class ProductDAO implements DAO 
{
    private static Connection connection = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;
    private static PreparedStatement preparedStatement = null;

    @Override
    public String select(Object o) 
    {
        String result = "";
        
        return result;
    }

    @Override
    public boolean insert(Object o) 
    {
        boolean inserted = false;
        
        return inserted;
    }

    @Override
    public boolean update(Object o) 
    {
        boolean updated = false;
        
        return updated;
    }

    @Override
    public boolean delete(Object o) 
    {
        boolean deleted = false;
        
        return deleted;
    }
    
    public Product[] getListProducts(int cID)
    {
        ArrayList<Product> products = new ArrayList<Product>();
        
        try
        {
            connection = DriverManager.getConnection(JDBC_URL);
            statement = connection.createStatement();
            String query = "Select * "
                    + "FROM BeanSquadRentalDB.Products "
                    + "WHERE catID = " + cID + " AND is_available = 1";
            
            resultSet = statement.executeQuery(query);
            
            int pID;
            int ownerID;
            String pName;
            String description;
            int is_available;
            int catID;
            double price;
            int rating;
            
            while(resultSet.next())
            {
                pID = resultSet.getInt("pID");
                ownerID = resultSet.getInt("ownerID");
                pName = resultSet.getString("pName");
                description = resultSet.getString("description");
                is_available = resultSet.getInt("is_available");
                catID = resultSet.getInt("catID");
                price = resultSet.getDouble("price");
                rating = resultSet.getInt("rating");
                products.add(new Product(pID, pName, catID, ownerID, price, rating, description, is_available));
            }
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        Product [] tmp = new Product[products.size()];
        
        for(int i = 0; i < tmp.length; i++)
            tmp[i] = products.get(i);
        
        return tmp;
    }
    
    public int getLeaserID(int productID)
    {
        int leaserID = -1;
        try{
            connection = DriverManager.getConnection(JDBC_URL);
            statement = connection.createStatement();
            String query = "SELECT ownerID "
                    + "FROM BeanSquadRentalDB.Products "
                    + "WHERE pID = " + productID + ";";
            resultSet = statement.executeQuery(query);
            if(resultSet.next())
            {
                return resultSet.getInt("ownerID");
            }
            close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return leaserID;
    }
    
    public void setProductAvailability(int pID, int available)
    {
        try
        {
            connection = DriverManager.getConnection(JDBC_URL);
            statement = connection.createStatement();
            
            String query = "UPDATE BeanSquadRentalDB.Products "
                    + "SET is_available = " + available
                    + " WHERE pID = '" + pID + "' ;";  
            
            statement.executeUpdate(query);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    
    @Override
    public void close()
    {
        try{
        if(connection != null)
            connection.close();
        if(statement != null)
            statement.close();
        if(resultSet != null)
            resultSet.close();
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }
}
