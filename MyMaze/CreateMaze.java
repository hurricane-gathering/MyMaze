package MyMaze;

/**
 * 创建迷宫及提供迷宫的一些参数
 */
public class CreateMaze {
    // 定义迷宫规模
    private int size;
    // 定义迷宫的入口和出口
    private int entrance, exit;
    // 用一维数组表示迷宫，0 号下标位置空出
    private Place[] maze = null;

    // 设置迷宫中每一个格子下一步能移动的方向
    private void setDirections(Place[] maze) {
        for (int i = 1; i <= size * size; i++) {
            if (i % size != 0 && maze[i + 1].getWall() == 0 && maze[i + 1] != null) {
                maze[i].setEast(maze[i + 1]);
            }
            if (i <= size * (size - 1) && maze[i + size].getWall() == 0 && maze[i + size] != null) {
                maze[i].setSouth(maze[i + size]);
            }
            if (i % size != 1 && maze[i - 1].getWall() == 0 && maze[i - 1] != null) {
                maze[i].setWest(maze[i - 1]);
            }
            if (i > size && maze[i - size].getWall() == 0 && maze[i - size] != null) {
                maze[i].setNorth(maze[i - size]);
            }
        }
    }
    // 设置默认迷宫参数
    public CreateMaze() {
        this.size = 10;
        this.entrance = 1;
        this.exit = this.size * this.size;
    }
    // 调用有参构造函数获取新迷宫大小及入口和出口
    public CreateMaze(int size, int entrance, int exit) {
        this.size = size;
        this.entrance = entrance;
        this.exit = exit;
    }
    // 返回当前迷宫格随机状态
    public Place[] getMaze() {
        maze = new Place[size * size + 1];
        for (int i = 1; i <= size * size; i++) {
            maze[i] = new Place((int) (Math.random() * 2));
            maze[i].setIndex(i);        // 设为 0 或 1
        }
        setDirections(maze);
        return maze;
    }
    // 返回入口索引
    public int getEntrance() {
        return entrance;
    }
    // 设置入口索引
    public void setEntrance(int entrance) {
        this.entrance = entrance;
    }
    // 返回出口索引
    public int getExit() {
        return exit;
    }
    // 设置出口索引
    public void setExit(int exit) {
        this.exit = exit;
    }
    // 返回迷宫大小 size 表示边长
    public int getSize() {
        return size;
    }
    // 设置迷宫边长
    public void setSize(int size) {
        this.size = size;
    }

}

