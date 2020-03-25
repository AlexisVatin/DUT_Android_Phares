package iut.phares.dummy;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import iut.phares.MainActivity;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

//    private static final int COUNT = 25;

//    static {
//        // Add some sample items.
//        for (int i = 1; i <= COUNT; i++) {
//            addItem(createDummyItem(i)); mettre dans la boucle de lecture
//        }
//    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.name, item);
    }

//    private static DummyItem createDummyItem(int position) {
//        return new DummyItem(String.valueOf(position), "Item " + position, makeDetails(position));
//    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
//        public final String id;
        public final String name;
//        public final String filename;
        public final String region;
        public final int construction;
//        public final String couleur;
//        public final int hauteur;
//        public final int eclat;
//        public final int periode;
//        public final int portee;
//        public final int automatisation;
//        public final int lat;
//        public final int lon;





        public DummyItem( String name, String region, int construction) {

            this.name = name;
            this.region = region;
            this.construction = construction;
//            this.id = id;

        }

        @Override
        public String toString() {
            return name;
        }
    }

    public static void loadJson() {
        try {
            BufferedReader br = new BufferedReader(new
                    InputStreamReader(MainActivity.getContext().getAssets().open("phares.json")));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            String str = new String(sb.toString());

            JSONObject jObjConnection = new JSONObject(str);
            JSONObject jsonBix = jObjConnection.getJSONObject("phares");
            JSONArray jsonA = jsonBix.getJSONArray("liste");
            for (int i = 0; i < jsonA.length(); i++) {
                JSONObject obj = (JSONObject) jsonA.get(i);
                String nom = obj.getString("name");
                String region = obj.getString("region");
                int construction = obj.getInt("construction");
                addItem(new DummyItem(nom, region, construction));

            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
}
