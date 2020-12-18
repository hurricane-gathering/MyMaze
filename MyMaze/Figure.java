package MyMaze;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
 * 本类为迷宫游戏提供图形化界面
 */
public class Figure {
    // 定义路径
    Path path;
    // 定义当前迷宫周围状态
    Place[] maze = null;
    // 用按钮实现迷宫格
    JButton[] button = null;
    boolean[] isPath = null;

    // A* 寻路界面
    class FindMaze extends JFrame implements ActionListener {
        public FindMaze() {
            super("A* Maze");
            // 界面大小
            this.setSize(500, 500);
            // 返回默认工具箱
            Toolkit kit = Toolkit.getDefaultToolkit();
            // 获取屏幕尺寸
            Dimension screenSize = kit.getScreenSize();
            // 获取屏幕宽度
            int screenWidth = screenSize.width;
            // 获取屏幕高度
            int screenHeight = screenSize.height;
            // 获取界面窗口宽度
            int windowWidth = this.getWidth();
            // 获取界面窗口高度
            int windowHeight = this.getHeight();
            // 界面居中
            this.setLocation((screenWidth - windowWidth) / 2, (screenHeight - windowHeight) / 2);
            // 四行一列布局
            this.setLayout(new GridLayout(path.getSize(), path.getSize()));
            maze = path.getMaze();

            button = new JButton[maze.length];
            for (int i = 1; i < maze.length; ++i) {
                // 当前格子是路则设置活动指令为 1，背景颜色为绿色
                if (maze[i].getWall() == 0) {
                    button[i] = new JButton();
                    button[i].setActionCommand("1");
                    button[i].setBackground(Color.GREEN);
                }
                // 当前格子为墙则设置活动指令为 0，标记为灰色
                if (maze[i].getWall() == 1) {
                    button[i] = new JButton();
                    button[i].setActionCommand("0");
                    button[i].setBackground(Color.LIGHT_GRAY);
                }
            }
            for (int i = 1; i < button.length; i++) {
                button[i].addActionListener(this);
                add(button[i]);
            }
            button[path.getSize() * path.getSize()].setActionCommand("退出");
            button[path.getSize() * path.getSize()].setText("退出");
            button[path.getSize() * path.getSize()].setFont(new Font("宋体", 0, 7));
            addWindowListener(new closeWin());
            this.setVisible(true);
        }

        private void start() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("退出")) {
                dispose();
                Figure figure = new Figure();
                figure.init();
            }
        }
    }

    // 启动游戏界面
    class MazeGameFigure extends JFrame implements ActionListener {
        public MazeGameFigure() {
            super("迷宫游戏");
        }

        public void init() {
            // 界面大小
            this.setSize(500, 500);
            // 返回默认工具箱
            Toolkit kit = Toolkit.getDefaultToolkit();
            // 获取屏幕尺寸
            Dimension screenSize = kit.getScreenSize();
            // 获取屏幕宽度
            int screenWidth = screenSize.width;
            // 获取屏幕高度
            int screenHeight = screenSize.height;
            // 获取界面窗口宽度
            int windowWidth = this.getWidth();
            // 获取界面窗口高度
            int windowHeight = this.getHeight();
            // 界面居中
            this.setLocation((screenWidth - windowWidth) / 2, (screenHeight - windowHeight) / 2);
            // 四行一列布局
            this.setLayout(new GridLayout(5, 1));
            JLabel welcom = new JLabel("欢迎进入迷宫游戏!");
            welcom.setBackground(Color.CYAN);
            welcom.setFont(new Font("宋体", 1, 25));
            JButton find = new JButton("A* 寻路");
            find.setFont(new Font("宋体", 1, 25));
            JButton start = new JButton("开始游戏");
            start.setFont(new Font("宋体", 1, 25));
            JButton set = new JButton("游戏设置");
            set.setFont(new Font("宋体", 1, 25));
            JButton end = new JButton("退出游戏");
            end.setFont(new Font("宋体", 1, 25));
            find.setBackground(Color.PINK);
            start.setBackground(Color.PINK);
            set.setBackground(Color.PINK);
            end.setBackground(Color.PINK);
            add(welcom);
            add(find);
            add(start);
            add(set);
            add(end);
            find.addActionListener(this);
            start.addActionListener(this);
            set.addActionListener(this);
            end.addActionListener(this);
            addWindowListener(new closeWin());
            this.setVisible(true);
        }

        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("A* 寻路")) {
                dispose();
                new FindMaze().start();
            }
            // 点击开始游戏生成迷宫
            if (e.getActionCommand().equals("开始游戏")) {
                MazeFigure mazeFigure = new MazeFigure();
                mazeFigure.init();
                dispose();
            }
            // 点击游戏设置进入设置模式
            if (e.getActionCommand().equals("游戏设置")) {
                MazeSetFigure mazeSetFigure = new MazeSetFigure();
                mazeSetFigure.init();
                dispose();
            }
            if (e.getActionCommand().equals("退出游戏")) {
                dispose();
            }
        }
    }

    // 开始游戏界面
    class MazeFigure extends JFrame implements ActionListener {
        public MazeFigure() {
            super("Maze");
        }

        public void init() {
            this.setSize(500, 500);
            this.setBackground(Color.BLACK);
            Toolkit kit = Toolkit.getDefaultToolkit();
            Dimension screenSize = kit.getScreenSize();
            int screenWidth = screenSize.width;
            int screenHeight = screenSize.height;
            int windowWidth = this.getWidth();
            int windowHeight = this.getHeight();
            this.setLocation((screenWidth - windowWidth) / 2, (screenHeight - windowHeight) / 2);
            // 获取迷宫尺寸设计按钮布局
            this.setLayout(new GridLayout(path.getSize(), path.getSize()));
            maze = path.getMaze();
            int entrance = path.getEntrance();
            int exit = path.getExit();
            button = new JButton[maze.length];
            for (int i = 1; i < maze.length; i++) {
                // 当前格子是路则设置活动指令为 1，背景颜色为绿色
                if (maze[i].getWall() == 0) {
                    button[i] = new JButton();
                    button[i].setActionCommand("1");
                    button[i].setBackground(Color.GREEN);
                }
                // 当前格子为墙则设置活动指令为 0，标记为灰色
                if (maze[i].getWall() == 1) {
                    button[i] = new JButton();
                    button[i].setActionCommand("0");
                    button[i].setBackground(Color.LIGHT_GRAY);
                }
            }
            button[entrance].setText("入口");
            button[entrance].setFont(new Font("宋体", 1, 7));
            button[exit].setText("出口");
            button[exit].setFont(new Font("宋体", 1, 7));
            // 为每个按钮添加监听器
            for (int i = 1; i < button.length; i++) {
                button[i].addActionListener(this);
                add(button[i]);
            }
            addWindowListener(new closeWin());
            this.setVisible(true);
        }

        // 判断是否完成通路
        private boolean isComplete() {
            isPath = path.getPath();
            for (int i = 1; i < isPath.length; i++) {
                if (isPath[i] && button[i].getBackground() != Color.YELLOW) {
                    return false;
                }
            }
            return true;
        }

        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            if (button.getActionCommand().equals("1")) {
                if (button.getBackground() == Color.GREEN) {
                    button.setBackground(Color.YELLOW);
                } else if (button.getBackground() == Color.YELLOW) {
                    button.setBackground(Color.GREEN);
                }
            }
            if (isComplete()) {
                CongratulationFigure congratulationFigure = new CongratulationFigure();
                congratulationFigure.init();
                this.dispose();
            }
        }
    }

    // 迷宫设置界面
    class MazeSetFigure extends Frame implements ActionListener, TextListener {
        String newSize, newEntrance, newExit;
        JTextField setMaze, setEntrance, setExit;
        int size, entrance, exit;

        public MazeSetFigure() {
            super("迷宫设置");
        }

        public void init() {
            this.setSize(500, 400);
            this.setBackground(Color.WHITE);
            Toolkit kit = Toolkit.getDefaultToolkit();
            Dimension screenSize = kit.getScreenSize();
            int screenWidth = screenSize.width;
            int screenHeight = screenSize.height;
            int windowWidth = this.getWidth();
            int windowHeight = this.getHeight();
            this.setLocation((screenWidth - windowWidth) / 2, (screenHeight - windowHeight) / 2);
            GridLayout layout = new GridLayout(5, 2);
            this.setLayout(layout);
            JLabel size = new JLabel("迷宫规模");
            size.setFont(new Font("宋体", 1, 20));
            JLabel entrance = new JLabel("迷宫入口");
            entrance.setFont(new Font("宋体", 1, 20));
            JLabel exit = new JLabel("迷宫出口");
            exit.setFont(new Font("宋体", 1, 20));
            JButton menu = new JButton("返回菜单");
            menu.setFont(new Font("宋体", 1, 20));
            JButton set = new JButton("设置完成");
            set.setFont(new Font("宋体", 1, 20));
            setMaze = new JTextField("10");
            setEntrance = new JTextField("左上角");
            setEntrance.setFont(new Font("宋体", 1, 18));
            setExit = new JTextField("右下角");
            setExit.setFont(new Font("宋体", 1, 18));
            JLabel tip = new JLabel("tips:出入口只能设置为四个角");
            JLabel tips = new JLabel("即左上角，右上角，左下角，右下角");
            add(tip);
            add(tips);
            add(size);
            add(setMaze);
            add(entrance);
            add(setEntrance);
            add(exit);
            add(setExit);
            add(menu);
            add(set);
            menu.addActionListener(this);
            set.addActionListener(this);
            setMaze.addActionListener(this);
            setEntrance.addActionListener(this);
            setExit.addActionListener(this);
            addWindowListener(new closeWin());
            this.setVisible(true);
        }

        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("返回菜单")) {
                dispose();
                Figure figure = new Figure();
                figure.init();
            }
            if (e.getActionCommand().equals("设置完成")) {
                boolean isSizeReasonable = true;
                boolean isEntranceReasonable = true;
                boolean isExitReasonable = true;
                newSize = setMaze.getText();
                newEntrance = setEntrance.getText();
                newExit = setExit.getText();
                try {
                    size = Integer.parseInt(newSize);
                } catch (Exception ex) {
                    isSizeReasonable = false;
                }
                if (isSizeReasonable == true) {
                    if (newEntrance.equals("左上角")) {
                        entrance = 1;
                    } else if (newEntrance.equals("右上角")) {
                        entrance = size;
                    } else if (newEntrance.equals("左下角")) {
                        entrance = size * (size - 1) + 1;
                    } else if (newEntrance.equals("右下角")) {
                        entrance = size * size;
                    } else {
                        isEntranceReasonable = false;
                    }

                    if (newExit.equals("左上角")) {
                        exit = 1;
                    } else if (newExit.equals("右上角")) {
                        exit = size;
                    } else if (newExit.equals("左下角")) {
                        exit = size * (size - 1) + 1;
                    } else if (newExit.equals("右下角")) {
                        exit = size * size;
                    } else {
                        isExitReasonable = false;
                    }

                    if (isEntranceReasonable == true && isExitReasonable == true) {
                        if (entrance == exit) {
                            isEntranceReasonable = false;
                            isExitReasonable = false;
                        }
                    }
                }
                if (isSizeReasonable == true && isEntranceReasonable == true && isExitReasonable == true) {
                    dispose();
                    Figure figure = new Figure(size, entrance, exit);
                    figure.init();
                } else {
                    SetErrorFigure setErrorFigure = new SetErrorFigure();
                    setErrorFigure.init();
                    dispose();
                }
            }
        }

        public void textValueChanged(TextEvent e) {

        }
    }

    // 通过迷宫游戏界面
    class CongratulationFigure extends Frame implements ActionListener {
        public CongratulationFigure() {
            super("恭喜");
        }

        public void init() {
            this.setSize(220, 200);
            this.setBackground(Color.WHITE);
            Toolkit kit = Toolkit.getDefaultToolkit();
            Dimension screenSize = kit.getScreenSize();
            int screenWidth = screenSize.width;
            int screenHeight = screenSize.height;
            int windowWidth = this.getWidth();
            int windowHeight = this.getHeight();
            this.setLocation((screenWidth - windowWidth) / 2, (screenHeight - windowHeight) / 2);
            this.setLayout(new GridLayout(2, 1));
            JLabel text = new JLabel("恭喜您成功走出迷宫!");
            JButton button = new JButton("确认");
            button.setBackground(Color.WHITE);
            add(text);
            add(button);
            button.addActionListener(this);
            addWindowListener(new closeWin());
            this.setVisible(true);
        }

        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("确认")) {
                dispose();
                Figure figure = new Figure();
                figure.init();
            }
        }
    }

    // 游戏设置数据错误界面
    class SetErrorFigure extends Frame implements ActionListener {
        public SetErrorFigure() {
            super("错误");
        }

        public void init() {
            this.setSize(230, 100);
            this.setBackground(Color.WHITE);
            Toolkit kit = Toolkit.getDefaultToolkit();
            Dimension screenSize = kit.getScreenSize();
            int screenWidth = screenSize.width;
            int screenHeight = screenSize.height;
            int windowWidth = this.getWidth();
            int windowHeight = this.getHeight();
            this.setLocation((screenWidth - windowWidth) / 2, (screenHeight - windowHeight) / 2);
            this.setLayout(new GridLayout(2, 1));
            JLabel text = new JLabel("您输入的数据不合理,设置失败!");
            JButton button = new JButton("确认");
            button.setBackground(Color.WHITE);
            add(text);
            add(button);
            button.addActionListener(this);
            addWindowListener(new closeWin());
            this.setVisible(true);
        }

        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("确认")) {
                dispose();
                Figure figure = new Figure();
                figure.init();
            }
        }
    }

    class closeWin extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            Window w = e.getWindow();
            w.dispose();
        }
    }

    public Figure() {
        path = new Path();
    }

    public Figure(int size, int entrance, int exit) {
        path = new Path(size, entrance, exit);
    }

    public void init() {
        MazeGameFigure mazeGameFigure = new MazeGameFigure();
        mazeGameFigure.init();
    }

}

