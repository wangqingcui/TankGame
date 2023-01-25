package com.qingcui.tankgame3;

import javax.swing.*;

/**
 * @author 王清脆
 * @version 1.0
 */
public class TankGame03 extends JFrame {
//    定义MyPanel
    MyPanel mp=null;
    public static void main(String[] args) {
        TankGame03 tankGame01 = new TankGame03();
    }
   public TankGame03(){
        mp=new MyPanel();
        this.add(mp);//把面板（绘图区）
       this.setSize(1000,750);
       this.addKeyListener(mp);//增加监听
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       this.setVisible(true);
   }
}
