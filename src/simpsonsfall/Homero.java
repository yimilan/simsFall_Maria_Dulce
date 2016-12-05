package simpsonsfall;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import sprites.MissingSpriteActionException;
import sprites.SpriteMap;
import sprites.SpriteSheet;

public class Homero extends JLabel {

    private int x;
    private int y;
    private JLabel salud;
    private final JLabel vidas;
    private final JLabel nombre;
    //para los sprites
    private ImageIcon homer;
    private SpriteMap map;
    private SpriteSheet sheet;

    public Homero(int posX, int altoVentana, String nombre) {
        homer = new ImageIcon(getClass().getResource("/Imagenes/persona.jpg"));
        
        setIcon(homer);
        //ImageIcon.setImageObserver(homer); 

        map = new SpriteMap();
        map.addAction("Caminar", 0, 96, 38, 136, 8, true, 29);
        map.addAction("Run", 0, 144, 38, 182, 4, true, 29);
        map.addAction("Inactivo", 0, 4, 38, 42, 4, true, 1);
        map.addAction("Agacharse", 200, 11, 235, 42, 2, false, 5);
        try {
            //Crear la hoja de sprites
            sheet = new SpriteSheet(map, "Run", homer.getImage());
        } catch (MissingSpriteActionException ex) {
            Logger.getLogger(Homero.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.nombre = new JLabel(" Nombre: " + nombre);
        this.nombre.setBounds(10, 10, 300, 15);
        this.nombre.setForeground(Color.WHITE);

        this.x = posX;
        this.y = altoVentana - homer.getIconHeight() - 23;

        this.salud = new JLabel(" Salud: 100 ");
        this.salud.setBounds(700, 10, 100, 15);
        this.salud.setForeground(Color.WHITE);

        this.vidas = new JLabel(" Vidas: 4 ");
        this.vidas.setBounds(900, 10, 100, 15);
        this.vidas.setForeground(Color.WHITE);

        this.setBounds(x, y, homer.getIconWidth(), homer.getIconHeight());
        this.setIcon(homer);
    }

    public JLabel getNombre() {
        return nombre;
    }

    public void paint(Graphics g) {
        g.drawImage(homer.getImage(), x, y, homer.getIconWidth(), homer.getIconHeight(), null);
        this.setOpaque(false);
        this.setVisible(true);
        super.paintComponent(g);
    }//pain

    public int getX() {
        return x;

    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;

    }

    public void setY(int y) {
        this.y = y;
    }

    public int getIntSalud() {
        return Integer.parseInt(salud.getText());
    }

    public void setIntSalud(int salud) {
        this.salud.setText("" + salud);
    }

    public JLabel getSalud() {
        return salud;
    }

    public JLabel getVidas() {
        return vidas;
    }

    public int getIntVidas() {
        return Integer.parseInt(vidas.getText());
    }

    public void setIntVidas(int vidas) {
        this.vidas.setText("" + vidas);
    }

    public ImageIcon getHomer() {
        return homer;
    }

    public void setHomer(ImageIcon homer) {
        this.homer = homer;
    }

    public Rectangle getRectangle() {
        return (new Rectangle(x, y, homer.getIconHeight(), homer.getIconWidth()));
    }

}//Class Homero
