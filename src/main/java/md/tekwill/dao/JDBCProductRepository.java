package md.tekwill.dao;

import md.tekwill.entity.product.Drink;
import md.tekwill.entity.product.Food;
import md.tekwill.entity.product.FoodCategory;
import md.tekwill.entity.product.Product;
import org.postgresql.ds.PGSimpleDataSource;
import org.postgresql.ds.common.BaseDataSource;

import java.sql.*;
import java.util.List;

public class JDBCProductRepository implements ProductRepository{
    private static final String CONNECTION_URL = "jdbc:postgresql://localhost:5432/ProductManagerDB";
    private static final String USER = "postgres";
    private static final String PASSWORD = "11235813";

    private final BaseDataSource dataSource;

    public JDBCProductRepository(){
        this.dataSource = new PGSimpleDataSource();
        this.dataSource.setURL(CONNECTION_URL);
        this.dataSource.setUser(USER);
        this.dataSource.setPassword(PASSWORD);
    }

    @Override
    public void save(Product product) {
        final String insertSQL = "INSERT INTO public."+"\"Product\"(name, price, bestBefore, category, volume) VALUES(?,?,?,?,?)";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setDate(3, Date.valueOf(product.getBestBefore()));

            if (product instanceof Food) {
                Food food = (Food) product;
                preparedStatement.setString(4, food.getCategory().toString());
                preparedStatement.setDouble(5, 0.0);
            }
            if (product instanceof Drink) {
                Drink drink = (Drink) product;
                preparedStatement.setString(4, "");
                preparedStatement.setDouble(5, drink.getVolume());
            }

            int row = preparedStatement.executeUpdate();
            System.out.println("Inserted " + row + " row(s)!");

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            while (generatedKeys.next()) {
                System.out.println("Created product with " + generatedKeys.getInt(1) + " id ");
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Override
    public List<Product> findAll() {
        return null;
    }

    @Override
    public Product findById(int id) {
        return null;
    }

    @Override
    public Product findByName(String name) {
        return null;
    }

    @Override
    public void update(int id, double newPrice) {

    }

    @Override
    public void update(int id, FoodCategory newFoodCategory) {

    }

    @Override
    public void delete(int id) {

    }
}
