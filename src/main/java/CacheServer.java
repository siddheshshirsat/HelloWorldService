import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CacheServer {
    private ServerSocket serverSocket;
    private ExecutorService executorService;
    
    public CacheServer() throws IOException {
    	executorService = Executors.newFixedThreadPool(5);
        serverSocket = new ServerSocket(9001);
    }

    public void start() throws IOException {
    	while(true) {
    		Socket clientSocket = serverSocket.accept();
            executorService.submit(() -> {
    			try {
    				handleClient(clientSocket);
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    		});
            System.out.println("Reached...accepted a connection");
    	}
    }
    
    private String processGetRequest() {
    	return "{\"result\":\"{\\\"Title\\\":\\\"Inception\\\",\\\"Year\\\":\\\"2010\\\",\\\"Rated\\\":\\\"PG-13\\\",\\\"Released\\\":\\\"16 Jul 2010\\\",\\\"Runtime\\\":\\\"148 min\\\",\\\"Genre\\\":\\\"Action, Adventure, Sci-Fi\\\",\\\"Director\\\":\\\"Christopher Nolan\\\",\\\"Writer\\\":\\\"Christopher Nolan\\\",\\\"Actors\\\":\\\"Leonardo DiCaprio, Joseph Gordon-Levitt, Elliot Page\\\",\\\"Plot\\\":\\\"Dom Cobb is a skilled thief, the absolute best in the dangerous art of extraction, stealing valuable secrets from deep within the subconscious during the dream state, when the mind is at its most vulnerable. Cobb's rare ability has made him a coveted player in this treacherous new world of corporate espionage, but it has also made him an international fugitive and cost him everything he has ever loved. Now Cobb is being offered a chance at redemption. One last job could give him his life back but only if he can accomplish the impossible, inception. Instead of the perfect heist, Cobb and his team of specialists have to pull off the reverse: their task is not to steal an idea, but to plant one. If they succeed, it could be the perfect crime. But no amount of careful planning or expertise can prepare the team for the dangerous enemy that seems to predict their every move. An enemy that only Cobb could have seen coming.\\\",\\\"Language\\\":\\\"English, Japanese, French\\\",\\\"Country\\\":\\\"United States, United Kingdom\\\",\\\"Awards\\\":\\\"Won 4 Oscars. 159 wins & 220 nominations total\\\",\\\"Poster\\\":\\\"https://m.media-amazon.com/images/M/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_SX300.jpg\\\",\\\"Ratings\\\":[{\\\"Source\\\":\\\"Internet Movie Database\\\",\\\"Value\\\":\\\"8.8/10\\\"},{\\\"Source\\\":\\\"Rotten Tomatoes\\\",\\\"Value\\\":\\\"87%\\\"},{\\\"Source\\\":\\\"Metacritic\\\",\\\"Value\\\":\\\"74/100\\\"}],\\\"Metascore\\\":\\\"74\\\",\\\"imdbRating\\\":\\\"8.8\\\",\\\"imdbVotes\\\":\\\"2,572,598\\\",\\\"imdbID\\\":\\\"tt1375666\\\",\\\"Type\\\":\\\"movie\\\",\\\"DVD\\\":\\\"N/A\\\",\\\"BoxOffice\\\":\\\"$292,587,330\\\",\\\"Production\\\":\\\"N/A\\\",\\\"Website\\\":\\\"N/A\\\",\\\"Response\\\":\\\"True\\\"}Sending to client...Message from server\\n\"}siddheshshirsat@siddheshshirsatsiddheshshirsat@siddheshshirsat-DNB20-series:~/code1/HelloWorldService$ \n";
    }

    private void handleClient(Socket clientSocket) throws IOException {
        PrintWriter clientPrintWriter = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader clientBufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        while(true) {
            String request = clientBufferedReader.readLine();
            if (request.startsWith("get")) {
            	String response = processGetRequest();
                System.out.println("Reached...response" + response);
                clientPrintWriter.println("response " + response);
                clientPrintWriter.flush();
            }

        }
    }

    public void stop() throws IOException {
        serverSocket.close();
        executorService.shutdown();
    }

    public static void main(String[] args) throws IOException {
    	System.out.println("Reached...started...");
        CacheServer server=new CacheServer();
        try {
			server.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
