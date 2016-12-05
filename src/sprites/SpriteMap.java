package sprites;

import java.util.HashMap;

public class SpriteMap {

    private HashMap<String, SpriteAction> mMap;

    public SpriteMap() {
        mMap = new HashMap<String, SpriteAction>();
    }

    public void addAction(String name, int x1, int y1, int x2, int y2, int cols,
                          boolean loop, int FPS) {
        mMap.put(name, new SpriteAction(x1, y1, x2, y2, cols, loop, FPS));
    }

    public SpriteAction getAction(String key) {
        return mMap.get(key);
    }
}