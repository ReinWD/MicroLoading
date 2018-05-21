import javax.swing.*;
import java.awt.*;

public class LoadPanel extends JPanel {
    Thread updateThread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true){
                LoadPanel.this.repaint();
                drawAngle += speed;
                endAngle +=speed;
                try{
                    Thread.sleep(30);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    });

    private int drawAngle;
    private int endAngle;
    private int startAngle;
    private int speed = 1;

    LoadPanel(){
        this(5,30);
    }

    LoadPanel(int speed, int startAngle){
        drawAngle = 0-startAngle;
        endAngle = startAngle;
        this.setBackground(new Color(0x66ccff));
        this.setVisible(true);
        this.setPreferredSize(new Dimension(200,200));
        updateThread.start();
        this.speed = speed;
        this.startAngle = startAngle;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (g==null)return;
        int height = this.getHeight();
        int width = this.getWidth();

        g.setColor(new Color(0x0));
        if (drawAngle>360){
            drawAngle %= 360;
            endAngle %= 360;
        }

        if (endAngle >startAngle&& endAngle <360-startAngle){
            float percent = ((((float) (endAngle - startAngle)))/(((float) (360 - 2 * startAngle))));
            endAngle +=(3*speed)*Math.sin(percent*Math.PI);
        }
        if (drawAngle>startAngle&&drawAngle<360-startAngle){
            float percent = ((((float) (drawAngle - startAngle)))/(((float) (360 - 2 * startAngle))));
            drawAngle += (3*speed)*Math.sin(percent*Math.PI);
        }
        int start = drawAngle+90;
        int end = endAngle+90;
        int length = end - start;

        g.drawArc((width/2)-25,(height/2)-25,50,50,start, length);
    }
}
