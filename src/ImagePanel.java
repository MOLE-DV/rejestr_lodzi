import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {
    private Image bgImage;

    public ImagePanel(){
        try{
            bgImage = new ImageIcon(getClass().getResource("./lodz.jpg")).getImage();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        if(bgImage != null){
            g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
