import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.time.Instant;
import java.util.Random;

public class QueryExecutor {
    private static String[] RandomWords = new String[] {"bloodshed", "single", "bench", "bride", "frank", "tribe",
            "practical", "limit", "relaxation", "achievement", "temporary", "insert", "message", "cute", "grudge",
            "characteristic", "host", "extraterrestrial", "crew", "chair", "hill", "prescription", "level", "refuse",
            "cunning", "extend", "classify", "will", "attachment", "imposter", "fail", "literature", "admit",
            "declaration", "lung", "rare", "wriggle", "supply", "chaos", "hunter", "tree", "miracle", "ignite", "deny",
            "scramble", "protect", "sodium", "certain", "prayer", "brink"};

    public static void InsertQuery(int n){
        Random rand = new Random();
        int value = RandomWords.length;
        StringBuilder query = new StringBuilder("INSERT INTO public.BookTest(\"bookid\", \"booktitle\") VALUES ");
        for(int i = 0; i < n; i++){
            query.append("("+ (i+1) +", '"+ RandomWords[rand.nextInt(value)] +"'), ");
        }
        query.append("(1000001, 'unicorn')");
        String SQLCode = query.toString();
        Instant startTime = Instant.now();
        try{
            Connection connection = DbConnector.connect();
            Statement statement = connection.createStatement();
            statement.execute(SQLCode);
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
        Instant endTime = Instant.now();
        System.out.println("Inserts - time: " + Duration.between(startTime, endTime).toMillis());
    }

    public static void Query(String anyQuery){
        Instant startTime = Instant.now();
        try{
            Connection connection = DbConnector.connect();
            Statement statement = connection.createStatement();
            statement.execute(anyQuery);
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
        Instant endTime = Instant.now();
        System.out.println("Query - time: " + Duration.between(startTime, endTime).toMillis());
    }

    public static ResultSet Select(String SelectQuery){
        try{
            Connection connection = DbConnector.connect();
            Statement statement = connection.createStatement();
            return statement.executeQuery(SelectQuery);
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
