import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler {

    ArrayList<String> list = new ArrayList<String>();

    public String handleRequest(URI url) {
        if(url.getPath().equals("/add")){
            String[] query = url.getQuery().split("=");
            if(query[0].equals("s")){
                list.add(query[1]);
            }

            return query[1] + " Added!";
        }
        else if(url.getPath().equals("/search")){
            String[] query = url.getQuery().split("=");
            if(query[0].equals("s")){
                String display = "";
                for(int i = 0; i < list.size(); i++){
                    if(list.get(i).contains(query[1])){
                        display += list.get(i) + ", ";
                    }
                }
                return display.substring(0,display.length() - 2);
            }
        }

        else if(url.getPath().equals("/")){
            return "Visit /add or /search";
        }
        else{
            return "404 Not Found!";
        }
        return "";
    }
}

class NumberServer {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
