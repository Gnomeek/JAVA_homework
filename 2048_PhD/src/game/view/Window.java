package game.view;

import javax.swing.*;//引入所用的包
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SuppressWarnings("serial")
public class Window extends JFrame {

    private static int score = 0; //分数
    final Font[] fonts = {new Font("Helvetica Neue", Font.BOLD, 60)//对不同长度的文字进行字号调整
            			, new Font("Helvetica Neue", Font.BOLD, 55)//如PhD使用第一档字号
            			, new Font("Helvetica Neue", Font.BOLD, 50)//而Meet Supervisor使用第五档字号
            			, new Font("Helvetica Neue", Font.BOLD, 28)
                        , new Font("Helvetica Neue", Font.BOLD, 20)
    };

    private GameBoard gameBoard;//游戏主界面
    private JLabel ltitle;//logo
    private JLabel lsctip;//计分板
    private JLabel lscore;//分数
    private JLabel lgatip;//帮助

    public void initView() {   	
        //logo
    	ltitle = new JLabel("PhD", JLabel.CENTER);
        ltitle.setFont(new Font("", Font.BOLD, 100));
        ltitle.setForeground(new Color(0x776e65));
        ltitle.setBounds(0, 0, 240, 120);
        //计分板
        lsctip = new JLabel("Hair Loss", JLabel.CENTER);
        lsctip.setFont(new Font("", Font.BOLD, 32));
        lsctip.setForeground(new Color(0xeee4da));
        lsctip.setOpaque(true);//控件设置为非透明
        lsctip.setBackground(new Color(0xbbada0));
        lsctip.setBounds(480, 10, 200, 50);
        //分数
        lscore = new JLabel("0", JLabel.CENTER);
        lscore.setFont(new Font("Helvetica Neue", Font.BOLD, 44));
        lscore.setForeground(Color.WHITE);
        lscore.setOpaque(true);
        lscore.setBackground(new Color(0xbbada0));
        lscore.setBounds(480, 60, 200, 50);
        //帮助
        lgatip = new JLabel("Use「↑ ↓ ← →」 to control movement ,「Esc」 for restart", JLabel.CENTER);
        lgatip.setFont(new Font("Helvetica Neue", Font.BOLD, 26));
        lgatip.setForeground(new Color(0x776e65));
        lgatip.setBounds(20, 120, 780, 60);
        //游戏主面板
        gameBoard = new GameBoard();
        gameBoard.setPreferredSize(new Dimension(800, 800));
        gameBoard.setBackground(new Color(0xbbada0));
        gameBoard.setBounds(0, 200, 800, 800);
        gameBoard.setFocusable(true);
        //把组件加入窗体
        this.add(ltitle);
        this.add(lsctip);
        this.add(lscore);
        this.add(lgatip);
        this.add(gameBoard);
    }

    class GameBoard extends JPanel implements KeyListener {
        private static final int GAP_CUBE = 32; //方块间隙
        private static final int SIZE_CUBE = 160;//方块大小

        private Cube[][] Cubes = new Cube[4][4];//初始化4*4的方块布局
        
        public GameBoard() {
            initGame();				//绘制界面
            addKeyListener(this);	//添加键盘监听器
        }

        private void initGame() {//初始化界面
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    Cubes[i][j] = new Cube();
                }
            }
            createCube();
            createCube();  //生成两个方块

            isMove = false;//对移动指示器置零
            isOver = false;//对结束指示器置零
        }
        
        private void createCube() {//获取空白方块，并加入列表
            List<Cube> list = getBlankCubes();
            if (!list.isEmpty()) {
                Random random = new Random();
                int index = random.nextInt(list.size());
                Cube Cube = list.get(index);
                //初始化新瓦片的值为2或4，更改该项的值来调试UI
                Cube.value = random.nextInt(10) > 5 ? 4 : 2;//调整生成随机数的值来降低游戏难度，默认生成4和2的几率相同
            }
        }

        private List<Cube> getBlankCubes() {//获取空白方块，加入列表返回
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
        
        public void keyPressed(KeyEvent e) {//监听键盘
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
        
        private void doMove(Cube a, Cube b) {//移动操作
            b.swap(a);
            a.clear();
            isMove = true;
        }

        private void doMerge(Cube a, Cube b) {//合并操作，
            b.value = b.value*2;//等价于b.value = b.value << 1；左移一位
            b.ismerge = true;
            a.clear();
            score += b.value;
            isMove = true;
        }
        
        private void inovkeCreateCube(){//生成新方块
            if(isMove){
                createCube();
                isMove = false;
            }
        }

        private void checkGameOver(boolean moved) {//通过有无空白方块且相邻的两个方块能否合并来判断游戏结束
            lscore.setText(score + "");
            if (!getBlankCubes().isEmpty()) {
                return;//有空白方块
            }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    //判断是否存在可合并的方块
                    if (Cubes[i][j].value == Cubes[i][j + 1].value || Cubes[i][j].value == Cubes[i + 1][j].value) {
                        isOver = false;//即一个方块的相邻方块是否和他相同，相同即不结束
                        return;
                    }
                }
            }
            isOver = true;
        }
        
        //上下左右四个方向的移动操作
        private boolean moveLeft() {
            isMove = false;
            for (int i = 0; i < 4; i++) {
                for (int j = 1; j < 4; j++) {
                    int k = j;
                    //方块不能到达边界，不能空白，相邻方块为不能合成
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
                  //方块不能到达边界，不能空白，相邻方块为不能合成
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
                  //方块不能到达边界，不能空白，相邻方块为不能合成
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
                  //方块不能到达边界，不能空白，相邻方块为不能合成
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
        
        //绘图
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    drawCube(g, i, j);
                }
            }
            if (isOver) {//绘制结束界面
                g.setColor(new Color(255, 255, 255, 180));
                g.fillRect(0, 0, getWidth(), getHeight());//填充矩形
                g.setColor(new Color(0x3d79ca));
                g.setFont(fonts[0]);
                FontMetrics fms = getFontMetrics(fonts[0]);//获取字符串长度
                String value = "Game Over";
                g.drawString(value, (getWidth() - fms.stringWidth(value)) / 2, getHeight() / 2);
                //|blank|string|blank|
                //1     2      3     4 
                //2点坐标为width-string/2
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
                    RenderingHints.VALUE_STROKE_NORMALIZE);			//消除文字的锯齿来支持TrueType字体
            Cube Cube = Cubes[i][j];
            //绘制背景
            g.setColor(Cube.getBackground());
            g.fillRect(GAP_CUBE + (GAP_CUBE + SIZE_CUBE) * j ,
                    GAP_CUBE + (GAP_CUBE + SIZE_CUBE) * i ,
                    SIZE_CUBE , SIZE_CUBE );
            //|gap|size|gap|size|gap|size|gap|size|
            //cube[0][0] (gap,gap,32,32)
            //绘制文字
            g.setColor(Cube.getForeground());
            Font font = Cube.getCubeFont();
            g.setFont(font);
            FontMetrics fms = getFontMetrics(font);
            String value = changeName(Cube.value);
            //注意：横坐标用j计算,纵坐标用i计算
            g.drawString(value, GAP_CUBE + (GAP_CUBE + SIZE_CUBE) * j+ (SIZE_CUBE - fms.stringWidth(value)) / 2
                    , GAP_CUBE + (GAP_CUBE + SIZE_CUBE) * i+ (SIZE_CUBE - fms.getAscent() - fms.getDescent()) / 2 + fms.getAscent());
            //blank
            //---
            //Ascent---   Ascent是b k 等字母的高度,Descent是p g等字母的下半部分的高度
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

