import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;

public class Main {
    public static void main(String[] args){
        //---------------------- INSERT ----------------------//
        //QueryExecutor.InsertQuery(1000000);

        //---------------------- SELECT ----------------------//
        Instant startTime = Instant.now();
        try{
            ResultSet result = QueryExecutor.Select("SELECT * FROM public.BookTest WHERE \"booktitle\" = 'chaos'");
            while(result.next()){
                int BookID = result.getInt("bookid");
                String BookTitle = result.getString("booktitle");
                System.out.println(BookID + ". " + BookTitle);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        Instant endTime = Instant.now();
        System.out.println("Select - time: " + Duration.between(startTime, endTime).toMillis());

        //---------------------- UPDATE ----------------------//
        QueryExecutor.Query("UPDATE public.BookTest SET \"booktitle\" = 'Monster' WHERE \"bookid\" < 20");
    }
}
