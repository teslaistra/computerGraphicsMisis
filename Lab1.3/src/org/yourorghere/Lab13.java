package org.yourorghere;

import com.sun.opengl.util.Animator;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;



/**
 * Lab13.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel) <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class Lab13 implements GLEventListener {

   public static void main(String[] args) {
        Frame frame = new Frame("Simple JOGL Application");
        GLCanvas canvas = new GLCanvas();

        canvas.addGLEventListener(new Lab13());
        frame.add(canvas);
        frame.setSize(640, 640);
        final Animator animator = new Animator(canvas);
        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                // Run this on another thread than the AWT event queue to
                // make sure the call to Animator.stop() completes before
                // exiting
                new Thread(new Runnable() {

                    public void run() {
                        animator.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });
        // Center frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        //animator.start();
        animator.stop();

    }

    public void init(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        System.err.println("INIT GL IS: " + gl.getClass().getName());

        // Enable VSync
        gl.setSwapInterval(1);

        // Setup the drawing area and shading mode
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glShadeModel(GL.GL_SMOOTH);  // try setting this to GL_FLAT and see what happens.
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL gl = drawable.getGL();
    }

    public void display(GLAutoDrawable drawable) {
         GL gl = drawable.getGL();
        gl.glClearColor(1.0f,1.0f,1.0f,0.0f);
     
        //окаймовку рисуем
        gl.glBegin(GL.GL_POLYGON);
        gl.glColor3f(0.0f,0.0f,1.0f);
        gl.glVertex2d(0.95,0.95);
        gl.glVertex2d(0.95,-0.95);
        gl.glVertex2d(-0.95,-0.95);
        gl.glVertex2d(-0.95,0.95);

        gl.glEnd();
        
        //автобус рисуем
        gl.glBegin(GL.GL_POLYGON);
        gl.glColor3f(1.0f,1.0f,1.0f);
        gl.glVertex2d(-0.25,-0.05);
        gl.glVertex2d(0.25,-0.05);
        gl.glVertex2d(0.25,-0.65);
        gl.glVertex2d(-0.25,-0.65);

        gl.glEnd();
        
        //окно рисуем
        gl.glBegin(GL.GL_POLYGON);
        gl.glColor3f(0.0f,0.0f,1.0f);
        gl.glVertex2d(-0.20,-0.10);
        gl.glVertex2d(0.20,-0.10);
        gl.glVertex2d(0.20,-0.35);
        gl.glVertex2d(-0.20,-0.35);

        gl.glEnd();
        
        //тело стрелки рисуем
        gl.glBegin(GL.GL_TRIANGLE_STRIP);
        
            gl.glColor3f(1.0f,1.0f,1.0f);
            gl.glVertex2d(0.10,0);
            gl.glVertex2d(-0.10,0);
            gl.glVertex2d(0.10,0.25);
            gl.glVertex2d(-0.10,0.25);

        gl.glEnd();
        
        
        // стрелки рисуем
        gl.glBegin(GL.GL_TRIANGLES);
            gl.glColor3f(1.0f,1.0f,1.0f);
            gl.glVertex2d(-0.25,0.25);
            gl.glVertex2d(0,0.75);
            gl.glVertex2d(0.25,0.25);
        gl.glEnd();
        
        //основания колес рисуем
        gl.glBegin(GL.GL_QUADS);
            gl.glColor3f(1.0f,1.0f,1.0f);
            gl.glVertex2d(-0.15,-0.70);
            gl.glVertex2d(-0.15,-0.75);
            gl.glVertex2d(-0.25,-0.75);
            gl.glVertex2d(-0.25,-0.7);

            gl.glVertex2d(0.15,-0.70);
            gl.glVertex2d(0.15,-0.75);
            gl.glVertex2d(0.25,-0.75);
            gl.glVertex2d(0.25,-0.7);

        gl.glEnd();
        // рисуем скругления на колесах
        gl.glTranslatef(-0.2f,-0.75f,0f);
        double R = 0.05, alpha, da, x,y; 
        alpha = Math.PI;
        
        da = Math.PI/9;
        gl.glBegin(GL.GL_POLYGON);
        gl.glColor3f(1f,1.0f,1.0f);

        while (alpha <= Math.PI*2){
            x = R*Math.cos(alpha);
            y = R*Math.sin(alpha);
            gl.glVertex2d(x,y);
            alpha = alpha+da;
            
        }
        gl.glEnd();
        
        gl.glTranslatef(0.4f,0f,0f);
         alpha = Math.PI;
        
        da = Math.PI/9;
        gl.glBegin(GL.GL_POLYGON);
        gl.glColor3f(1.0f,1.0f,1.0f);

        while (alpha <= Math.PI*2){
            x = R*Math.cos(alpha);
            y = R*Math.sin(alpha);
            gl.glVertex2d(x,y);
            alpha = alpha+da;
            
        }
        gl.glEnd();
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
    
}

