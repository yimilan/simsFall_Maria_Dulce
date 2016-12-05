package simpsonsfall;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ReanudarPartida extends JFrame {

    JLabel reanuda;
    JTextField name_player;
    JButton seguir;
    JButton volver;

    public ReanudarPartida() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setSize(625, 472);
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/dona_menu.png"));
        setIconImage(icon);
        
        ReanudarPartida.EventosBotones evento = new ReanudarPartida.EventosBotones();

        ImageIcon panel_reanudar = new ImageIcon(getClass().getResource("/imagenes/reanudar.jpg"));
        reanuda = new JLabel(panel_reanudar);
        reanuda.setBounds(0, 0, 625, 472);

        name_player = new JTextField("Nombre del Jugador");
        name_player.setBounds(270,380,150,30);
        reanuda.add(name_player);

        ImageIcon icon_seguir = new ImageIcon(getClass().getResource("/imagenes/next.png"));
        seguir = new JButton();
        seguir.setBounds(500, 380, 90, 44);
        seguir.setBorderPainted(false);
        seguir.setContentAreaFilled(false);
        seguir.setIcon(icon_seguir);
        seguir.setPressedIcon(new ImageIcon(getClass().getResource("/imagenes/next_press.png")));
        seguir.addActionListener(evento);
        reanuda.add(seguir);

        ImageIcon icon_volver = new ImageIcon(getClass().getResource("/imagenes/back.png"));
        volver = new JButton();
        volver.setBounds(500, 10, 90, 42);
        volver.setBorderPainted(false);
        volver.setContentAreaFilled(false);
        volver.setIcon(icon_volver);
        volver.setPressedIcon(new ImageIcon(getClass().getResource("/imagenes/back_press.png")));
        volver.addActionListener(evento);
        reanuda.add(volver);

        getContentPane().add(reanuda);

        setLocationRelativeTo(null);
        this.setVisible(true);
    }//Constructor_ReanudarPartida
    
     public class EventosBotones implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == seguir) {
                if(!name_player.getText().equals("Nombre del Jugador") && !name_player.getText().equals(" ") && !name_player.getText().equals("")){
                    setVisible(false);
                    SimpsonsFall juego = new SimpsonsFall(name_player.getText());
                }else{
                    JOptionPane.showMessageDialog(reanuda, "Por favor, Ingrese el nombre","Importante",JOptionPane.WARNING_MESSAGE);
                }//else
            }//ifseguir
            if (e.getSource() == volver) {
                setVisible(false);
                MenuPrincipal menu = new MenuPrincipal(false);
            }//if volver
        }//clausula

    }//EventosBotones 

}//ReanudarPartida
