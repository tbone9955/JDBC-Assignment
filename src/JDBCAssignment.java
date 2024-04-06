import java.sql.*;
import java.util.*;


public class JDBCAssignment {

    // JDBC URL, username, and password of MySQL server
    private static final java.lang.String JDBC_CONNECTION_URL = "jdbc:mysql://localhost:3306/MySQL";
    private static final java.lang.String USERNAME = "root";

    private static final java.lang.String PASSWORD = "YourPassword";

    // JDBC variables for  connections
    private static Connection connection;
    private static java.sql.PreparedStatement preparedStatement;
    private static ResultSet resultSet;

    public static void main(String[] args) {
        try {
            // Connect to the  database
            connection = DriverManager.getConnection(JDBC_CONNECTION_URL, USERNAME, PASSWORD);


            boolean administerDB = true;
            Scanner scanner = new Scanner(System.in);
            while (administerDB) {
                System.out.println("1. View All Games in DB\n2. Find a single Game\n3. Update a Game\n4. Delete a Game\n5. Add a Game\n6. Quit");
                System.out.println("Please make a choice: ");

                if (scanner.hasNextInt()) {
                    int decision  = scanner.nextInt();

                    if (decision == 1)
                        readAllRecords();

                    if (decision == 2) {
                        System.out.println("Enter a gameId (integer): ");
                        if (scanner.hasNextInt()) {
                            int number = scanner.nextInt();
                            if (number > 0)
                                readRecord(number);
                        }
                        else {
                            System.out.println("Error: Input is not an integer.");
                            scanner.next();
                        }
                    }

                    if (decision == 3) {
                        System.out.println("Enter comma-separated values to update an existing record (by gameId as a key): ");
                        System.out.println("<gameId>, <name of game>, <game type>, <isMultiPlayer> as 0 or 1");
                        scanner = new Scanner(System.in);
                        String input = scanner.nextLine();
                        String[] fields = input.split(",\\s*");  //split using commas
                        if (fields.length >= 4) {    // Check if array has at least 4 elements
                            try {
                                int gameId = Integer.parseInt(fields[0].trim());
                                int isMulti = Integer.parseInt(fields[3].trim());

                                updateRecord(gameId, fields[1], fields[2], isMulti);
                            } catch (NumberFormatException e) {
                                System.out.println("Error: Invalid integer format in fields[0]");
                                // Handle error
                            }
                        } else {
                            System.out.println("Error: Insufficient fields in the input");
                            // Handle error
                        }
                    }

                    if (decision == 4) {
                        System.out.println("Enter a gameId (integer) to delete: ");
                        int number = scanner.nextInt();
                        if (number > 0)
                            deleteRecord(number);
                    }

                    if (decision == 5) {
                        System.out.println("Enter comma-separated values to Add a new Game: ");
                        System.out.println("<gameId> as integer, <name of game> as string, <game type> as string, <isMultiPlayer> as 0 or 1");
                        scanner = new Scanner(System.in);
                        String input = scanner.nextLine();
                        String[] fields = input.split(",\\s*");  //split using commas
                        if (fields.length >= 4) {    // Check if array haas at least 4 elements
                            try {
                                int gameId = Integer.parseInt(fields[0].trim());
                                int isMulti = Integer.parseInt(fields[3].trim());
                                insertRecord(gameId, fields[1], fields[2], isMulti);;
                            } catch (NumberFormatException e) {
                                System.out.println("Error: Invalid integer format in fields[0]");
                                // Handle error
                            }
                        } else {
                            System.out.println("Error: Insufficient fields in the input");
                        }
                    }

                    if (decision == 6) {    //  Quit the game
                        administerDB = false;
                        System.out.print("\n Good bye!\n ");
                    }
                }
                else {
                    System.out.println("Error: Input is not an integer.");
                    scanner.next();
                }
            }

            scanner.close();  // clean up

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close JDBC objects as a last step
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void readRecord(int gameId) throws SQLException {
        java.lang.String selectRecordSQL = "SELECT * FROM GamesDB.games where gameId = ?";
        preparedStatement = connection.prepareStatement(selectRecordSQL);
        preparedStatement.setInt(1, gameId);
        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            System.out.println("---- Results of ReadRecord where gameId = " + gameId + " ----");
            System.out.println("GameId: " + resultSet.getInt("gameId"));
            System.out.println("Name: " + resultSet.getString("name"));
            System.out.println("gameType: " + resultSet.getString("gameType"));
            System.out.println("isMultiPlayer: " + resultSet.getInt("isMultiPlayer"));
            System.out.println("------------------------------------------------------------------------------------------");
        } else {
            System.out.println("Record not found!");
        }
    }

    private static void insertRecord(int gameId, String name, String gameType, int isMultiPlayer) throws SQLException {
        String insertRecordSQL = "INSERT INTO GamesDB.games (gameId, name, gameType, isMultiPlayer) VALUES (?, ?, ?, ?)";
        preparedStatement = connection.prepareStatement(insertRecordSQL);
        preparedStatement.setInt(1, gameId);
        preparedStatement.setString(2, name);
        preparedStatement.setString(3, gameType);
        preparedStatement.setInt(4, isMultiPlayer);
        int rowsAffected = preparedStatement.executeUpdate();
        System.out.println(rowsAffected + " row(s) inserted.");
    }

    private static void readAllRecords() throws SQLException {
        String selectAllRecordsSQL = "SELECT * FROM GamesDB.games";
        preparedStatement = connection.prepareStatement(selectAllRecordsSQL);
        resultSet = preparedStatement.executeQuery();

        System.out.println("------------------ Results of ReadAllRecords ----------------------------");

        while (resultSet.next()) {
            System.out.println("GameId: " + resultSet.getInt("gameId"));
            System.out.println("Name: " + resultSet.getString("name"));
            System.out.println("gameType: " + resultSet.getString("gameType"));
            System.out.println("isMultiPlayer: " + resultSet.getInt("isMultiPlayer"));
            System.out.println("------------------------------------------------------------------------------------------");
        }
    }

    private static void updateRecord(int gameId, String name, String gameType, int isMultiPlayer) throws SQLException {
        String updateRecordSQL = "UPDATE GamesDB.games SET name=?, gameType=?, isMultiPlayer=? WHERE gameId=?";
        preparedStatement = connection.prepareStatement(updateRecordSQL);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, gameType);
        preparedStatement.setInt(3, isMultiPlayer);
        preparedStatement.setInt(4, gameId);
        int rowsAffected = preparedStatement.executeUpdate();
        System.out.println(rowsAffected + " row(s) updated successfully.");
    }

    private static void deleteRecord(int gameId) throws SQLException {
        String deleteRecordSQL = "DELETE FROM GamesDB.games WHERE gameId=?";
        preparedStatement = connection.prepareStatement(deleteRecordSQL);
        preparedStatement.setInt(1, gameId);
        int rowsAffected = preparedStatement.executeUpdate();
        System.out.println(rowsAffected + " row(s) deleted.");
    }

}
