package sapphire.seemetrain;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.test.InstrumentationTestCase;
import android.view.View;
import android.widget.RelativeLayout;

public class PlaylistInstrumentationTest extends InstrumentationTestCase {

    private static final String KEY_SP_PACKAGE = "PrivateStorageUtilsTest";

    protected void setUp() throws Exception {
        super.setUp();

        // Clear everything in the SharedPreferences
        SharedPreferences sharedPreferences = getInstrumentation().getTargetContext()
                .getSharedPreferences(KEY_SP_PACKAGE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

    protected void tearDown() throws Exception {
        // Clear everything in the SharedPreferences
        SharedPreferences sharedPreferences = getInstrumentation().getTargetContext().
                getSharedPreferences(KEY_SP_PACKAGE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }


    public void testWriteToPreferencesThreeCount() throws Exception {
        SharedPreferences sharedPref = getInstrumentation().getTargetContext().
                getSharedPreferences(KEY_SP_PACKAGE, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();

        Uri uri = Uri.parse("file:///tmp/sit.mp4");
        Uri uri2 = Uri.parse("file:///tmp/lay.mp4");
        Uri uri3 = Uri.parse("file:///tmp/speak.mp4");
        VideoPath video = new VideoPath("Sit", uri);
        VideoPath video2 = new VideoPath("Lay", uri2);
        VideoPath video3 = new VideoPath("Speak", uri3);
        Playlist playlist = new Playlist("Test", video);
        playlist.addVideo(video2);
        playlist.addVideo(video3);
        RelativeLayout relativeLayout = new RelativeLayout(getInstrumentation().getContext());
        playlist.writeToPreferences(3, relativeLayout, editor, getInstrumentation().getContext());
        assertEquals(sharedPref.getInt("video1count", 1), 0);
        assertEquals(sharedPref.getInt("video2count", 1), 0);
        assertEquals(sharedPref.getInt("video3count", 1), 0);
    }

    public void testWriteToPreferencesThreePath() throws Exception {
        SharedPreferences sharedPref = getInstrumentation().getTargetContext().
                getSharedPreferences(KEY_SP_PACKAGE, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();

        Uri uri = Uri.parse("file:///tmp/sit.mp4");
        Uri uri2 = Uri.parse("file:///tmp/lay.mp4");
        Uri uri3 = Uri.parse("file:///tmp/speak.mp4");
        VideoPath video = new VideoPath("Sit", uri);
        VideoPath video2 = new VideoPath("Lay", uri2);
        VideoPath video3 = new VideoPath("Speak", uri3);
        Playlist playlist = new Playlist("Test", video);
        playlist.addVideo(video2);
        playlist.addVideo(video3);
        RelativeLayout relativeLayout = new RelativeLayout(getInstrumentation().getContext());
        playlist.writeToPreferences(3, relativeLayout, editor, getInstrumentation().getContext());
        assertEquals(sharedPref.getString("video1path", ""), "file:///tmp/sit.mp4");
        assertEquals(sharedPref.getString("video2path", ""), "file:///tmp/lay.mp4");
        assertEquals(sharedPref.getString("video3path", ""), "file:///tmp/speak.mp4");
    }

    public void testWriteToPreferencesOneCount() throws Exception {
        SharedPreferences sharedPref = getInstrumentation().getTargetContext().
                getSharedPreferences(KEY_SP_PACKAGE, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();

        Uri uri = Uri.parse("file:///tmp/sit.mp4");
        VideoPath video = new VideoPath("Sit", uri);
        Playlist playlist = new Playlist("Test", video);
        RelativeLayout relativeLayout = new RelativeLayout(getInstrumentation().getContext());
        playlist.writeToPreferences(3, relativeLayout, editor, getInstrumentation().getContext());
        assertEquals(sharedPref.getInt("video1count", 1), 0);
    }

    public void testWriteToPreferencesOnePath() throws Exception {
        SharedPreferences sharedPref = getInstrumentation().getTargetContext().
                getSharedPreferences(KEY_SP_PACKAGE, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();

        Uri uri = Uri.parse("file:///tmp/sit.mp4");
        VideoPath video = new VideoPath("Sit", uri);
        Playlist playlist = new Playlist("Test", video);
        RelativeLayout relativeLayout = new RelativeLayout(getInstrumentation().getContext());
        playlist.writeToPreferences(3, relativeLayout, editor, getInstrumentation().getContext());
        assertEquals(sharedPref.getString("video1path", ""), "file:///tmp/sit.mp4");
    }

}
