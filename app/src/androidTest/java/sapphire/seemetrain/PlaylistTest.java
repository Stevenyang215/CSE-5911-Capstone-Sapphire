package sapphire.seemetrain;

import android.net.Uri;

import junit.framework.TestCase;

/**
 * File Created by Joseph
 */
public class PlaylistTest extends TestCase {

    public void testGetName() throws Exception {
        Uri uri = Uri.parse("file:///tmp/sit.mp4");
        VideoPath video = new VideoPath("Sit", uri);
        Playlist playlist = new Playlist("Test", video);
        assertEquals("Test", playlist.getName());
    }

    public void testGetNextVideo() throws Exception {
        Uri uri = Uri.parse("file:///tmp/sit.mp4");
        VideoPath video = new VideoPath("Sit", uri);
        Playlist playlist = new Playlist("Test", video);
        assertEquals(video, playlist.getNextVideo());
        assertEquals(video, playlist.getNextVideo());
    }

    public void testAddVideo() throws Exception {

        Uri uri = Uri.parse("file:///tmp/sit.mp4");
        Uri uri2 = Uri.parse("file:///tmp/lay.mp4");
        Uri uri3 = Uri.parse("file:///tmp/speak.mp4");
        VideoPath video = new VideoPath("Sit", uri);
        VideoPath video2 = new VideoPath("Lay", uri2);
        VideoPath video3 = new VideoPath("Speak", uri3);
        Playlist playlist = new Playlist("Test", video);
        assertEquals(video, playlist.getNextVideo());
        playlist.addVideo(video2);
        playlist.addVideo(video3);
        assertEquals(video, playlist.getNextVideo());
        assertEquals(video2, playlist.getNextVideo());
        assertEquals(video3, playlist.getNextVideo());
    }
}