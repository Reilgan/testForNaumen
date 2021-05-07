import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

class Request {
    private static String hostUrl = "http://localhost:8080";

    public static ArrayList<Note> requestNotes(){
        ArrayList<Note> notes = new ArrayList<>();
        String requestUrl = hostUrl + "/notes";
        try{
            RestTemplate rest = new RestTemplate();
            ResponseEntity<String> response = rest.getForEntity(requestUrl, String.class);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());
            for (int i = 0; i < root.size(); i++){
                JsonNode id = root.path(i).path("id");
                JsonNode h = root.path(i).path("heading");
                JsonNode n = root.path(i).path("note");
                Note note = new Note(h.asText(), n.asText());
                note.setId(Integer.parseInt(id.toString()));
                notes.add(note);
            }
        } catch (HttpClientErrorException | JsonProcessingException e){
//            e.printStackTrace();
        }
        return notes;
    }

    public static Note requestCreateNote(Note note){
        String requestUrl = hostUrl + "/notes";
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<Note> request = new HttpEntity<>(note);
            ResponseEntity<String> response = restTemplate.exchange(requestUrl,
                                                                    HttpMethod.POST,
                                                                    request,
                                                                    String.class);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());
            note.setId(Integer.parseInt(root.path("id").toString()));
            return note;

        } catch (HttpClientErrorException | JsonProcessingException e) {
            e.printStackTrace();
        }
        note.setId(-1);
        return note;
    }

    public static Note requestDeleteNote(Note note){
        String requestUrl = hostUrl + "/notes" + "/" + note.getId();
        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.delete(requestUrl);
        }catch (HttpClientErrorException e){
            e.printStackTrace();
        }
        return note;
    }

    public static void main(String[] args) {
        System.out.println(Request.requestNotes());
    }
}
