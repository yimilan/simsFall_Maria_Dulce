package simpsonsfall;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.Timer;

public class SimpsonsFall extends JFrame {
    JLabel barraInfo; 
    JLabel fondo;
    JLabel tiempoJuego;
    Homero personaje;
    
    ImageIcon []imagenesF;
    private final int MAX_IMG = 6;
    private int tiempo,posIni;
    private final Timer tmrTiempo;
    private boolean bajar;
    
    private final Timer saltar;

    private int ancho = 1800;
    private int alto = 388;
    
    private int fondoActual = 0;

    public SimpsonsFall(String nombre) throws HeadlessException {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("SimpsonsFall - Dulce Garcia - Maria Valenzuela");
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/dona_menu.png"));
        setIconImage(icon);
        setSize(ancho, alto+30);

        barraInfo = new JLabel();
        barraInfo.setBounds(0, 0, ancho, 30);
        barraInfo.setOpaque(true);
        barraInfo.setBackground(Color.gray);
        
        /* Crear el vector de imagenes */
        
        imagenesF = new ImageIcon[MAX_IMG];
        
        String direcciones[] = {"/imagenes/escenario1.jpg", "/imagenes/escenario2.jpg",
                                "/imagenes/escenario3.jpg", "/imagenes/escenario4.jpg",
                                "/imagenes/escenario5.jpg", "/imagenes/escenario6.jpg",
                                "/imagenes/escenario7.jpg"};
        
        for (int i = 0; i < imagenesF.length; i++) {
            String data = direcciones[i];
            URL url = getClass().getResource(data);
            ImageIcon image = new ImageIcon(url);
            
            imagenesF[i] = new ImageIcon(image.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH));
        }
            
        fondo = new JLabel(imagenesF[this.fondoActual]);
        fondo.setBounds(0, 30, ancho, alto);

        tiempo = 0;
        tmrTiempo = new Timer(1000, new ContadorTiempo());
        saltar = new Timer(20,new saltarTimer());
        
        tiempoJuego = new JLabel(" Tiempo: 00:00 ");
        tiempoJuego.setBounds(500, 10, 100, 15);
        tiempoJuego.setForeground(Color.WHITE); 
        
        personaje = new Homero(0, alto,nombre);
        fondo.add(personaje);
        
        barraInfo.add(tiempoJuego);
        barraInfo.add(personaje.getSalud());
        barraInfo.add(personaje.getVidas());
        barraInfo.add(personaje.getNombre());

        tmrTiempo.start();
        
        posIni = 0;
        bajar = false;
        
        getContentPane().add(fondo);
        getContentPane().add(barraInfo);
        System.out.println(nombre);
          addKeyListener(new moverHomer());
        setLocationRelativeTo(null);
        this.setVisible(true);
    }//constructorSimpsonsFall

    /**
     * Eventos
     */
    
    public void reiniciarHomer() {
        if(this.personaje.getX() + 6 + (this.personaje.getWidth()/2) > this.getWidth()) {
            this.fondo.setIcon(imagenesF[fondoActual]);
            this.personaje.setX(0 - (this.personaje.getWidth() / 2));
            
            if(this.fondoActual == (MAX_IMG - 1))
                this.fondoActual = -1;
            this.fondoActual++;
        } else if(this.personaje.getX() + (this.personaje.getWidth()/2) - 6 < 0) {
            this.fondo.setIcon(imagenesF[fondoActual]);
            this.personaje.setX(this.getWidth() - this.personaje.getWidth()/2);
            
            if(this.fondoActual == 0)
                this.fondoActual = MAX_IMG;
            this.fondoActual--;
        }
        this.fondo.repaint();
    }
    
    public class moverHomer extends KeyAdapter {
// se mueve homero en el panel del juego 
        @Override
        public void keyPressed(KeyEvent e) {
            reiniciarHomer();
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    if (personaje.getY() - 10 > 0) {
                        personaje.setY(personaje.getY() - 10);
                        personaje.setLocation(personaje.getX(), personaje.getY());
                        personaje.repaint();
                        repaint();
                    }//if
                    break;
                case KeyEvent.VK_RIGHT:
                    if (personaje.getX() + 10 < (ancho - personaje.getWidth())) {
                        personaje.setX(personaje.getX() + 10);
                        personaje.setLocation(personaje.getX(), personaje.getY());
                        personaje.repaint();
                        repaint();
                    }//if   
                    break;
                case KeyEvent.VK_DOWN:
                    if (personaje.getY() + personaje.getHeight() + 5 < alto - 25) {
                        personaje.setY(personaje.getY() + 10);
                        personaje.setLocation(personaje.getX(), personaje.getY());
                        personaje.repaint();
                        repaint();
                    }//if

                    break;
                case KeyEvent.VK_LEFT:
                    if (personaje.getX() - 5 > -personaje.getWidth()/2) {
                        personaje.setX(personaje.getX() - 10);
                        personaje.setLocation(personaje.getX(), personaje.getY());
                        personaje.repaint();
                        repaint();
                    }//if
                    break;
                    case KeyEvent.VK_SPACE:
                        posIni = personaje.getY();
                        saltar.start();
                    break;
            }            
        }//keyPressed

    }//teclas
    public class ContadorTiempo implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            tiempo++;
            tiempoJuego.setText(TiempoTranscurrido());
            repaint();
        }//action performed

    }//Class contador tiempo
    
     public class saltarTimer implements ActionListener {
//   homero aplica el salto 
        public void actionPerformed(ActionEvent e) {
        
          if(posIni-personaje.getY() < 70 && !bajar){ 
            if (personaje.getY() - 2 > 0) {
                personaje.setY(personaje.getY() - 2);
                personaje.setLocation(personaje.getX(), personaje.getY());
                personaje.repaint();
               repaint();
            }//if
          }else{
              bajar = true;
            if (personaje.getY() + personaje.getHeight() + 5 < alto - 25) {
                personaje.setY(personaje.getY() + 2);
                personaje.setLocation(personaje.getX(), personaje.getY());
                personaje.repaint();
                repaint();
            }//if
          }
          if(posIni == personaje.getY() && bajar){
              bajar = false;
              saltar.stop();
          }
        }//action performed

    }//Class contador tiempo
    /**
     * Decisiones para obtener el tiempo
     * @return retorna cadena para especificar tiempo transcurrido
     */
    public String TiempoTranscurrido() {
        int min = tiempo / 60;
        int seg = tiempo % 60;
        String cadena = "";
        if (min < 10 && seg < 10) {
            cadena = " Tiempo: 0" + min + ":0" + seg + " ";
        } else if (min < 10 && seg > 9) {
            cadena = " Tiempo: 0" + min + ":" + seg + " ";
        } else if (min > 9 && seg < 10) {
            cadena = " Tiempo: " + min + ":0" + seg + " ";
        } else if (min > 9 && seg > 9) {
            cadena = " Tiempo: " + min + ":" + seg + " ";
        }
        return cadena;
    }// metodo de tiempo

}//class_Simpsons
