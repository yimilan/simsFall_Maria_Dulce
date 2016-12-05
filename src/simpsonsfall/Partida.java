package simpsonsfall;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class Partida extends JFrame {

    JLabel jugador;
    JTextField name;
    JButton seguir;
    JButton volver;

    public Partida() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("SimpsonsFall - Dulce Garcia - Maria Valenzuela");
        setSize(500, 579);
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/dona_menu.png"));
        setIconImage(icon);

        EventosBotones evento = new EventosBotones();

        ImageIcon nombre = new ImageIcon(getClass().getResource("/imagenes/nombre_jugador.jpg"));
        jugador = new JLabel(nombre);
        jugador.setBounds(0, 0, 500, 579);

        name = new JTextField("Escribe tu nombre...");
        name.setBounds(180, 510, 180, 30);
        jugador.add(name);

        ImageIcon icon_seguir = new ImageIcon(getClass().getResource("/imagenes/next.png"));
        seguir = new JButton();
        seguir.setBounds(380, 500, 90, 44);
        seguir.setBorderPainted(false);
        seguir.setContentAreaFilled(false);
        seguir.setIcon(icon_seguir);
        seguir.setPressedIcon(new ImageIcon(getClass().getResource("/imagenes/next_press.png")));
        seguir.addActionListener(evento);
        jugador.add(seguir);

        ImageIcon icon_volver = new ImageIcon(getClass().getResource("/imagenes/back.png"));
        volver = new JButton();
        volver.setBounds(380, 10, 90, 42);
        volver.setBorderPainted(false);
        volver.setContentAreaFilled(false);
        volver.setIcon(icon_volver);
        volver.setPressedIcon(new ImageIcon(getClass().getResource("/imagenes/back_press.png")));
        volver.addActionListener(evento);
        jugador.add(volver);

        getContentPane().add(jugador);

        setLocationRelativeTo(null);
        this.setVisible(true);
    }//constructor    

    public class EventosBotones implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == seguir) {
                if(!name.getText().equals("Escribe tu nombre...") && !name.getText().equals(" ") && !name.getText().equals("")){
                    setVisible(false);
                    SimpsonsFall juego = new SimpsonsFall(name.getText());
                }else{
                    JOptionPane.showMessageDialog(jugador, "Por favor, Ingrese el nombre","Importante",JOptionPane.WARNING_MESSAGE);
                }//else
            }//ifseguir
            if (e.getSource() == volver) {
                setVisible(false);
                MenuPrincipal menu = new MenuPrincipal(false);
            }//if volver
        }//clausula

    }//EventosBotones 
}//partida
