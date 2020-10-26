package joke;

public class Joke {

   private String id;
   private String content;
   private int upVotes;
   private int downVotes;

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public int getUpVotes() {
        return upVotes;
    }

    public int getDownVotes() {
        return downVotes;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUpVotes(int upVotes) {
        this.upVotes = upVotes;
    }

    public void setDownVotes(int downVotes) {
        this.downVotes = downVotes;
    }

    public Joke(String id, String content, int upVotes, int downVotes) {
        this.id = id;
        this.content = content;
        this.upVotes = upVotes;
        this.downVotes = downVotes;
    }
}
