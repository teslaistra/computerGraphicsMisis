package org.yourorghere;

import com.sun.opengl.util.Animator;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;

public class Lab11 implements GLEventListener {

    public static void main(String[] args) {
        Frame frame = new Frame("Simple JOGL Application");
        GLCanvas canvas = new GLCanvas();

        canvas.addGLEventListener(new Lab11());
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
        gl.glPointSize(5);

        gl.glClearColor(0.0f,0.0f,0.0f,0.0f);
        int n = 32; 
        double R = 0.7,innerR=0.3, alpha, da, x,y; 
        alpha = 0;
        
        da = 2 * Math.PI/n;
        gl.glBegin(GL.GL_LINE_LOOP);

            while (alpha <= Math.PI*2){
                x = R*Math.cos(alpha);
                y = R*Math.sin(alpha);
                gl.glVertex2d(x,y);
                alpha = alpha+da;

            }
        gl.glEnd();
        
        
        gl.glColor3f(1.0f,0.0f,0.0f);

        
        n = 200;
        da = 2 * Math.PI/n;
        alpha = 0; 
        double r = R; 
        for(int i=1; i <= n; i++){
            gl.glBegin(GL.GL_POINTS);

                r = innerR + Math.random() * (R-innerR);
                x = r*Math.cos(alpha);
                y = r*Math.sin(alpha);

                gl.glVertex2d(x, y);
            gl.glEnd();
            alpha = alpha+da;
        }
        
        
        gl.glBegin(GL.GL_LINE_LOOP);
        
            gl.glColor3f(1.0f,1.0f,1.0f);

            alpha = 0;
            while (alpha <= Math.PI*2){

                x = innerR*Math.cos(alpha);
                y = innerR*Math.sin(alpha);
                gl.glVertex2d(x,y);
                alpha = alpha+da;

            }
        
        gl.glEnd();



    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
}

