# SeeMeTrain

This Android application helps users train their dogs while they are away from home. It does this by recording videos, saving them to the device and playing them back during alarms set by the user. Recording is done with the user's default camera application. Videos are saved to the `DCIM/Camera` location with a filename of "SMT" followed by a timestamp.

The user can define 2 parameters for video playback. 

The first is the `Schedule`. As part of this, the user defines 1: the interval between each video played and 2: the *end* time. If the user defines an interval of 30 minutes with an end time of 8:45, then the app will play a video once every half hour from the time they begin the app until 8:45. The second parameter, a `Playlist`, are a queue of video paths that are played through sequentially. After a `Playlist` is run through, the app will go back to the first video and start over from there.

# Alarms

The automatic playback of videos is handled using alarms, specifically the Android `AlarmManager` class. The app takes the user defined end time and interval and sends it as a `PendingIntent` within a `Broadcast` to the Android system. When the alarm is scheduled to go off, Android will send a `Broadcast` back to the app with information about what to do. The receiver we created, `AlarmVideoReceiver` handles this incoming message. Because the alarm must turn on the phone display if it is off, `AlarmVideoReceiver` extends `WakefulBroadcastReceiver`. By doing this, our receiver is allowed to start a *wakeful* service, which has permission to turn the device display on. This service, `AlarmVideoService`, runs in the background and determines the next video to play. After retreiving the video path, it starts an activity to play the designated video.

Another alternate implementation that we used is to instead have our `AlarmVideoReceiver` start the activity directly. However, this implementation caused file read permission issues. that we could not solve.

# SharedPreferences

For the purposes of this application, all persistent data is saved using Android's `SharedPreferences`. If you are unfamiliar with this method of storing data, it is essentially a database that maps primitive values to a String key. Data can be retreived by calling the appropriate `.get("key")` method on the SharedPreferences object. 

Everything in SeeMeTrain is saved to a single file, `"MyPrefs"`. The key naming convention that we followed is as follows:
1. Every video is saved as 3 pieces of data: a path (Uri location), name (user defined command name) and playcount (number of times the video has been played in the current schedule setup)
2. Keys are named in the format: "video#path" where # is the order is was put in the `Playlist` and "path" is either the "path", "name" or "count"
3. The current video # is saved in "currentVid"

This data is used to populate the history screen. Clearing the history will clear all schedule, playlist and playcount information. It will not clear any account or password information.
