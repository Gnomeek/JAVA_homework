package chap8_12;

public class test {
    /*
     * ¸²¸Çupdate·½·¨ 
     */
    public void update(Graphics g){
        ImageBuffer = createImage(this.getWidth(), this.getHeight()); 
        GraImage = ImageBuffer.getGraphics();        paint(GraImage); 
        GraImage.dispose();
        g.drawImage(ImageBuffer, 0, 0, this);  
}

}
