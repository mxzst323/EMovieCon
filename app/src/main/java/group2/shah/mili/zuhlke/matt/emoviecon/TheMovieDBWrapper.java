package group2.shah.mili.zuhlke.matt.emoviecon;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TheMovieDBWrapper {

    private static List<EMovieCon> nowPlayingMoviesList = new ArrayList<>();
    private static List<EMovieCon> upcomingMoviesList = new ArrayList<>();

    private static final String apiKey = "7dbb01c9bbcc61c91e0aeb60fae81f59"; //Unsecure
    private static final String nowPlayingURL = "http://api.themoviedb.org/3/movie/now_playing?api_key=" + apiKey + "&language=en-US&page=";
    private static final String upcomingURL = "http://api.themoviedb.org/3/movie/upcoming?api_key=" + apiKey + "&language=en-US&page=";

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    private static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new URL(url).openStream(), StandardCharsets.UTF_8))) {
            String jsonText;
            jsonText = readAll(br);
            return new JSONObject(jsonText);
        }
    }

    public static ArrayList<Bitmap> getBackdrops(int id) {
        final String imageCall = "https://api.themoviedb.org/3/movie/" + id + "/images?api_key=" + apiKey + "&include_image_language=en";
        final ArrayList<Bitmap> imgs = new ArrayList<>();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject json = readJsonFromUrl(imageCall);
                    Iterator<String> keys = json.keys();
                    while (keys.hasNext()) {
                        String key = keys.next();
                        if (key.equals("backdrops")) {
                            JSONArray jsonVideos = json.getJSONArray(key);
                            // Only get first object because this is defaulted on the site
                            if (0 < jsonVideos.length()) {
                                for (int i = 0; i <= jsonVideos.length(); i++) {
                                    String fp = "https://image.tmdb.org/t/p/original/" + jsonVideos.getJSONObject(i).getString("file_path");
                                    imgs.add(getMoviePostBitMap(new URL(fp)));
                                }
                            }
                        }
                    }
                } catch (IOException | JSONException ex) {
                    Log.e("EMovieCon", "There was a problem parsing the videos(s): " + ex.getMessage());
                }
            }
        });
        try {
            t.start();
            t.join(); //Wait for the query to finish
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
        if (0 < imgs.size())
            return imgs;
        else
            return null;
    }

    public static Bitmap getMoviePostBitMap(final URL photoURL) {
        if (photoURL != null) {
            final ArrayList<Bitmap> pics = new ArrayList<>();
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        pics.add(BitmapFactory.decodeStream(photoURL.openConnection().getInputStream()));
                    } catch (IOException ex) {
                        Log.e("EMovieCon", "exception:" + ex.getMessage());
                    }
                }
            });
            try {
                t.start();
                t.join(); //Wait for the query to finish
            } catch (InterruptedException e) {
                e.printStackTrace();
                return null;
            }
            return pics.get(0);
        } else {
            return null;
        }
    }

    public static List<EMovieCon> getNowPlayingMovies() {
        return nowPlayingMoviesList;
    }

    public static List<EMovieCon> getUpcomingMovies() {
        return upcomingMoviesList;
    }

    // Getting youtube vids separately to limit down on requests since not every movie will be clicked on for details
    public static ArrayList<ArrayList<String>> getYouTubeLinks(int id) {
        final String videoCall = "https://api.themoviedb.org/3/movie/" + id + "/videos?api_key=" + apiKey + "&language=en-US";
        final ArrayList<ArrayList<String>> vids = new ArrayList<>();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject json = readJsonFromUrl(videoCall);
                    Iterator<String> keys = json.keys();
                    while (keys.hasNext()) {
                        String key = keys.next();
                        if (key.equals("results")) {
                            JSONArray jsonVideos = json.getJSONArray(key);
                            for (int i = 0; i < jsonVideos.length(); i++) {
                                if (jsonVideos.getJSONObject(i).getString("site").equals("YouTube")) {
                                    String id = jsonVideos.getJSONObject(i).getString("id");
                                    String name = jsonVideos.getJSONObject(i).getString("name");
                                    String keyStr = "https://www.youtube.com/watch?v=" + jsonVideos.getJSONObject(i).getString("key");
                                    ArrayList<String> nameURLList = new ArrayList<>(2);
                                    nameURLList.add(name);
                                    nameURLList.add(keyStr);
                                    vids.add(nameURLList);
                                    Log.i("EMovieCon", "*** Got video:" + id);
                                }
                            }
                        }
                    }
                } catch (IOException | JSONException ex) {
                    Log.e("EMovieCon", "There was a problem parsing the videos(s): " + ex.getMessage());
                }
            }
        });
        try {
            t.start();
            t.join(); //Wait for the query to finish
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
        return vids;
    }

    private static List<EMovieCon> grabMovies(final String url, final int maxPage) {
        final List<List<EMovieCon>> movieList = new ArrayList<>();
        Thread t = new Thread(new Runnable() {
            List<EMovieCon> tmpMovies = new ArrayList<>();

            @Override
            public void run() {
                try {
                    int realPages;
                    JSONObject json = readJsonFromUrl(url + "1");
                    if (maxPage == 11) //This means maximum was selected
                        realPages = json.getInt("total_pages");
                    else
                        realPages = maxPage;
                    Iterator<String> keys = json.keys();
                    for (int f = 2; f <= realPages; f++) { //Starts on page 1 so it should query page 2
                        while (keys.hasNext()) {
                            String key = keys.next();
                            if (key.equals("results")) {
                                JSONArray jsonMovies = json.getJSONArray(key);
                                for (int i = 0; i < jsonMovies.length(); i++) {
                                    int id = jsonMovies.getJSONObject(i).getInt("id");
                                    String title = jsonMovies.getJSONObject(i).getString("title");
                                    String descr = jsonMovies.getJSONObject(i).getString("overview");
                                    String poster = jsonMovies.getJSONObject(i).getString("poster_path");
                                    String datestr = jsonMovies.getJSONObject(i).getString("release_date");
                                    long popularity = jsonMovies.getJSONObject(i).getLong("popularity");
                                    Double voteAvg = jsonMovies.getJSONObject(i).getDouble("vote_average");
                                    tmpMovies.add(new EMovieCon(id, title, descr, new URL("http://image.tmdb.org/t/p/w500" + poster), voteAvg, popularity, datestr));
                                    Log.i("EMovieCon", "*** Got Movie:" + id);
                                }
                            }
                        }
                        json = readJsonFromUrl(url + f);
                        keys = json.keys();
                    }
                } catch (IOException | JSONException ex) {
                    Log.e("EMovieCon", "There was a problem parsing the movie(s): " + ex.getMessage());
                }
                movieList.add(tmpMovies);
            }
        });
        try {
            t.start();
            t.join(); //Wait for the query to finish
            return movieList.get(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void updateLists(Context appContext) {
        final SharedPreferences sharedPref = appContext.getSharedPreferences("EMovieConPrefs", 0);
        int maxPage = sharedPref.getInt("saved_max_pages_key", 3);
        setNowPlayingMovies(maxPage);
        setUpcomingMovies(maxPage);
    }

    private static void setNowPlayingMovies(int maxPage) {
        nowPlayingMoviesList = grabMovies(nowPlayingURL, maxPage);
    }

    private static void setUpcomingMovies(int maxPage) {
        upcomingMoviesList = grabMovies(upcomingURL, maxPage);
    }
}
