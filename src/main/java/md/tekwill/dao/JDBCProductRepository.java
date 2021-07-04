package md.tekwill.dao;

import md.tekwill.entity.product.Drink;
import md.tekwill.entity.product.Food;
import md.tekwill.entity.product.FoodCategory;
import md.tekwill.entity.product.Product;
import org.postgresql.ds.PGSimpleDataSource;
import org.postgresql.ds.common.BaseDataSource;
import sun.rmi.server.LoaderHandler;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JDBCProductRepository implements ProductRepository {
    private static final String CONNECTION_URL = "jdbc:postgresql://localhost:5432/product-manager";
    private static final String USER = "postgres";
    private static final String PASSWORD = "11235813";

    private final BaseDataSource dataSource;

    public JDBCProductRepository() {
        this.dataSource = new PGSimpleDataSource();
        this.dataSource.setURL(CONNECTION_URL);
        this.dataSource.setUser(USER);
        this.dataSource.setPassword(PASSWORD);
    }

    @Override
    public void save(Product product) {
        final String insertSQL = "INSERT INTO product(name, price, best_before, category, volume) VALUES(?,?,?,?,?)";
        try (Connection connection = dataSource.getConnection();
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
                preparedStatement.setNull(4, 12);//preparedStatement.setString(4, null);
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
        Product product = null;
        List<Product> productList = new ArrayList<>();
        String selectSQL = "SELECT id, name, price, best_before, category, volume FROM product";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                LocalDate bestBefore = resultSet.getDate("best_before").toLocalDate();
                String category = resultSet.getString("category");
                double volume = resultSet.getDouble("volume");

                if (category != null) {
                    product = new Food(name, price, bestBefore, FoodCategory.valueOf(category));
                }

                if (volume != 0.0) {
                    product = new Drink(name, price, bestBefore, volume);
                }

                if (product != null) {
                    product.setId(id);
                }
                productList.add(product);
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return productList;
    }

    @Override
    public Product findById(int idToFind) {
        Product product = null;
        String selectSQL = "SELECT id, name, price, best_before, category, volume FROM product where id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
            preparedStatement.setInt(1, idToFind);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                LocalDate bestBefore = resultSet.getDate("best_before").toLocalDate();
                String category = resultSet.getString("category");
                double volume = resultSet.getDouble("volume");

                if (category != null) {
                    product = new Food(name, price, bestBefore, FoodCategory.valueOf(category));
                }
                if (volume != 0.0) {
                    product = new Drink(name, price, bestBefore, volume);
                }
                if (product != null) {
                    product.setId(id);
                }
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return product;
    }

    @Override
    public Product findByName(String nameToFind) {
        Product product = null;
        String selectSQL = "SELECT id, name, price, best_before, category, volume FROM product where name = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
            preparedStatement.setString(1, nameToFind);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                LocalDate bestBefore = resultSet.getDate("best_before").toLocalDate();
                String category = resultSet.getString("category");
                double volume = resultSet.getDouble("volume");

                if (category != null) {
                    product = new Food(name, price, bestBefore, FoodCategory.valueOf(category));
                }

                if (volume != 0.0) {
                    product = new Drink(name, price, bestBefore, volume);
                }

                if (product != null) {
                    product.setId(id);
                }
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return product;
    }

    @Override
    public void update(int idToFind, double volumeToUpdate) {
        String selectSQL = "UPDATE product SET volume=? where id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
            preparedStatement.setDouble(1, volumeToUpdate);
            preparedStatement.setInt(2, idToFind);

            findById(idToFind);
            preparedStatement.executeQuery();

        } catch (SQLException sqlException) {
            //sqlException.printStackTrace();
        }
    }

    @Override
    public void update(int idToFind, FoodCategory newFoodCategory) {
        String selectSQL = "UPDATE product SET category=? where id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
            preparedStatement.setString(1, String.valueOf(newFoodCategory));
            preparedStatement.setInt(2, idToFind);

            findById(idToFind);
            preparedStatement.executeQuery();

        } catch (SQLException sqlException) {
            //sqlException.printStackTrace();
        }
    }

    @Override
    public void delete(int idToFind) {
        String selectSQL = "DELETE FROM product where id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
            preparedStatement.setInt(1, idToFind);

            findById(idToFind);
            preparedStatement.executeQuery();

        } catch (SQLException sqlException) {
            //sqlException.printStackTrace();
        }
    }
}
