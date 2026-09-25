public class Song {

    private String songName;
    private String artistName;
    private int lenght;

    public Song(String songName, String artistName, int lenght) {
        this.songName = songName;
        this.artistName = artistName;
        this.lenght = lenght;
    }

    public String getSongName() {
        return songName;
    }
    public void setSongName(String songName) {
        this.songName = songName;
    }
    public String getArtistName() {
        return artistName;
    }
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }
    public int getLenght() {
        return lenght;
    }
    public void setLenght(int lenght) {
        this.lenght = lenght;
    }
}
