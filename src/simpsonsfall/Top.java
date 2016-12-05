/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpsonsfall;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;

/**
 *
 * @author MASTER
 */
public class Top extends JFrame {

    JLabel ganadores;
    JButton regresar;

    public Top() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setSize(800, 687);
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/dona_menu.png"));
        setIconImage(icon);

        Top.EventosBotones evento = new Top.EventosBotones();
        
        ImageIcon gano = new ImageIcon(getClass().getResource("/imagenes/fondotop.jpg"));
        ganadores = new JLabel(gano);
        ganadores.setBounds(0, 0, 800, 687);

        ImageIcon regresa = new ImageIcon(getClass().getResource("/imagenes/back.png"));
        regresar = new JButton();
        regresar.setBounds(700, 600, 90, 42);
        regresar.setBorderPainted(false);
        regresar.setContentAreaFilled(false);
        regresar.setIcon(regresa);
        regresar.setPressedIcon(new ImageIcon(getClass().getResource("/imagenes/back_press.png")));
        regresar.addActionListener(evento);
        ganadores.add(regresar);

        getContentPane().add(ganadores);

        setLocationRelativeTo(null);
        this.setVisible(true);
    }//Constructor_Top

    public class EventosBotones implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == regresar) {
                setVisible(false);
                MenuPrincipal menu = new MenuPrincipal(false);
            }//if volver
        }//clausula

    }//EventosBotones 

}//Top

