package com.qingcui.tankgame;

import javax.swing.*;
import java.awt.*;

/**
 * @author 王清脆
 * @version 1.0
 */
//坦克大战绘图区
public class MyPanel extends JPanel {
//    定义我的坦克
    Hero hero=null;

    public MyPanel() {
        hero =new Hero(100,100);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0,0,1000,750);//填充矩形默认黑色

    }
}
