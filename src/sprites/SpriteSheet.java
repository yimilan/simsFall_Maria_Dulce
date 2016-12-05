package sprites;

import java.awt.Graphics;
import java.awt.Image;

public class SpriteSheet {
            private Image mBitmap;
            private Rect mDestination = new Rect();
            private Rect mSRectangle = new Rect();

            public int mSpriteHeight;
            public int mSpriteWidth;
            private int mSheetFrames;
            private int mCurrentFrame = 0;

            private double mFrameTimer = 0;

            private SpriteAction mActiveAction;
            private SpriteMap mMap;

            public boolean mActionComplete;
            private String defaultAction;

    public String getDefaultAction() {
        return defaultAction;
    }

            public SpriteSheet(SpriteMap map, String defaultAction, Image sheet) throws MissingSpriteActionException {
                mMap = map;
                this.defaultAction = defaultAction;
                mActiveAction = mMap.getAction(defaultAction);
                if(mActiveAction==null)
                    throw new MissingSpriteActionException(defaultAction);
                mSheetFrames = mActiveAction.mSpriteActionFrames;
                mSpriteHeight = mActiveAction.getHeight();
                mSpriteWidth = mActiveAction.getWidth();
                mBitmap = sheet;

                //Iniciar el sprite
                updateSourceRectangle();
            }

            public void setAction(String name) throws MissingSpriteActionException{
                setAction(name, true);
            }
            
            public void setAction(String name, boolean reset) throws MissingSpriteActionException {
                defaultAction = name;
                mActiveAction = mMap.getAction(name);
                if(mActiveAction==null)
                    throw new MissingSpriteActionException(name);
                mSheetFrames = mActiveAction.mSpriteActionFrames;
                mSpriteHeight = mActiveAction.getHeight();
                mSpriteWidth = mActiveAction.getWidth();
                
                if(reset)
                    resetAction();
                mActionComplete = false;

                //Init sprite
                updateSourceRectangle();
            }

            public void resetAction() {
                mCurrentFrame = 0;
                mFrameTimer = 0;
                
                updateSourceRectangle();
            }
            
            private void updateSourceRectangle(){
                mSRectangle.left = mActiveAction.mFirstFrameLeft + mCurrentFrame * mSpriteWidth;
                mSRectangle.right = mSRectangle.left + mSpriteWidth;
                mSRectangle.top = mActiveAction.mFirstFrameTop;
                mSRectangle.bottom = mSRectangle.top + mSpriteHeight;
            }

            public void update(double timePassed) {
                if(mActionComplete)
                    return;
                
                mFrameTimer += timePassed;
                if (mFrameTimer >= mActiveAction.mFPS) {
                    mFrameTimer -= mActiveAction.mFPS;
                    mCurrentFrame++;
                    if (mCurrentFrame == mSheetFrames) {
                        if(mActiveAction.mLoop)
                            mCurrentFrame = 0;
                        else {
                            mActionComplete = true;
                            mCurrentFrame--;
                        }
                    }
                        
                    updateSourceRectangle();
                }
            }

            public boolean isComplete() {
                return mActionComplete;
            }

            public void drawCurrentFrame(Graphics g, int x, int y) {
                mDestination.left = x;
                mDestination.top = y;
                mDestination.right = mDestination.left + mSpriteWidth;
                mDestination.bottom = mDestination.top + mSpriteHeight;
                g.drawImage(mBitmap, mDestination.left, mDestination.top, mDestination.right, mDestination.bottom, 
                                     mSRectangle.left, mSRectangle.top, mSRectangle.right, mSRectangle.bottom, null);
            }
        }