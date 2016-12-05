package sprites;

public class SpriteAction {
    public int mFirstFrameLeft;
    public int mFirstFrameRight;
    public int mFirstFrameTop;
    public int mFirstFrameBottom;
    public int mSpriteActionFrames;
    public double mFPS;
    public boolean mLoop;

    public SpriteAction(){
        mFirstFrameLeft = 0;
        mFirstFrameRight = 0;
        mFirstFrameTop = 0;
        mFirstFrameBottom = 0;
        mSpriteActionFrames=0;
        mFPS = 0;
        mLoop = false;
    }
    public SpriteAction(int x1, int y1, int x2, int y2, int cols, boolean loop, int fps) {
        mFirstFrameLeft = x1;
        mFirstFrameTop = y1;
        mFirstFrameRight = x2;
        mFirstFrameBottom = y2;
        mSpriteActionFrames = cols;
        mLoop = loop;
        mFPS = 1000.0 / (float)fps;
    }
    
    public int getHeight(){
        return mFirstFrameBottom - mFirstFrameTop+1;
    }
    
    public int getWidth(){
        return mFirstFrameRight - mFirstFrameLeft+1;
    }
}