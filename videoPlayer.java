final Holder<com.codename1.media.Media> mHolder = new Holder<com.codename1.media.Media>();
final Holder<Component> cHolder = new Holder<Component>();
final MyDialog d = new MyDialog(media.mediaName) {
        @Override
        public void dispose() {
                super.dispose();
        }
 
        @Override
        protected void onShow() {
                super.onShow();
                if (mHolder.element != null) mHolder.element.play();
        }
};
final String mediaFile = (String) media.mediaData;
mHolder.element =  MediaManager.createMedia(mediaFile, true);
cHolder.element = mHolder.element.getVideoComponent();
d.addComponent(BorderLayout.CENTER,cHolder.element);                            
d.setCommandButtons( new Command[] {
        new Command("",Main.theme.getImage("media-rewind-icon.png")) {
                public void actionPerformed(ActionEvent evt) {
                        mHolder.element.cleanup();
                        Component oldComp = cHolder.element;
                        try {
                                mHolder.element =  MediaManager.createMedia(mediaFile, true);
                                cHolder.element = mHolder.element.getVideoComponent();
                                d.replace(oldComp, cHolder.element, null);
                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                }
        },
        new Command("",Main.theme.getImage("media-pause-icon.png")) {
                public void actionPerformed(ActionEvent evt) {
                        if (mHolder.element != null) {
                                if (mHolder.element.isPlaying()) mHolder.element.pause();
                        }
                }
        },
        new Command("",Main.theme.getImage("media-play-icon.png")) {
                public void actionPerformed(ActionEvent evt) {
                        if (mHolder.element != null) {
 
                                mHolder.element.play();
                        }
                }
        }
});