package MyMaze;

/**
 * 本类保存迷宫中每一个格子的信息。
 */

public class Place {
    //定义当前格子是否可走，若 wall → 0，则表示可走，若 wall → 1，则表示不可走
    private int wall;
    //表示当前格子是否被搜索过
    private boolean search = false;
    //表示当前格子的四个方向分别是哪些格子,搜索时的上一个格子。
    private Place east = null, south = null, west = null, north = null, last = null;
    //保存迷宫格子位置索引
    private int index = 0;

    public Place(int wall) {
        this.wall = wall;
    }

    public int getWall() {
        return wall;
    }

    public void setWall(int wall) {
        this.wall = wall;
    }

    public boolean isSearch() {
        return search;
    }

    public void setSearch(boolean search) {
        this.search = search;
    }

    public Place getEast() {
        return east;
    }

    public void setEast(Place east) {
        this.east = east;
    }

    public Place getSouth() {
        return south;
    }

    public void setSouth(Place south) {
        this.south = south;
    }

    public Place getWest() {
        return west;
    }

    public void setWest(Place west) {
        this.west = west;
    }

    public Place getNorth() {
        return north;
    }

    public void setNorth(Place north) {
        this.north = north;
    }

    public Place getLast() {
        return last;
    }

    public void setLast(Place last) {
        this.last = last;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

}

