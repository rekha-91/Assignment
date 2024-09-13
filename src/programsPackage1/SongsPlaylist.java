package programsPackage1;

	import java.util.LinkedHashMap;
	import java.util.Map;
	import java.util.LinkedList;


public class SongsPlaylist {
	    private final int capacity;
	    private final Map<String, LinkedHashMap<String, Integer>> userSongMap;

	    // Constructor
	    public SongsPlaylist(int capacity) {
	        this.capacity = capacity;
	        this.userSongMap = new LinkedHashMap<>();
	    }

	    /* Play a song for a user */
	    public void playSong(String user, String song) {
	    	
	        // Retrieve or create user's song list
	        LinkedHashMap<String, Integer> songList = userSongMap.getOrDefault(user, new LinkedHashMap<>());

	        // If song is already in the list, move it to the end (most recently played)
	        if (songList.containsKey(song)) {
	            songList.remove(song);
	        } else if (songList.size() >= capacity) {
	            // Remove the least recently played song (the first entry)
	            String oldestSong = songList.keySet().iterator().next();
	            songList.remove(oldestSong);
	        }

	        // Add the new song as the most recently played
	        songList.put(song, 0); // Value is not used, but required for LinkedHashMap
	        
	        // Update the user song list
	        userSongMap.put(user, songList);
	    }

	    // Get recently played songs for a user
	    public LinkedList<String> getRecentlyPlayed(String user) {
	        LinkedHashMap<String, Integer> songList = userSongMap.get(user);
	        if (songList == null) {
	            return new LinkedList<>();
	        }

	        // Convert LinkedHashMap keys to a LinkedList to maintain order
	        return new LinkedList<>(songList.keySet());
	    }

	    public static void main(String[] args) {
	    	SongsPlaylist playlistStore = new SongsPlaylist(3);

	        playlistStore.playSong("user1", "S1");
	        playlistStore.playSong("user1", "S2");
	        playlistStore.playSong("user1", "S3");
	        System.out.println(playlistStore.getRecentlyPlayed("user1")); // Output: [S1, S2, S3]

	        playlistStore.playSong("user1", "S4");
	        System.out.println(playlistStore.getRecentlyPlayed("user1")); // Output: [S2, S3, S4]

	        playlistStore.playSong("user1", "S2");
	        System.out.println(playlistStore.getRecentlyPlayed("user1")); // Output: [S3, S4, S2]

	        playlistStore.playSong("user1", "S4");
	        System.out.println(playlistStore.getRecentlyPlayed("user1")); // Output: [S4, S2, S1]
	    }
	}



