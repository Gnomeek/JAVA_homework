package game.view;

import javax.swing.*;//�������õİ�
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SuppressWarnings("serial")
public class Window extends JFrame {

    private static int score = 0; //����
    final Font[] fonts = {new Font("Helvetica Neue", Font.BOLD, 60)//�Բ�ͬ���ȵ����ֽ����ֺŵ���
            			, new Font("Helvetica Neue", Font.BOLD, 55)//��PhDʹ�õ�һ���ֺ�
            			, new Font("Helvetica Neue", Font.BOLD, 50)//��Meet Supervisorʹ�õ��嵵�ֺ�
            			, new Font("Helvetica Neue", Font.BOLD, 28)
                        , new Font("Helvetica Neue", Font.BOLD, 20)
    };

    private GameBoard gameBoard;//��Ϸ������
    private JLabel ltitle;//logo
    private JLabel lsctip;//�Ʒְ�
    private JLabel lscore;//����
    private JLabel lgatip;//����

    public void initView() {   	
        //logo
    	ltitle = new JLabel("PhD", JLabel.CENTER);
        ltitle.setFont(new Font("", Font.BOLD, 100));
        ltitle.setForeground(new Color(0x776e65));
        ltitle.setBounds(0, 0, 240, 120);
        //�Ʒְ�
        lsctip = new JLabel("Hair Loss", JLabel.CENTER);
        lsctip.setFont(new Font("", Font.BOLD, 32));
        lsctip.setForeground(new Color(0xeee4da));
        lsctip.setOpaque(true);//�ؼ�����Ϊ��͸��
        lsctip.setBackground(new Color(0xbbada0));
        lsctip.setBounds(480, 10, 200, 50);
        //����
        lscore = new JLabel("0", JLabel.CENTER);
        lscore.setFont(new Font("Helvetica Neue", Font.BOLD, 44));
        lscore.setForeground(Color.WHITE);
        lscore.setOpaque(true);
        lscore.setBackground(new Color(0xbbada0));
        lscore.setBounds(480, 60, 200, 50);
        //����
        lgatip = new JLabel("Use���� �� �� ���� to control movement ,��Esc�� for restart", JLabel.CENTER);
        lgatip.setFont(new Font("Helvetica Neue", Font.BOLD, 26));
        lgatip.setForeground(new Color(0x776e65));
        lgatip.setBounds(20, 120, 780, 60);
        //��Ϸ�����
        gameBoard = new GameBoard();
        gameBoard.setPreferredSize(new Dimension(800, 800));
        gameBoard.setBackground(new Color(0xbbada0));
        gameBoard.setBounds(0, 200, 800, 800);
        gameBoard.setFocusable(true);
        //��������봰��
        this.add(ltitle);
        this.add(lsctip);
        this.add(lscore);
        this.add(lgatip);
        this.add(gameBoard);
    }

    class GameBoard extends JPanel implements KeyListener {
        private static final int GAP_CUBE = 32; //�����϶
        private static final int SIZE_CUBE = 160;//�����С

        private Cube[][] Cubes = new Cube[4][4];//��ʼ��4*4�ķ��鲼��
        
        public GameBoard() {
            initGame();				//���ƽ���
            addKeyListener(this);	//��Ӽ��̼�����
        }

        private void initGame() {//��ʼ������
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    Cubes[i][j] = new Cube();
                }
            }
            createCube();
            createCube();  //������������

            isMove = false;//���ƶ�ָʾ������
            isOver = false;//�Խ���ָʾ������
        }
        
        private void createCube() {//��ȡ�հ׷��飬�������б�
            List<Cube> list = getBlankCubes();
            if (!list.isEmpty()) {
                Random random = new Random();
                int index = random.nextInt(list.size());
                Cube Cube = list.get(index);
                //��ʼ������Ƭ��ֵΪ2��4�����ĸ����ֵ������UI
                Cube.value = random.nextInt(10) > 5 ? 4 : 2;//���������������ֵ��������Ϸ�Ѷȣ�Ĭ������4��2�ļ�����ͬ
            }
        }

        private List<Cube> getBlankCubes() {//��ȡ�հ׷��飬�����б���
            List<Cube> list = new ArrayList<Cube>();
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (Cubes[i][j].value == 0) {
                        list.add(Cubes[i][j]);
                    }
                }
            }
            return list;
        }
        
        private boolean isOver;
        private boolean isMove;
        
        public void keyPressed(KeyEvent e) {//��������
            boolean moved = false;
            switch (e.getKeyCode()) {
                case KeyEvent.VK_ESCAPE:
                    initGame();
                    break;
                case KeyEvent.VK_LEFT:
                    moved = moveLeft();
                    inovkeCreateCube();
                    checkGameOver(moved);
                    break;
                case KeyEvent.VK_RIGHT:
                    moved = moveRight();
                    inovkeCreateCube();
                    checkGameOver(moved);
                    break;
                case KeyEvent.VK_UP:
                    moved = moveUp();
                    inovkeCreateCube();
                    checkGameOver(moved);
                    break;
                case KeyEvent.VK_DOWN:
                    moved = moveDown();
                    inovkeCreateCube();
                    checkGameOver(moved);
                    break;
            }
            repaint();
        }
        
        private void doMove(Cube a, Cube b) {//�ƶ�����
            b.swap(a);
            a.clear();
            isMove = true;
        }

        private void doMerge(Cube a, Cube b) {//�ϲ�������
            b.value = b.value*2;//�ȼ���b.value = b.value << 1������һλ
            b.ismerge = true;
            a.clear();
            score += b.value;
            isMove = true;
        }
        
        private void inovkeCreateCube(){//�����·���
            if(isMove){
                createCube();
                isMove = false;
            }
        }

        private void checkGameOver(boolean moved) {//ͨ�����޿հ׷��������ڵ����������ܷ�ϲ����ж���Ϸ����
            lscore.setText(score + "");
            if (!getBlankCubes().isEmpty()) {
                return;//�пհ׷���
            }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    //�ж��Ƿ���ڿɺϲ��ķ���
                    if (Cubes[i][j].value == Cubes[i][j + 1].value || Cubes[i][j].value == Cubes[i + 1][j].value) {
                        isOver = false;//��һ����������ڷ����Ƿ������ͬ����ͬ��������
                        return;
                    }
                }
            }
            isOver = true;
        }
        
        //���������ĸ�������ƶ�����
        private boolean moveLeft() {
            isMove = false;
            for (int i = 0; i < 4; i++) {
                for (int j = 1; j < 4; j++) {
                    int k = j;
                    //���鲻�ܵ���߽磬���ܿհף����ڷ���Ϊ���ܺϳ�
                    while (k > 0 && Cubes[i][k].value != 0 && !Cubes[i][k - 1].ismerge) {
                        if (Cubes[i][k - 1].value == 0) {
                            doMove(Cubes[i][k], Cubes[i][k - 1]);
                        } else if (Cubes[i][k - 1].value == Cubes[i][k].value) {
                            doMerge(Cubes[i][k], Cubes[i][k - 1]);
                            break;
                        } else {
                            break;
                        }
                        k--;
                    }
                }
            }
            return isMove;
        }

        private boolean moveRight() {
            isMove = false;
            for (int i = 0; i < 4; i++) {
                for (int j = 2; j > -1; j--) {
                    int k = j;
                  //���鲻�ܵ���߽磬���ܿհף����ڷ���Ϊ���ܺϳ�
                    while (k < 3 && Cubes[i][k].value != 0 && !Cubes[i][k + 1].ismerge) {
                        if (Cubes[i][k + 1].value == 0) {
                            doMove(Cubes[i][k], Cubes[i][k + 1]);
                        } else if (Cubes[i][k + 1].value == Cubes[i][k].value) {
                            doMerge(Cubes[i][k], Cubes[i][k + 1]);
                            break;
                        } else {
                            break;
                        }
                        k++;
                    }
                }
            }
            return isMove;
        }

        private boolean moveUp() {
            isMove = false;
            for (int j = 0; j < 4; j++) {
                for (int i = 1; i < 4; i++) {
                    int k = i;
                  //���鲻�ܵ���߽磬���ܿհף����ڷ���Ϊ���ܺϳ�
                    while (k > 0 && Cubes[k][j].value != 0 && !Cubes[k - 1][j].ismerge) {
                        if (Cubes[k - 1][j].value == 0) {
                            doMove(Cubes[k][j], Cubes[k - 1][j]);
                        } else if (Cubes[k - 1][j].value == Cubes[k][j].value) {
                            doMerge(Cubes[k][j], Cubes[k - 1][j]);
                            break;
                        } else {
                            break;
                        }
                        k--;
                    }
                }
            }
            return isMove;
        }

        private boolean moveDown() {
            isMove = false;
            for (int j = 0; j < 4; j++) {
                for (int i = 2; i > -1; i--) {
                    int k = i;
                  //���鲻�ܵ���߽磬���ܿհף����ڷ���Ϊ���ܺϳ�
                    while (k < 3 && Cubes[k][j].value != 0 && !Cubes[k + 1][j].ismerge) {
                        if (Cubes[k + 1][j].value == 0) {
                            doMove(Cubes[k][j], Cubes[k + 1][j]);
                        } else if (Cubes[k + 1][j].value == Cubes[k][j].value) {
                            doMerge(Cubes[k][j], Cubes[k + 1][j]);
                            break;
                        } else {
                            break;
                        }
                        k++;
                    }
                }
            }
            return isMove;
        }
        
        //��ͼ
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    drawCube(g, i, j);
                }
            }
            if (isOver) {//���ƽ�������
                g.setColor(new Color(255, 255, 255, 180));
                g.fillRect(0, 0, getWidth(), getHeight());//������
                g.setColor(new Color(0x3d79ca));
                g.setFont(fonts[0]);
                FontMetrics fms = getFontMetrics(fonts[0]);//��ȡ�ַ�������
                String value = "Game Over";
                g.drawString(value, (getWidth() - fms.stringWidth(value)) / 2, getHeight() / 2);
                //|blank|string|blank|
                //1     2      3     4 
                //2������Ϊwidth-string/2
            }

        }

        public String changeName(int string){
    		String change = null;
    		switch(string){
    		case 2:change="Coffee";break;
    		case 4:change="Panini";break;
    		case 8:change="Idea";break;
    		case 16:change="Code";break;
    		case 32:change="Deep Learning";break;
    		case 64:change="Meet Supervisor";break;
    		case 128:change="Experiment";break;
    		case 256:change="Paper";break;
    		case 512:change="Conference";break;
    		case 1024:change="Viva";break;
    		case 2048:change="Phd";break;
    		case 4096:change="Die";break;
    		case 0:change="";break;
    		}return change;
    	}
        
        private void drawCube(Graphics graphic, int i, int j) {
            Graphics2D g = (Graphics2D) graphic;
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
                    RenderingHints.VALUE_STROKE_NORMALIZE);			//�������ֵľ����֧��TrueType����
            Cube Cube = Cubes[i][j];
            //���Ʊ���
            g.setColor(Cube.getBackground());
            g.fillRect(GAP_CUBE + (GAP_CUBE + SIZE_CUBE) * j ,
                    GAP_CUBE + (GAP_CUBE + SIZE_CUBE) * i ,
                    SIZE_CUBE , SIZE_CUBE );
            //|gap|size|gap|size|gap|size|gap|size|
            //cube[0][0] (gap,gap,32,32)
            //��������
            g.setColor(Cube.getForeground());
            Font font = Cube.getCubeFont();
            g.setFont(font);
            FontMetrics fms = getFontMetrics(font);
            String value = changeName(Cube.value);
            //ע�⣺��������j����,��������i����
            g.drawString(value, GAP_CUBE + (GAP_CUBE + SIZE_CUBE) * j+ (SIZE_CUBE - fms.stringWidth(value)) / 2
                    , GAP_CUBE + (GAP_CUBE + SIZE_CUBE) * i+ (SIZE_CUBE - fms.getAscent() - fms.getDescent()) / 2 + fms.getAscent());
            //blank
            //---
            //Ascent---   Ascent��b k ����ĸ�ĸ߶�,Descent��p g����ĸ���°벿�ֵĸ߶�
            //Descent
            //---
            //blank
            Cubes[i][j].ismerge = false;
        }

        public void keyTyped(KeyEvent e) {
        
        }

        public void keyReleased(KeyEvent e) {

        }
    }

    class Cube {
        public int value;
        public boolean ismerge;

        public Cube() {
            clear();
        }

        public void clear() {
            value = 0;
            ismerge = false;
        }

        public void swap(Cube Cube) {
            this.value = Cube.value;
            this.ismerge = Cube.ismerge;
        }

        public Color getForeground() {
            switch (value) {
                case 0:
                    return new Color(0xcdc1b4);
                case 2:
                case 4:
                    return new Color(0x776e65);
                default:
                    return new Color(0xf9f6f2);
            }
        }

        public Color getBackground() {
            switch (value) {
                case 0:
                    return new Color(0xcdc1b4);
                case 2:
                    return new Color(0xeee4da);
                case 4:
                    return new Color(0xede0c8);
                case 8:
                    return new Color(0xf2b179);
                case 16:
                    return new Color(0xf59563);
                case 32:
                    return new Color(0xf67c5f);
                case 64:
                    return new Color(0xf65e3b);
                case 128:
                    return new Color(0xedcf72);
                case 256:
                    return new Color(0xedcc61);
                case 512:
                    return new Color(0xedc850);
                case 1024:
                    return new Color(0xedc53f);
                case 2048:
                    return new Color(0xedc22e);
                case 4096:
                    return new Color(0x65da92);
                case 8192:
                    return new Color(0x5abc65);
                case 16384:
                    return new Color(0x248c51);
                default:
                    return new Color(0x248c51);
            }
        }
        
        public Font getCubeFont() {
            int index = 0;
            if(value==2||value==4)
            	index=2;
            else if(value==32||value==64)
            	index=4;
            else if(value==128||value==512)
            	index=3;
            else if(value==8||value==16||value==256||value==1024||value==2048||value==4096)
            	index=1;
            return fonts[index];
        }
    }
    
    public Window() {
        this.setLayout(null);
    }

}

