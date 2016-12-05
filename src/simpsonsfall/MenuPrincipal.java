package simpsonsfall;

import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.*;

public class MenuPrincipal extends JFrame {

    JLabel labelMenu;
    JLabel menu_princ;
    JLabel instrucciones;

    JButton b_start;
    JButton b_play;
    JButton b_restart;
    JButton b_help;
    JButton b_top;
    JButton b_exit;
    JButton back;

    /* boolean portada - variable booleana que indica: si portada es true -> muestra portada simple
     si no -> menu principal   */
    public MenuPrincipal(boolean portada) throws HeadlessException {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("SimpsonsFall - Dulce Garcia - Maria Valenzuela");
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/dona_menu.png"));
        setIconImage(icon);

        Botones handler = new Botones();

        ImageIcon menu = new ImageIcon(getClass().getResource("/imagenes/menu.jpg"));
        labelMenu = new JLabel(menu);
        labelMenu.setBounds(0, 0, 700, 465);

        ImageIcon menup = new ImageIcon(getClass().getResource("/imagenes/menu_principal.png"));
        menu_princ = new JLabel(menup);
        menu_princ.setBounds(0, 0, 700, 314);

        ImageIcon inst = new ImageIcon(getClass().getResource("/imagenes/instrucciones.jpg"));
        instrucciones = new JLabel(inst);
        instrucciones.setBounds(0, 0, 700, 392);

        ImageIcon icon_iniciar = new ImageIcon(getClass().getResource("/imagenes/b_start.png"));
        b_start = new JButton();
        b_start.setBounds(10, 360, 156, 57);
        b_start.setBorderPainted(false);
        b_start.setContentAreaFilled(false);
        b_start.setIcon(icon_iniciar);
        b_start.setPressedIcon(new ImageIcon(getClass().getResource("/imagenes/b_start_press.png")));
        b_start.addActionListener(handler);

        b_play = new JButton();
        b_play.setBounds(370, 100, 90, 46);
        b_play.setBorderPainted(false);
        b_play.setContentAreaFilled(false);
        b_play.setIcon(new ImageIcon(getClass().getResource("/imagenes/play.png")));
        b_play.addActionListener(handler);
        b_play.setPressedIcon(new ImageIcon(getClass().getResource("/imagenes/play_press.png")));

        b_restart = new JButton();
        b_restart.setBounds(520, 100, 130, 46);
        b_restart.setBorderPainted(false);
        b_restart.setContentAreaFilled(false);
        b_restart.setIcon(new ImageIcon(getClass().getResource("/imagenes/restart.png")));
        b_restart.addActionListener(handler);
        b_restart.setPressedIcon(new ImageIcon(getClass().getResource("/imagenes/restart_press.png")));

        b_help = new JButton();
        b_help.setBounds(370, 170, 92, 46);
        b_help.setBorderPainted(false);
        b_help.setContentAreaFilled(false);
        b_help.setIcon(new ImageIcon(getClass().getResource("/imagenes/help.png")));
        b_help.addActionListener(handler);
        b_help.setPressedIcon(new ImageIcon(getClass().getResource("/imagenes/help_press.png")));

        b_top = new JButton();
        b_top.setBounds(525, 170, 92, 46);
        b_top.setBorderPainted(false);
        b_top.setContentAreaFilled(false);
        b_top.setIcon(new ImageIcon(getClass().getResource("/imagenes/top.png")));
        b_top.addActionListener(handler);
        b_top.setPressedIcon(new ImageIcon(getClass().getResource("/imagenes/top_press.png")));

        b_exit = new JButton();
        b_exit.setBounds(450, 220, 92, 46);
        b_exit.setBorderPainted(false);
        b_exit.setContentAreaFilled(false);
        b_exit.setIcon(new ImageIcon(getClass().getResource("/imagenes/exit.png")));
        b_exit.addActionListener(handler);
        b_exit.setPressedIcon(new ImageIcon(getClass().getResource("/imagenes/exit_press.png")));

        back = new JButton();
        back.setBounds(580, 10, 92, 46);
        back.setBorderPainted(false);
        back.setContentAreaFilled(false);
        back.setIcon(new ImageIcon(getClass().getResource("/imagenes/back.png")));
        back.addActionListener(handler);
        back.setPressedIcon(new ImageIcon(getClass().getResource("/imagenes/back_press.png")));

        menu_princ.add(b_play);
        menu_princ.add(b_restart);
        menu_princ.add(b_help);
        menu_princ.add(b_top);
        menu_princ.add(b_exit);

        instrucciones.add(back);

        labelMenu.add(b_start);

        if (portada) {
            getContentPane().add(labelMenu);
            setSize(700, 465);
        }//if portada
        else {
            getContentPane().add(menu_princ);
            setSize(700, 314);
        }//else principal
        setLocationRelativeTo(null);
        this.setVisible(true);
    }//contructor_de_menuPrincipal

    public class Botones implements ActionListener {

        public void actionPerformed(ActionEvent event) {

            if (event.getSource() == b_start) {
                getContentPane().remove(labelMenu);
                getContentPane().add(menu_princ);
                setSize(700, 314);
                repaint();
            }//if b_start
            
            if (event.getSource() == b_play) {
                setVisible(false);
                Partida juego = new Partida();
            }//if jugar
            
            if (event.getSource() == b_restart) {
                setVisible(false);
                ReanudarPartida jugar = new ReanudarPartida();
            }//if reiniciar 
            
            if (event.getSource() == b_help) {
                getContentPane().remove(menu_princ);
                getContentPane().add(instrucciones);
                setSize(700, 392);
                repaint();
            }//if ayuda
            
            if (event.getSource() == b_top) {
                setVisible(false);
                Top ganan = new Top();
            }//if top 10
            
            if (event.getSource() == b_exit) {
                if (JOptionPane.showConfirmDialog(MenuPrincipal.this,
                        "¿Desea cerrar el juego?", "SimpsonsFall",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }//clausula
            }//if salir
            
            if (event.getSource() == back) {
                getContentPane().remove(instrucciones);
                getContentPane().add(menu_princ);
                setSize(700, 314);
                repaint();
            }//if top 10
        }//actionPerformed

    }//class eventos
}//class_menuPrincipal
